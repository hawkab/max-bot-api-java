package ru.golshansky.maxbotapi.parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ru.golshansky.maxbotapi.domain.FieldDoc;
import ru.golshansky.maxbotapi.domain.MethodDoc;
import ru.golshansky.maxbotapi.domain.ObjectDoc;
import ru.golshansky.maxbotapi.fetcher.HtmlFetcher;
import ru.golshansky.maxbotapi.support.ParsingUtils;

/**
 * Парсер документации MAX API.
 * Отвечает за сбор ссылок, парсинг методов и объектов, а также разрешение зависимостей между ними.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class MaxDocsParser {

    private static final Logger log = LoggerFactory.getLogger(MaxDocsParser.class);

    private final HtmlFetcher htmlFetcher;
    private final FieldDocParser fieldDocParser;

    public MaxDocsParser(HtmlFetcher htmlFetcher, FieldDocParser fieldDocParser) {
        this.htmlFetcher = htmlFetcher;
        this.fieldDocParser = fieldDocParser;
    }

    /**
     * Собирает ссылки на страницы методов и объектов с корневой страницы документации.
     *
     * @param rootUrl корневой URL документации
     * @return объект со списками URL методов и объектов
     */
    public RootLinks collectRootLinks(String rootUrl) {
        Document document = htmlFetcher.fetch(ParsingUtils.normalizeUrl(rootUrl));
        ParsingUtils.sanitize(document);

        Set<String> methodUrls = new LinkedHashSet<>();
        Set<String> objectUrls = new LinkedHashSet<>();
        for (var link : document.select("a[href]")) {
            var absolute = ParsingUtils.extractDocUrl(rootUrl, rootUrl, link.attr("href"));
            if (absolute == null) {
                continue;
            }
            if (ParsingUtils.isMethodDocUrl(rootUrl, absolute)) {
                methodUrls.add(absolute);
            } else if (ParsingUtils.isObjectDocUrl(rootUrl, absolute)) {
                objectUrls.add(absolute);
            }
        }
        return new RootLinks(List.copyOf(methodUrls), List.copyOf(objectUrls));
    }

    /**
     * Рекурсивно собирает все ссылки на страницы объектов, начиная с заданных URL.
     *
     * @param rootUrl корневой URL документации
     * @param urls начальный список URL
     * @return список уникальных URL объектов
     */
    public List<String> collectObjectLinksFromPages(String rootUrl, List<String> urls) {
        var seen = new LinkedHashSet<String>();
        var normalizedUrls = urls.stream()
                .map(ParsingUtils::normalizeUrl)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        var queue = new ArrayDeque<>(normalizedUrls);
        var objects = normalizedUrls.stream()
                .filter(url -> ParsingUtils.isObjectDocUrl(rootUrl, url))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        while (!queue.isEmpty()) {
            var url = queue.removeFirst();
            if (!seen.add(url)) {
                continue;
            }
            var document = htmlFetcher.fetch(url);
            ParsingUtils.sanitize(document);
            for (var link : document.select("a[href]")) {
                var absolute = ParsingUtils.extractDocUrl(rootUrl, url, link.attr("href"));
                if (absolute == null || !ParsingUtils.isObjectDocUrl(rootUrl, absolute)) {
                    continue;
                }
                if (objects.add(absolute)) {
                    queue.addLast(absolute);
                }
            }
        }

        return List.copyOf(objects);
    }

    /**
     * Парсит страницу метода API.
     * Извлекает метод, путь, описание, параметры, тело запроса и результат.
     *
     * @param url URL страницы метода
     * @return объект {@link MethodDoc} или null, если страница не распознана
     */
    public MethodDoc parseMethodPage(String url) {
        var document = htmlFetcher.fetch(url);
        ParsingUtils.sanitize(document);
        var article = ParsingUtils.findArticleRoot(document);
        if (article == null) {
            return null;
        }

        var h1 = article.selectFirst("h1");
        if (h1 == null) {
            return null;
        }

        var summary = ParsingUtils.cleanText(h1.text());
        var signature = ParsingUtils.extractMethodAndPathFromUrl(url);
        if (signature == null) {
            return null;
        }
        var method = signature[0];
        var path = signature[1];

        var primaryLines = ParsingUtils.trimLinesFromHeading(ParsingUtils.articleToLines(article), summary);
        var fallbackLines = ParsingUtils.trimLinesFromHeading(ParsingUtils.articleTextLines(article), summary);
        var primarySections = ParsingUtils.splitSections(primaryLines, null);
        var fallbackSections = ParsingUtils.splitSections(fallbackLines, null);

        var primaryParams = ParsingUtils.ensurePathParameters(fieldDocParser.parse(primarySections.getOrDefault("Параметры", List.of())), path);
        var primaryBody = fieldDocParser.parse(primarySections.getOrDefault("Тело запроса", List.of()));
        var primaryResult = fieldDocParser.parse(primarySections.getOrDefault("Результат", List.of()));
        var fallbackParams = ParsingUtils.ensurePathParameters(fieldDocParser.parse(fallbackSections.getOrDefault("Параметры", List.of())), path);
        var fallbackBody = fieldDocParser.parse(fallbackSections.getOrDefault("Тело запроса", List.of()));
        var fallbackResult = fieldDocParser.parse(fallbackSections.getOrDefault("Результат", List.of()));

        var params = primaryParams;
        var body = primaryBody;
        var result = primaryResult;
        if (score(fallbackParams, fallbackBody, fallbackResult) > score(primaryParams, primaryBody, primaryResult)) {
            params = fallbackParams;
            body = fallbackBody;
            result = fallbackResult;
        }

        var description = ParsingUtils.extractMethodIntroMarkdown(article, h1, summary, method, path);
        if (description.isBlank()) {
            description = ParsingUtils.extractMethodDescription(primarySections.getOrDefault("__description__", List.of()), summary, method, path);
        }
        if (description.isBlank()) {
            var source = fallbackSections.getOrDefault("__description__", List.of()).size()
                > primarySections.getOrDefault("__description__", List.of()).size() ? fallbackSections : primarySections;
            description = ParsingUtils.extractMethodDescription(source.getOrDefault("__description__", List.of()), summary, method, path);
        }

        var methodDoc = new MethodDoc();
        methodDoc.setUrl(url);
        methodDoc.setMethod(method);
        methodDoc.setPath(path);
        methodDoc.setSummary(summary);
        methodDoc.setDescription(ParsingUtils.appendSourceLink(description, url, method + " " + path));
        methodDoc.setParams(params);
        methodDoc.setBody(body);
        methodDoc.setResult(result);
        methodDoc.setTag(ParsingUtils.inferTagFromPath(path));
        return methodDoc;
    }

    /**
     * Парсит страницу объекта API.
     * Извлекает имя, описание и список полей.
     *
     * @param url URL страницы объекта
     * @param expectedName ожидаемое имя объекта (может быть null)
     * @return объект {@link ObjectDoc} или null, если страница не подходит
     */
    public ObjectDoc parseObjectPage(String url, String expectedName) {
        if (!ParsingUtils.normalizeUrl(url).contains("/objects/")) {
            return null;
        }

        var document = htmlFetcher.fetch(url);
        ParsingUtils.sanitize(document);
        var article = ParsingUtils.findArticleRoot(document);
        if (article == null) {
            return null;
        }

        var h1 = article.selectFirst("h1");
        if (h1 == null) {
            return null;
        }

        var name = ParsingUtils.cleanText(h1.text());
        if (expectedName != null && !expectedName.equals(name)) {
            return null;
        }
        if (Set.of("Страница не найдена", "MAX для разработчиков").contains(name)) {
            return null;
        }

        var primaryLines = ParsingUtils.articleToLines(article);
        var fallbackLines = ParsingUtils.articleTextLines(article);
        var primary = extractObject(name, primaryLines);
        var fallback = extractObject(name, fallbackLines);

        var selected = primary;
        if (fallback.fields().size() > primary.fields().size()) {
            selected = fallback;
        } else if (primary.fields().isEmpty() && ParsingUtils.countProbableObjectFields(fallbackLines) > ParsingUtils.countProbableObjectFields(primaryLines)) {
            selected = fallback;
        }

        var objectDoc = new ObjectDoc();
        objectDoc.setName(name);
        objectDoc.setUrl(url);
        objectDoc.setDescription(selected.description());
        objectDoc.setFields(selected.fields());
        return objectDoc;
    }

    /**
     * Разрешает неявно используемые объекты, которые не были найдены напрямую.
     * Пытается загрузить их по предполагаемым URL.
     *
     * @param rootUrl корневой URL документации
     * @param objects уже найденные объекты
     * @param methods список методов
     * @param parsedObjectNames множество уже обработанных имён объектов
     * @param parsedObjectUrls множество уже обработанных URL объектов
     */
    public void resolveHiddenObjectDocs(String rootUrl, List<ObjectDoc> objects, List<MethodDoc> methods,
                                        Set<String> parsedObjectNames, Set<String> parsedObjectUrls) {
        var progress = true;
        while (progress) {
            progress = false;
            var candidates = collectReferencedComponentNames(objects, methods);
            candidates.removeAll(parsedObjectNames);

            for (var name : candidates) {
                if (!ParsingUtils.looksLikeComponentName(name)) {
                    continue;
                }
                var guessedUrl = ParsingUtils.normalizeUrl(rootUrl + "/objects/" + name);
                if (parsedObjectUrls.contains(guessedUrl)) {
                    continue;
                }
                try {
                    var objectDoc = parseObjectPage(guessedUrl, name);
                    parsedObjectUrls.add(guessedUrl);
                    if (objectDoc == null || parsedObjectNames.contains(objectDoc.getName())) {
                        continue;
                    }
                    objects.add(objectDoc);
                    parsedObjectNames.add(objectDoc.getName());
                    progress = true;
                } catch (Exception exception) {
                    log.warn("hidden object parse failed {}: {}", guessedUrl, exception.getMessage());
                    parsedObjectUrls.add(guessedUrl);
                }
            }
        }
    }

    /**
     * Собирает имена компонентов, на которые ссылаются поля объектов и методов.
     *
     * @param objects список объектов
     * @param methods список методов
     * @return множество имён компонентов
     */
    private Set<String> collectReferencedComponentNames(List<ObjectDoc> objects, List<MethodDoc> methods) {
        Set<String> names = new LinkedHashSet<>();
        for (var objectDoc : objects) {
            objectDoc.getFields().forEach(field -> names.addAll(ParsingUtils.extractComponentNamesFromRawType(Objects.requireNonNullElse(field.getRawType(), ""))));
        }
        for (var method : methods) {
            method.getParams().forEach(field -> names.addAll(ParsingUtils.extractComponentNamesFromRawType(Objects.requireNonNullElse(field.getRawType(), ""))));
            method.getBody().forEach(field -> names.addAll(ParsingUtils.extractComponentNamesFromRawType(Objects.requireNonNullElse(field.getRawType(), ""))));
            method.getResult().forEach(field -> names.addAll(ParsingUtils.extractComponentNamesFromRawType(Objects.requireNonNullElse(field.getRawType(), ""))));
        }
        names.removeIf(Objects::isNull);
        return names;
    }

    /**
     * Извлекает описание и поля объекта из строк документации.
     *
     * @param name имя объекта
     * @param lines строки документации
     * @return извлечённое описание и список полей объекта
     */
    private ExtractedObject extractObject(String name, List<String> lines) {
        var trimmed = ParsingUtils.trimLinesFromHeading(lines, name);
        var descriptionLines = new ArrayList<String>();
        var fieldLines = new ArrayList<String>();

        var index = 0;
        while (index < trimmed.size()) {
            var line = trimmed.get(index);
            if (ParsingUtils.OBJECT_SECTION_STOP_TITLES.contains(line)) {
                break;
            }
            var next = index + 1 < trimmed.size() ? trimmed.get(index + 1) : "";
            if (ParsingUtils.looksLikeFieldName(line) && ParsingUtils.looksLikeTypeLine(next)) {
                fieldLines.addAll(trimmed.subList(index, trimmed.size()));
                break;
            }
            descriptionLines.add(line);
            index++;
        }

        var filteredFieldLines = new ArrayList<String>();
        for (var line : fieldLines) {
            if (ParsingUtils.OBJECT_SECTION_STOP_TITLES.contains(line) || Set.of("Пример", "Пример объекта", "JSON", "Скопировать").contains(line)) {
                break;
            }
            filteredFieldLines.add(line);
        }

        return new ExtractedObject(ParsingUtils.cleanupMultilineDescription(descriptionLines), fieldDocParser.parse(filteredFieldLines));
    }

    /**
     * Вычисляет оценку качества результата парсинга по количеству найденных полей.
     *
     * @param params список параметров
     * @param body список полей тела запроса
     * @param result список полей результата
     * @return числовая оценка результата парсинга
     */
    private int score(List<FieldDoc> params, List<FieldDoc> body, List<FieldDoc> result) {
        return (params.size() + body.size() + result.size()) * 1000 + result.size();
    }

    /**
     * Контейнер для ссылок на методы и объекты.
     *
     * @param methodUrls список URL методов
     * @param objectUrls список URL объектов
     */
    public record RootLinks(List<String> methodUrls, List<String> objectUrls) {
    }

    /**
     * Контейнер для результата извлечения объекта из документации.
     *
     * @param description описание объекта
     * @param fields список полей объекта
     */
    private record ExtractedObject(String description, List<FieldDoc> fields) {
    }
}
