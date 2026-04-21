package ru.golshansky.maxbotapi.support;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import lombok.experimental.UtilityClass;
import ru.golshansky.maxbotapi.domain.FieldDoc;

/**
 * Утилитный класс для парсинга и нормализации HTML-документов
 * и текстового представления документации MAX API.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@UtilityClass
public class ParsingUtils {

    public static final String ROOT_URL = "https://dev.max.ru/docs-api";
    public static final String BASE_SERVER_URL = "https://platform-api.max.ru";

    public static final Set<String> OBJECT_SECTION_STOP_TITLES = Set.of("Пример объекта", "Пример");
    public static final Set<String> ALL_SECTION_TITLES = Set.of("Авторизация", "Параметры", "Тело запроса", "Результат", "Пример объекта", "Пример");
    public static final Set<String> TYPE_TOKENS = Set.of("string", "integer", "boolean", "number", "object", "enum", "apiKey");
    public static final Set<String> PRIMITIVE_TYPES = Set.of("string", "integer", "boolean", "number", "object", "array");
    public static final Set<String> IGNORED_PSEUDO_COMPONENTS = Set.of("ID", "URL", "Items", "Enum", "Nullable", "Default");

    private static final Pattern HTTP_SIGNATURE = Pattern.compile("\\b(GET|POST|PUT|PATCH|DELETE|DEL)\\s*([/][^\\s]+)");
    private static final Pattern METHOD_URL_PATTERN = Pattern.compile(".*/methods/([^/]+)(/.*)?");
    private static final Pattern PATH_VARIABLE_PATTERN = Pattern.compile("\\{([^}]+)}");
    private static final Pattern HIDDEN_PARAM_PATTERN = Pattern.compile("-([A-Za-z0-9_]+)-");
    private static final Pattern FIELD_NAME_PATTERN = Pattern.compile("[A-Za-z_][A-Za-z0-9_\\-]*");
    private static final Pattern COMPONENT_NAME_PATTERN = Pattern.compile("[A-Z][A-Za-z0-9_]*");
    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\[\\s*-?\\d+\\s*-\\s*-?\\d+\\s*])");
    private static final Pattern ENUM_PATTERN = Pattern.compile("\"([^\"]+)\"");
    private static final Pattern DEFAULT_BACKTICK_PATTERN = Pattern.compile("По умолчанию:\\s*`([^`]+)`");
    private static final Pattern DEFAULT_SCALAR_PATTERN = Pattern.compile("По умолчанию:\\s*(true|false|null|-?\\d+(?:\\.\\d+)?)\\b", Pattern.CASE_INSENSITIVE);

    /**
     * Удаляет из документа скрипты, стили, служебные элементы и HTML-комментарии.
     *
     * @param document HTML-документ
     */
    public static void sanitize(Document document) {
        document.select("script,style,noscript,template,svg").forEach(Node::remove);
        List<Node> comments = new ArrayList<>();
        collectComments(document, comments);
        comments.forEach(Node::remove);
    }

    private static void collectComments(Node node, List<Node> comments) {
        if (node instanceof Comment) {
            comments.add(node);
        }
        for (Node child : node.childNodes()) {
            collectComments(child, comments);
        }
    }

    /**
     * Нормализует URL: добавляет базовый префикс при необходимости,
     * убирает лишние слеши и хвостовые символы.
     *
     * @param url исходный URL
     * @return нормализованный URL
     * @throws IllegalArgumentException если URL некорректен
     */
    public static String normalizeUrl(String url) {
        String candidate = url;
        if (!candidate.startsWith("http://") && !candidate.startsWith("https://")) {
            candidate = ROOT_URL + "/" + candidate.replaceFirst("^/+", "");
        }
        try {
            URI uri = new URI(candidate);
            String path = uri.getPath() == null || uri.getPath().isBlank() ? "/" : uri.getPath().replaceAll("/{2,}", "/");
            if (!"/".equals(path)) {
                path = path.replaceAll("/+$", "");
            }
            URI normalized = new URI(uri.getScheme(), uri.getAuthority(), path, null, null);
            return normalized.toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Некорректный URL: " + candidate, e);
        }
    }

    /**
     * Проверяет, относится ли URL к документации.
     *
     * @param rootUrl корневой URL документации
     * @param url проверяемый URL
     * @return true, если URL относится к документации
     */
    public static boolean isDocsUrl(String rootUrl, String url) {
        URI root = URI.create(normalizeUrl(rootUrl));
        URI target = URI.create(normalizeUrl(url));
        return ("http".equals(target.getScheme()) || "https".equals(target.getScheme()))
            && root.getHost().equalsIgnoreCase(target.getHost())
            && target.getPath().startsWith(root.getPath());
    }

    /**
     * Проверяет, является ли URL страницей метода API.
     *
     * @param rootUrl корневой URL документации
     * @param url проверяемый URL
     * @return true, если URL соответствует методу API
     */
    public static boolean isMethodDocUrl(String rootUrl, String url) {
        return isDocsUrl(rootUrl, url) && normalizeUrl(url).contains("/methods/");
    }

    /**
     * Проверяет, является ли URL страницей объекта API.
     *
     * @param rootUrl корневой URL документации
     * @param url проверяемый URL
     * @return true, если URL соответствует объекту API
     */
    public static boolean isObjectDocUrl(String rootUrl, String url) {
        return isDocsUrl(rootUrl, url) && normalizeUrl(url).contains("/objects/");
    }

    /**
     * Извлекает абсолютный URL документации из ссылки.
     *
     * @param rootUrl корневой URL документации
     * @param baseUrl базовый URL текущей страницы
     * @param href значение атрибута href
     * @return абсолютный URL или null, если ссылка невалидна
     */
    public static String extractDocUrl(String rootUrl, String baseUrl, String href) {
        if (href == null) {
            return null;
        }
        String trimmed = href.trim();
        if (trimmed.isBlank() || trimmed.startsWith("#")) {
            return null;
        }
        String lowered = trimmed.toLowerCase(Locale.ROOT);
        if (lowered.startsWith("javascript:") || lowered.startsWith("mailto:") || lowered.startsWith("tel:")) {
            return null;
        }
        String absolute = normalizeUrl(URI.create(baseUrl).resolve(trimmed).toString());
        return isDocsUrl(rootUrl, absolute) ? absolute : null;
    }

    /**
     * Находит корневой элемент статьи в документе.
     *
     * @param document HTML-документ
     * @return элемент статьи или null, если не найден
     */
    public static Element findArticleRoot(Document document) {
        for (String selector : List.of("main", "article", "[role='main']")) {
            Element node = document.selectFirst(selector);
            if (node != null && node.selectFirst("h1") != null) {
                return node;
            }
        }
        Element h1 = document.selectFirst("h1");
        if (h1 == null) {
            return null;
        }
        Element parent = h1.parent();
        while (parent != null) {
            if (cleanText(parent.text()).length() > 200) {
                return parent;
            }
            parent = parent.parent();
        }
        return document.body();
    }

    /**
     * Преобразует HTML-статью в список текстовых строк.
     *
     * @param article элемент статьи
     * @return список строк
     */
    public static List<String> articleToLines(Element article) {
        List<String> lines = new ArrayList<>();
        var blockTags = Set.of("h1", "h2", "h3", "p", "blockquote", "li", "dt", "dd", "pre", "tr");
        var inlineContainerTags = Set.of("p", "blockquote", "li", "dt", "dd", "pre", "h1", "h2", "h3", "th", "td");

        for (Element element : article.getAllElements()) {
            var tag = element.tagName().toLowerCase(Locale.ROOT);
            if (blockTags.contains(tag)) {
                if (hasBlockAncestor(article, element, blockTags)) {
                    continue;
                }
                if ("tr".equals(tag)) {
                    for (Element cell : element.children()) {
                        addLine(lines, cell.text());
                    }
                    continue;
                }
                if ("li".equals(tag)) {
                    var textValue = cleanupParagraphText(element.text());
                    addLine(lines, textValue.isBlank() ? "" : "- " + textValue);
                    continue;
                }
                if ("blockquote".equals(tag)) {
                    var textValue = cleanupParagraphText(element.text());
                    addLine(lines, textValue.isBlank() ? "" : "> " + textValue);
                    continue;
                }
                addLine(lines, element.text());
                continue;
            }
            if (isStandaloneCode(article, element, inlineContainerTags)) {
                addLine(lines, element.text());
            }
        }
        return new ArrayList<>(lines);
    }

    /**
     * Возвращает текст статьи, разбитый на строки.
     *
     * @param article элемент статьи
     * @return список строк
     */
    public static List<String> articleTextLines(Element article) {
        List<String> lines = new ArrayList<>();
        List<String> rawLines = new ArrayList<>();
        collectTextLines(article, rawLines);
        for (String raw : rawLines) {
            addLine(lines, raw);
        }
        return new ArrayList<>(lines);
    }

    /**
     * Рекурсивно собирает текстовые узлы документа в виде последовательности строк.
     * Эквивалентно Python-подходу article.get_text("\n", strip=False), где
     * разделитель вставляется между всеми descendant text nodes.
     *
     * @param node текущий узел DOM
     * @param lines накопитель строк
     */
    private static void collectTextLines(Node node, List<String> lines) {
        if (node instanceof TextNode textNode) {
            lines.add(textNode.getWholeText());
            return;
        }
        for (Node child : node.childNodes()) {
            collectTextLines(child, lines);
        }
    }

    private static boolean hasBlockAncestor(Element article, Element element, Set<String> blockTags) {
        for (var parent : element.parents()) {
            if (parent == article) {
                return false;
            }
            if (blockTags.contains(parent.tagName().toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStandaloneCode(Element article, Element element, Set<String> inlineContainerTags) {
        if (!"code".equalsIgnoreCase(element.tagName())) {
            return false;
        }
        for (var parent : element.parents()) {
            if (parent == article) {
                break;
            }
            if (inlineContainerTags.contains(parent.tagName().toLowerCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

    private static void addLine(List<String> lines, String value) {
        String cleaned = cleanupParagraphText(value);
        if (cleaned.isBlank()) {
            return;
        }
        if (Set.of("Скопировать", "BASH", "JSON", "SHELL", "Markdown", "Отображение", "Код").contains(cleaned)) {
            return;
        }
        if (isNavigationNoise(cleaned)) {
            return;
        }
        if (lines.isEmpty() || !Objects.equals(lines.get(lines.size() - 1), cleaned)) {
            lines.add(cleaned);
        }
    }

    /**
     * Очищает текст: убирает спецсимволы и нормализует пробелы.
     *
     * @param text исходный текст
     * @return очищенный текст
     */
    public static String cleanText(String text) {
        if (text == null) {
            return "";
        }
        return normalizeSpaces(text.replace("\u00A0", " ").replace("\u200B", "").trim());
    }

    /**
     * Нормализует пробелы в тексте.
     *
     * @param text исходный текст
     * @return текст с нормализованными пробелами
     */
    public static String normalizeSpaces(String text) {
        return text == null ? "" : text.replaceAll("\\s+", " ").trim();
    }

    /**
     * Нормализует inline-код в тексте.
     *
     * @param text исходный текст
     * @return очищенный текст
     */
    public static String normalizeInlineCode(String text) {
        return cleanText(text).replace("`", "").replace("\u2014", "—").trim();
    }

    /**
     * Обрезает список строк, начиная с указанного заголовка.
     *
     * @param lines список строк
     * @param heading заголовок
     * @return список строк после заголовка
     */
    public static List<String> trimLinesFromHeading(List<String> lines, String heading) {
        String normalized = normalizeInlineCode(heading);
        for (int i = 0; i < lines.size(); i++) {
            if (normalizeInlineCode(lines.get(i)).equals(normalized)) {
                return new ArrayList<>(lines.subList(i + 1, lines.size()));
            }
        }
        return new ArrayList<>(lines);
    }

    /**
     * Разбивает строки на секции по заголовкам.
     *
     * @param lines строки документа
     * @param stopTitles заголовки, при достижении которых парсинг останавливается
     * @return карта секций
     */
    public static java.util.Map<String, List<String>> splitSections(List<String> lines, Set<String> stopTitles) {
        java.util.LinkedHashMap<String, List<String>> sections = new java.util.LinkedHashMap<>();
        sections.put("__description__", new ArrayList<>());
        String current = "__description__";
        for (String line : lines) {
            if (stopTitles != null && stopTitles.contains(line)) {
                break;
            }
            if (ALL_SECTION_TITLES.contains(line)) {
                current = line;
                sections.putIfAbsent(current, new ArrayList<>());
                continue;
            }
            sections.computeIfAbsent(current, ignored -> new ArrayList<>()).add(line);
        }
        return sections;
    }

    /**
     * Очищает текст абзаца от лишних пробелов и символов.
     *
     * @param text исходный текст
     * @return очищенный текст
     */
    public static String cleanupParagraphText(String text) {
        String value = normalizeInlineCode(text);
        value = value.replaceAll("\\s+([,.;:!?])", "$1");
        value = value.replaceAll("([\\[(/{])\\s+", "$1");
        value = value.replaceAll("\\s+([\\])}/])", "$1");
        value = value.replaceAll("\\s+—\\s+", " — ");
        return normalizeSpaces(value);
    }

    /**
     * Извлекает описание метода из текста.
     *
     * @param lines строки документа
     * @param summary краткое описание
     * @param method HTTP-метод
     * @param path путь API
     * @return описание метода
     */
    public static String extractMethodDescription(List<String> lines, String summary, String method, String path) {
        List<String> filtered = new ArrayList<>();
        String compactSignature = (method + path).replace(" ", "");
        for (String line : lines) {
            String normalized = normalizeInlineCode(line);
            if (normalized.isBlank()) {
                continue;
            }
            if (normalized.equals(summary) || normalized.equals(method) || normalized.equals(path)
                || normalized.equals(method + " " + path) || normalized.replace(" ", "").equals(compactSignature)) {
                continue;
            }
            if (extractHttpMethodAndPath(normalized) != null) {
                continue;
            }
            if (normalized.startsWith("Пример") || Set.of("Скопировать", "BASH", "JSON", "SHELL", "Код").contains(normalized)
                || normalized.startsWith("curl ") || normalized.startsWith("-X ")) {
                break;
            }
            filtered.add(normalized);
        }
        return cleanupMultilineDescription(filtered);
    }

    /**
     * Очищает и объединяет многострочное описание.
     *
     * @param parts части описания
     * @return объединённый текст
     */
    public static String cleanupMultilineDescription(List<String> parts) {
        List<String> cleaned = new ArrayList<>();
        for (String part : parts) {
            String normalized = normalizeInlineCode(part);
            if (!normalized.isBlank() && !Set.of("Скопировать", "JSON", "BASH", "SHELL", "Код").contains(normalized)) {
                cleaned.add(normalized);
            }
        }
        return String.join("\n\n", mergeDescriptionLines(cleaned)).trim();
    }

    /**
     * Объединяет строки описания в логические блоки.
     *
     * @param lines строки
     * @return список объединённых строк
     */
    public static List<String> mergeDescriptionLines(List<String> lines) {
        List<String> merged = new ArrayList<>();
        List<String> paragraph = new ArrayList<>();
        for (String rawLine : lines) {
            String line = cleanupParagraphText(rawLine);
            if (line.isBlank()) {
                continue;
            }
            if (line.startsWith("- ") || line.startsWith("> ") || line.endsWith(":")) {
                flushParagraph(merged, paragraph);
                merged.add(line);
            } else {
                paragraph.add(line);
            }
        }
        flushParagraph(merged, paragraph);
        return merged;
    }


    private static void flushParagraph(List<String> merged, List<String> paragraph) {
        if (paragraph.isEmpty()) {
            return;
        }
        merged.add(cleanupParagraphText(String.join(" ", paragraph)));
        paragraph.clear();
    }

    /**
     * Добавляет ссылку на источник в описание.
     *
     * @param description описание
     * @param url ссылка
     * @param label текст ссылки
     * @return обновлённое описание
     */
    public static String appendSourceLink(String description, String url, String label) {
        String source = "Источник описания: [" + label + "](" + url + ")";
        return description == null || description.isBlank() ? source : description.trim() + "\n\n" + source;
    }

    /**
     * Извлекает HTTP-метод и путь из URL страницы метода.
     *
     * @param url URL
     * @return массив [method, path] или null
     */
    public static String[] extractMethodAndPathFromUrl(String url) {
        Matcher matcher = METHOD_URL_PATTERN.matcher(URI.create(url).getPath());
        if (!matcher.matches()) {
            return null;
        }
        String method = normalizeHttpMethod(matcher.group(1));
        String rest = matcher.group(2) == null ? "" : matcher.group(2);
        String[] parts = rest.replaceFirst("^/+", "").split("/");
        List<String> pathParts = new ArrayList<>();
        for (String part : parts) {
            if (part.isBlank()) {
                continue;
            }
            Matcher hidden = HIDDEN_PARAM_PATTERN.matcher(part);
            if (hidden.matches()) {
                pathParts.add("{" + hidden.group(1) + "}");
            } else {
                pathParts.add(part);
            }
        }
        return new String[]{method, cleanupPath("/" + String.join("/", pathParts))};
    }

    /**
     * Извлекает HTTP-метод и путь из текста.
     *
     * @param text текст
     * @return массив [method, path] или null
     */
    public static String[] extractHttpMethodAndPath(String text) {
        Matcher matcher = HTTP_SIGNATURE.matcher(normalizeInlineCode(text));
        if (!matcher.find()) {
            return null;
        }
        return new String[]{normalizeHttpMethod(matcher.group(1)), cleanupPath(matcher.group(2))};
    }

    /**
     * Нормализует HTTP-метод.
     *
     * @param method метод
     * @return нормализованный метод
     */
    public static String normalizeHttpMethod(String method) {
        return "DEL".equalsIgnoreCase(method) ? "DELETE" : method.toUpperCase(Locale.ROOT);
    }

    /**
     * Очищает путь API.
     *
     * @param path путь
     * @return очищенный путь
     */
    public static String cleanupPath(String path) {
        return path.replace("`", "").replace(" ", "");
    }

    /**
     * Определяет тег (группу) по пути API.
     *
     * @param path путь API
     * @return тег
     */
    public static String inferTagFromPath(String path) {
        for (String part : path.split("/")) {
            if (!part.isBlank() && !part.startsWith("{")) {
                return part;
            }
        }
        return "default";
    }

    /**
     * Проверяет, является ли строка именем поля.
     *
     * @param text текст
     * @return true, если строка похожа на имя поля
     */
    public static boolean looksLikeFieldName(String text) {
        String value = normalizeInlineCode(text);
        if (value.isBlank() || value.length() > 80 || value.contains(" ") || ALL_SECTION_TITLES.contains(value)) {
            return false;
        }
        if (Set.of("Пример", "Пример запроса:", "Скопировать", "JSON", "BASH", "SHELL",
            "Nullable", "optional", "required", "Enum", "Items", "Default", "По", "умолчанию", "true", "false", "null").contains(value)) {
            return false;
        }
        if (value.startsWith("$") || value.startsWith("self.__next")) {
            return false;
        }
        if (extractHttpMethodAndPath(value) != null) {
            return false;
        }
        return FIELD_NAME_PATTERN.matcher(value).matches();
    }

    /**
     * Проверяет, является ли строка описанием типа.
     *
     * @param text текст
     * @return true, если строка похожа на тип
     */
    public static boolean looksLikeTypeLine(String text) {
        String value = normalizeInlineCode(text);
        if (value.isBlank()) {
            return false;
        }
        String first = value.split(" ")[0];
        if (TYPE_TOKENS.contains(first)) {
            return true;
        }
        if (first.matches("[A-Z][A-Za-z0-9_]*(\\[\\])?")) {
            return true;
        }
        return first.matches("[a-zA-Z][A-Za-z0-9_]*(\\[\\])?") && (first.contains("[]") || value.contains("optional") || value.contains("Nullable"));
    }

    /**
     * Извлекает значение по умолчанию из текста.
     *
     * @param text текст
     * @return значение или null
     */
    public static String extractDefaultValue(String text) {
        Matcher backtick = DEFAULT_BACKTICK_PATTERN.matcher(text);
        if (backtick.find()) {
            return backtick.group(1).trim();
        }
        Matcher scalar = DEFAULT_SCALAR_PATTERN.matcher(text);
        return scalar.find() ? scalar.group(1).trim() : null;
    }

    /**
     * Извлекает значения enum из текста.
     *
     * @param text текст
     * @return список значений
     */
    public static List<String> extractEnumValues(String text) {
        if (!text.startsWith("Enum:")) {
            return List.of();
        }
        List<String> values = new ArrayList<>();
        Matcher matcher = ENUM_PATTERN.matcher(text);
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values;
    }

    /**
     * Извлекает диапазон значений из текста.
     *
     * @param text текст
     * @return диапазон или null
     */
    public static String extractRangeHint(String text) {
        Matcher matcher = RANGE_PATTERN.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Извлекает регулярное выражение из текста.
     *
     * @param text текст
     * @return шаблон или null
     */
    public static String extractPatternHint(String text) {
        String stripped = text.trim().replace("`", "");
        if (stripped.isBlank()) {
            return null;
        }
        if (stripped.matches("\\[\\s*-?\\d+\\s*-\\s*-?\\d+\\s*]")) {
            return null;
        }
        if (stripped.startsWith("^")) {
            return stripped;
        }
        if (stripped.matches("[A-Za-zА-Яа-я0-9_./:\\-\\[\\]]+")) {
            return null;
        }
        return !stripped.contains(" ") && stripped.matches(".*[\\\\^$+?{}|()].*") ? stripped : null;
    }

    /**
     * Проверяет наличие маркера optional.
     *
     * @param text текст
     * @return true, если поле optional
     */
    public static boolean containsOptionalMarker(String text) {
        String normalized = normalizeSpaces(text).toLowerCase(Locale.ROOT);
        return (" " + normalized).contains(" optional") || normalized.endsWith(" optional");
    }

    /**
     * Проверяет наличие маркера nullable.
     *
     * @param text текст
     * @return true, если поле nullable
     */
    public static boolean containsNullableMarker(String text) {
        String normalized = normalizeSpaces(text).toLowerCase(Locale.ROOT);
        return (" " + normalized).contains(" nullable") || normalized.endsWith(" nullable");
    }

    /**
     * Проверяет, является ли строка метаданными поля.
     *
     * @param text текст
     * @return true, если строка содержит метаданные
     */
    public static boolean isFieldMetadataLine(String text) {
        String normalized = normalizeSpaces(text);
        String lowered = normalized.toLowerCase(Locale.ROOT);
        if (Set.of("optional", "nullable", "required", "bigint").contains(lowered)) {
            return true;
        }
        if (extractRangeHint(normalized) != null && normalized.equals(extractRangeHint(normalized))) {
            return true;
        }
        if (extractPatternHint(normalized) != null && normalized.equals(extractPatternHint(normalized))) {
            return true;
        }
        return normalized.matches("\\[[^]]+](?:\\s+(optional|nullable|required))*")
            || normalized.matches("(<[^>]+>|bigint|optional|nullable|required)(\\s+(<[^>]+>|bigint|optional|nullable|required))*");
    }

    /**
     * Формирует описание поля.
     *
     * @param parts части описания
     * @return описание поля
     */
    public static String cleanupFieldDescription(List<String> parts) {
        List<String> cleaned = new ArrayList<>();
        for (String part : parts) {
            String normalized = normalizeInlineCode(part);
            if (!normalized.isBlank() && !Set.of("Скопировать", "JSON", "BASH", "SHELL", "Код").contains(normalized)) {
                cleaned.add(normalized);
            }
        }
        if (cleaned.isEmpty()) {
            return "";
        }
        return String.join("\n\n", mergeFieldDescriptionLines(cleaned)).trim();
    }

    /**
     * Объединяет строки описания поля.
     *
     * @param lines строки
     * @return объединённые строки
     */
    public static List<String> mergeFieldDescriptionLines(List<String> lines) {
        List<String> merged = new ArrayList<>();
        List<String> paragraph = new ArrayList<>();
        for (String rawLine : lines) {
            String line = cleanupParagraphText(rawLine);
            if (line.isBlank()) {
                continue;
            }
            if (line.startsWith("- ") || line.startsWith("> ") || line.endsWith(":") || isConstraintLine(line) || startsNewDescriptionParagraph(line)) {
                flushParagraph(merged, paragraph);
                merged.add(line);
            } else {
                paragraph.add(line);
            }
        }
        flushParagraph(merged, paragraph);
        return merged;
    }

    /**
     * Проверяет, является ли строка ограничением поля.
     *
     * @param text текст
     * @return true, если строка содержит ограничения
     */
    public static boolean isConstraintLine(String text) {
        String line = cleanupParagraphText(text);
        return line.matches("от\\s+`?\\d+`?\\s+символ(?:а|ов)?")
            || line.matches("от\\s+`?\\d+`?\\s+до\\s+`?\\d+`?\\s+символ(?:а|ов)?")
            || line.matches("до\\s+`?\\d+`?\\s+символ(?:а|ов)?")
            || line.matches("<[^>]+>")
            || line.matches("\\[\\s*-?\\d+\\s*-\\s*-?\\d+\\s*]");
    }

    /**
     * Проверяет, начинается ли новая смысловая часть описания.
     *
     * @param text текст
     * @return true, если начинается новый абзац
     */
    public static boolean startsNewDescriptionParagraph(String text) {
        String line = cleanupParagraphText(text);
        return line.startsWith("Можно получить из")
            || line.startsWith("По умолчанию:")
            || line.startsWith("Например")
            || line.startsWith("Пример")
            || line.startsWith("Если ")
            || line.startsWith("Когда ")
            || line.startsWith("Чтобы ")
            || line.startsWith("Поддерживаемые форматы:")
            || line.startsWith("Возможные значения:");
    }

    /**
     * Формирует итоговое описание поля.
     *
     * @param field описание поля
     * @return текст описания
     */
    public static String renderFieldDescription(FieldDoc field) {
        List<String> parts = new ArrayList<>();
        if (field.getDescription() != null && !field.getDescription().isBlank()) {
            parts.add(field.getDescription().trim());
        }
        if (!field.getEnumValues().isEmpty()) {
            List<String> enumLines = new ArrayList<>();
            enumLines.add("Возможные значения:");
            for (String value : field.getEnumValues()) {
                enumLines.add("- `" + value + "`");
            }
            parts.add(String.join("\n", enumLines));
        }
        if (field.getDefaultValue() != null && (field.getDescription() == null || !field.getDescription().contains("По умолчанию:"))) {
            parts.add("По умолчанию: `" + field.getDefaultValue() + "`");
        }
        if (field.getRangeHint() != null && (field.getDescription() == null || !field.getDescription().contains(field.getRangeHint()))) {
            parts.add("Диапазон: `" + field.getRangeHint() + "`");
        }
        if (field.getPattern() != null && (field.getDescription() == null || !field.getDescription().contains(field.getPattern()))) {
            parts.add("Шаблон: `" + field.getPattern() + "`");
        }
        return String.join("\n\n", parts).trim();
    }

    /**
     * Добавляет отсутствующие path-параметры в список.
     *
     * @param params список параметров
     * @param path путь API
     * @return обновлённый список параметров
     */
    public static List<FieldDoc> ensurePathParameters(List<FieldDoc> params, String path) {
        List<FieldDoc> result = new ArrayList<>(params);
        Set<String> existing = new LinkedHashSet<>();
        for (FieldDoc field : params) {
            existing.add(field.getName());
        }
        Matcher matcher = PATH_VARIABLE_PATTERN.matcher(path);
        List<String> names = new ArrayList<>();
        while (matcher.find()) {
            names.add(matcher.group(1));
        }
        for (int i = names.size() - 1; i >= 0; i--) {
            String name = names.get(i);
            if (!existing.contains(name)) {
                FieldDoc field = new FieldDoc(name, "string");
                field.setDescription("Path parameter");
                field.setOptional(false);
                field.setNullable(false);
                result.add(0, field);
                existing.add(name);
            }
        }
        return result;
    }

    /**
     * Извлекает вводное описание метода в формате Markdown.
     *
     * @param article статья
     * @param h1 заголовок
     * @param summary краткое описание
     * @param method HTTP-метод
     * @param path путь API
     * @return Markdown-текст описания
     */
    public static String extractMethodIntroMarkdown(Element article, Element h1, String summary, String method, String path) {
        List<Element> processed = new ArrayList<>();
        List<String> blocks = new ArrayList<>();
        boolean started = false;
        for (Element element : article.getAllElements()) {
            if (element == h1) {
                started = true;
                continue;
            }
            if (!started) {
                continue;
            }
            if (processed.stream().anyMatch(parent -> element.parents().contains(parent))) {
                continue;
            }
            if (Set.of("h2", "h3").contains(element.tagName())) {
                String title = cleanText(element.text());
                if (Set.of("Авторизация", "Параметры", "Тело запроса", "Результат").contains(title)) {
                    break;
                }
            }
            if (!Set.of("p", "ul", "ol", "blockquote").contains(element.tagName())) {
                continue;
            }
            String text = tagToMarkdown(element);
            if (text.isBlank()) {
                processed.add(element);
                continue;
            }
            String normalized = cleanupParagraphText(text.replace("\n", " "));
            String compact = normalized.replace(" ", "");
            String methodPathCompact = (method + path).replace(" ", "");
            if (Set.of(summary, method, path, method + " " + path).contains(normalized) || compact.equals(methodPathCompact)) {
                processed.add(element);
                continue;
            }
            if (normalized.startsWith("Пример") || normalized.startsWith("curl ") || normalized.startsWith("-X ")) {
                break;
            }
            blocks.add(text);
            processed.add(element);
        }
        return String.join("\n\n", blocks).replaceAll("\n{3,}", "\n\n").trim();
    }

    private static String tagToMarkdown(Element tag) {
        String name = tag.tagName().toLowerCase(Locale.ROOT);
        if ("p".equals(name)) {
            return cleanupParagraphText(tag.text());
        }
        if (Set.of("ul", "ol").contains(name)) {
            List<String> items = new ArrayList<>();
            for (Element li : tag.children()) {
                if (!"li".equals(li.tagName())) {
                    continue;
                }
                String text = cleanupParagraphText(li.text());
                if (!text.isBlank()) {
                    items.add("- " + text);
                }
            }
            return String.join("\n", items);
        }
        if ("blockquote".equals(name)) {
            String text = cleanupParagraphText(tag.text());
            return text.isBlank() ? "" : "> " + text;
        }
        return "";
    }

    /**
     * Проверяет, является ли строка именем компонента.
     *
     * @param name имя
     * @return true, если строка является именем компонента
     */
    public static boolean looksLikeComponentName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        if (IGNORED_PSEUDO_COMPONENTS.contains(name) || PRIMITIVE_TYPES.contains(name.toLowerCase(Locale.ROOT))) {
            return false;
        }
        if (name.matches("[A-Z]{2,5}")) {
            return false;
        }
        return COMPONENT_NAME_PATTERN.matcher(name).matches();
    }

    /**
     * Извлекает имена компонентов из типа.
     *
     * @param rawType строковое представление типа
     * @return множество имён компонентов
     */
    public static Set<String> extractComponentNamesFromRawType(String rawType) {
        String value = normalizeSpaces(rawType).replaceAll("\\bNullable\\b", "").replaceAll("\\boptional\\b", "").replaceAll("\\[[^]]+]", "");
        value = normalizeSpaces(value);
        Set<String> names = new LinkedHashSet<>();
        if (value.startsWith("enum ")) {
            return names;
        }
        if (value.startsWith("object ")) {
            String refName = value.substring("object ".length()).trim();
            if (looksLikeComponentName(refName)) {
                names.add(refName);
            }
            return names;
        }
        String first = value.isBlank() ? "" : value.split(" ")[0];
        if (first.endsWith("[]")) {
            first = first.substring(0, first.length() - 2);
        }
        if (looksLikeComponentName(first)) {
            names.add(first);
        }
        return names;
    }

    /**
     * Подсчитывает вероятное количество полей объекта в наборе строк.
     *
     * @param lines строки документации
     * @return количество пар "имя поля + тип"
     */
    public static int countProbableObjectFields(List<String> lines) {
        var count = 0;
        for (var index = 0; index < lines.size() - 1; index++) {
            if (looksLikeFieldName(lines.get(index)) && looksLikeTypeLine(lines.get(index + 1))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Проверяет, является ли строка навигационным шумом.
     *
     * @param text текст
     * @return true, если строка является шумом
     */
    public static boolean isNavigationNoise(String text) {
        return Set.of("для разработчиков dev", "Документация", "MAX UI", "Помощь", "Меню", "Оглавление", "Методы API", "Объекты").contains(text)
            || text.startsWith("self.__next_f.push(")
            || text.startsWith("self.__next_f=")
            || text.startsWith("window.__NEXT_DATA__")
            || text.matches("^(GET|POST|PUT|PATCH|DEL|DELETE)\\s+[^/].*");
    }

    /**
     * Возвращает текущее время генерации в строковом формате.
     *
     * @return строка с датой и временем
     */
    public static String currentGeneratedAt() {
        return ZonedDateTime.now(java.time.ZoneId.of("Europe/Moscow"))
            .format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy"));
    }
}
