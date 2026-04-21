package ru.golshansky.maxbotapi.openapi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import ru.golshansky.maxbotapi.domain.FieldDoc;
import ru.golshansky.maxbotapi.domain.MethodDoc;
import ru.golshansky.maxbotapi.domain.ObjectDoc;
import ru.golshansky.maxbotapi.support.ParsingUtils;

/**
 * Построитель OpenAPI-спецификации на основе разобранной документации.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class OpenApiBuilder {

    private static final Set<String> ALWAYS_OVERRIDE = Set.of(
        "Image",
        "PhotoAttachmentPayload",
        "PhotoAttachmentRequestPayload",
        "Recipient",
        "LinkedMessage",
        "MessageBody",
        "MessageStat",
        "AttachmentPayload",
        "AttachmentRequestPayload",
        "Attachment",
        "AttachmentRequest",
        "MarkupElement",
        "NewMessageLink",
        "BotCommand",
        "ChatAdmin",
        "FailedUserDetails",
        "Subscription",
        "VideoUrls"
    );

    private final PlaceholderRegistry placeholderRegistry;

    public OpenApiBuilder(PlaceholderRegistry placeholderRegistry) {
        this.placeholderRegistry = placeholderRegistry;
    }

    /**
     * Формирует OpenAPI-описание.
     *
     * @param rootUrl корневой URL документации
     * @param methods список методов API
     * @param objects список объектов API
     * @return структура OpenAPI в виде Map
     */
    public Map<String, Object> build(String rootUrl, List<MethodDoc> methods, List<ObjectDoc> objects) {
        var componentsSchemas = new LinkedHashMap<String, Object>();
        var unknownComponentNames = new LinkedHashSet<String>();
        for (var objectDoc : objects) {
            componentsSchemas.put(objectDoc.getName(), objectToSchema(objectDoc, unknownComponentNames));
        }

        var paths = new LinkedHashMap<String, Object>();
        methods.stream()
            .sorted(Comparator.comparing(MethodDoc::getPath).thenComparing(MethodDoc::getMethod))
            .forEach(method -> buildOperation(paths, method, objects, unknownComponentNames));

        var predefinedPlaceholders = placeholderRegistry.placeholders(rootUrl);
        for (var name : new ArrayList<>(unknownComponentNames)) {
            componentsSchemas.putIfAbsent(name, predefinedPlaceholders.getOrDefault(name, genericPlaceholder(name)));
        }

        resolveMissingRefs(paths, componentsSchemas, predefinedPlaceholders);
        applyOverrides(componentsSchemas, predefinedPlaceholders);

        return mapOf(
            "openapi", "3.0.3",
            "info", mapOf(
                "title", "Методы API MAX",
                "version", "generated",
                "description", "Сгенерировано " + ParsingUtils.currentGeneratedAt() + " на основе официальной документации [API MAX](https://dev.max.ru/docs-api)"
            ),
            "servers", List.of(mapOf("url", ParsingUtils.BASE_SERVER_URL)),
            "security", List.of(mapOf("AccessTokenAuth", List.of())),
            "paths", paths,
            "components", mapOf(
                "securitySchemes", mapOf(
                    "AccessTokenAuth", mapOf(
                        "type", "apiKey",
                        "in", "header",
                        "name", "Authorization",
                        "description", "Access token MAX bot API"
                    )
                ),
                "schemas", componentsSchemas
            )
        );
    }

    /**
     * Добавляет описание операции (endpoint) в paths.
     *
     * @param paths структура paths
     * @param method описание метода
     * @param objects список объектов
     * @param unknownComponentNames множество неизвестных компонентов
     */
    private void buildOperation(Map<String, Object> paths, MethodDoc method, List<ObjectDoc> objects, Set<String> unknownComponentNames) {
        var pathItem = castMap(paths.computeIfAbsent(method.getPath(), ignored -> new LinkedHashMap<>()));
        var operation = new LinkedHashMap<String, Object>();
        operation.put("tags", List.of(method.getTag()));
        operation.put("summary", method.getSummary());
        if (method.getDescription() != null && !method.getDescription().isBlank()) {
            operation.put("description", method.getDescription());
        }
        operation.put("operationId", makeOperationId(method.getMethod(), method.getPath()));
        operation.put("security", List.of(mapOf("AccessTokenAuth", List.of())));
        operation.put("x-source-url", method.getUrl());

        var parameters = new ArrayList<Map<String, Object>>();
        for (var field : method.getParams()) {
            var parameterType = method.getPath().contains("{" + field.getName() + "}") ? "path" : "query";
            var parameter = new LinkedHashMap<String, Object>();
            parameter.put("name", field.getName());
            parameter.put("in", parameterType);
            parameter.put("required", "path".equals(parameterType) || isRequiredField(field));
            var description = ParsingUtils.renderFieldDescription(field);
            if (!description.isBlank()) {
                parameter.put("description", description);
            }
            parameter.put("schema", fieldToSchema(field, unknownComponentNames));
            parameters.add(parameter);
        }
        if (!parameters.isEmpty()) {
            operation.put("parameters", parameters);
        }

        if (!method.getBody().isEmpty()) {
            operation.put("requestBody", mapOf(
                "required", method.getBody().stream().anyMatch(this::isRequiredField),
                "content", mapOf("application/json", mapOf("schema", objectLikeSchema(method.getBody(), objects, unknownComponentNames)))
            ));
        }

        operation.put("responses", mapOf(
            "200", mapOf(
                "description", method.getSummary(),
                "content", mapOf("application/json", mapOf("schema", objectLikeSchema(method.getResult(), objects, unknownComponentNames)))
            )
        ));

        pathItem.put(method.getMethod().toLowerCase(Locale.ROOT), operation);
    }

    /**
     * Формирует схему объекта по списку полей или ссылку на существующий объект.
     *
     * @param fields список полей
     * @param objects список объектов
     * @param unknownComponentNames множество неизвестных компонентов
     * @return схема OpenAPI
     */
    private Map<String, Object> objectLikeSchema(List<FieldDoc> fields, List<ObjectDoc> objects, Set<String> unknownComponentNames) {
        var matchingObjectName = findMatchingObjectName(fields, objects);
        if (matchingObjectName != null) {
            return mapOf("$ref", "#/components/schemas/" + matchingObjectName);
        }
        var schema = new LinkedHashMap<String, Object>();
        schema.put("type", "object");
        var properties = new LinkedHashMap<String, Object>();
        var required = new ArrayList<String>();
        for (var field : fields) {
            var fieldSchema = fieldToSchema(field, unknownComponentNames);
            var description = ParsingUtils.renderFieldDescription(field);
            if (!description.isBlank()) {
                fieldSchema.putIfAbsent("description", description);
            }
            properties.put(field.getName(), fieldSchema);
            if (isRequiredField(field)) {
                required.add(field.getName());
            }
        }
        schema.put("properties", properties);
        if (!required.isEmpty()) {
            schema.put("required", required);
        }
        return schema;
    }

    /**
     * Ищет имя объекта, совпадающего по сигнатуре полей.
     *
     * @param fields список полей
     * @param objects список объектов
     * @return имя объекта или null
     */
    private String findMatchingObjectName(List<FieldDoc> fields, List<ObjectDoc> objects) {
        if (fields.isEmpty()) {
            return null;
        }
        var signature = fields.stream().map(this::fieldSignature).toList();
        for (var objectDoc : objects) {
            if (objectDoc.getFields().stream().map(this::fieldSignature).toList().equals(signature)) {
                return objectDoc.getName();
            }
        }
        return null;
    }

    /**
     * Формирует сигнатуру поля для сравнения.
     *
     * @param field поле
     * @return список характеристик поля
     */
    private List<Object> fieldSignature(FieldDoc field) {
        return List.of(
            field.getName(),
            ParsingUtils.normalizeSpaces(field.getRawType() == null ? "" : field.getRawType()),
            field.isOptional(),
            field.isNullable()
        );
    }

    /**
     * Преобразует объект документации в OpenAPI-схему.
     *
     * @param objectDoc объект документации
     * @param unknownComponentNames множество неизвестных компонентов
     * @return схема OpenAPI
     */
    private Map<String, Object> objectToSchema(ObjectDoc objectDoc, Set<String> unknownComponentNames) {
        var schema = new LinkedHashMap<String, Object>();
        schema.put("type", "object");
        schema.put("description", ParsingUtils.appendSourceLink(objectDoc.getDescription(), objectDoc.getUrl(), objectDoc.getName()));

        var properties = new LinkedHashMap<String, Object>();
        var required = new ArrayList<String>();
        for (var field : objectDoc.getFields()) {
            var fieldSchema = fieldToSchema(field, unknownComponentNames);
            var description = ParsingUtils.renderFieldDescription(field);
            if (!description.isBlank()) {
                fieldSchema.putIfAbsent("description", description);
            }
            properties.put(field.getName(), fieldSchema);
            if (isRequiredField(field)) {
                required.add(field.getName());
            }
        }
        schema.put("properties", properties);
        if (!required.isEmpty()) {
            schema.put("required", required);
        }
        schema.put("x-source-url", objectDoc.getUrl());
        return schema;
    }

    /**
     * Преобразует поле в OpenAPI-схему.
     *
     * @param field поле
     * @param unknownComponentNames множество неизвестных компонентов
     * @return схема поля
     */
    private Map<String, Object> fieldToSchema(FieldDoc field, Set<String> unknownComponentNames) {
        var raw = ParsingUtils.normalizeSpaces(field.getRawType() == null ? "string" : field.getRawType());
        var schema = typeLineToSchema(raw, unknownComponentNames);
        if (field.isNullable()) {
            schema.put("nullable", true);
        }
        if (!field.getEnumValues().isEmpty() && (raw.startsWith("enum ") || "string".equals(schema.get("type")))) {
            schema.put("enum", field.getEnumValues());
            schema.putIfAbsent("type", "string");
        }
        if (field.getDefaultValue() != null) {
            schema.put("default", coerceScalar(field.getDefaultValue()));
        }
        if (field.getPattern() != null && "string".equals(schema.get("type"))) {
            schema.put("pattern", field.getPattern());
        }
        if (field.getRangeHint() != null) {
            schema.put("x-range-hint", field.getRangeHint());
        }
        return schema;
    }

    /**
     * Преобразует строковое описание типа в OpenAPI-схему.
     *
     * @param rawType строковое описание типа
     * @param unknownComponentNames множество неизвестных компонентов
     * @return схема типа
     */
    private Map<String, Object> typeLineToSchema(String rawType, Set<String> unknownComponentNames) {
        var base = rawType.replaceAll("\\bNullable\\b", "")
            .replaceAll("\\boptional\\b", "")
            .replaceAll("\\[[^]]+]", "")
            .trim();

        if (base.startsWith("enum ")) {
            return mapOf("type", "string");
        }
        if (base.startsWith("object ")) {
            var refName = base.substring("object ".length()).trim();
            if (!refName.isBlank() && !"object".equals(refName) && !ParsingUtils.IGNORED_PSEUDO_COMPONENTS.contains(refName)) {
                unknownComponentNames.add(refName);
                return mapOf("$ref", "#/components/schemas/" + refName);
            }
            return mapOf("type", "object");
        }

        var first = base.isBlank() ? "string" : base.split(" ")[0];
        if ("ID".equals(first)) {
            return mapOf("type", "integer");
        }
        if ("URL".equals(first)) {
            return mapOf("type", "string");
        }
        if (first.endsWith("[]")) {
            var itemName = first.substring(0, first.length() - 2);
            return mapOf("type", "array", "items", nameToSchema(itemName, unknownComponentNames));
        }
        if (Set.of("string", "boolean", "number", "object").contains(first)) {
            return mapOf("type", first);
        }
        if ("integer".equals(first)) {
            var schema = new LinkedHashMap<String, Object>();
            schema.put("type", "integer");
            if (base.contains("<int64>")) {
                schema.put("format", "int64");
            }
            return schema;
        }
        if ("apiKey".equals(first)) {
            return mapOf("type", "string");
        }
        return nameToSchema(first, unknownComponentNames);
    }

    /**
     * Преобразует имя типа в OpenAPI-схему или ссылку на компонент.
     *
     * @param name имя типа
     * @param unknownComponentNames множество неизвестных компонентов
     * @return схема типа
     */
    private Map<String, Object> nameToSchema(String name, Set<String> unknownComponentNames) {
        if (name.endsWith("[]")) {
            return mapOf("type", "array", "items", nameToSchema(name.substring(0, name.length() - 2), unknownComponentNames));
        }
        if ("ID".equals(name)) {
            return mapOf("type", "integer");
        }
        if ("URL".equals(name)) {
            return mapOf("type", "string");
        }
        var lowered = name.toLowerCase(Locale.ROOT);
        if (ParsingUtils.PRIMITIVE_TYPES.contains(lowered)) {
            return mapOf("type", lowered);
        }
        if ("string[]".equals(lowered)) {
            return mapOf("type", "array", "items", mapOf("type", "string"));
        }
        if (ParsingUtils.IGNORED_PSEUDO_COMPONENTS.contains(name)) {
            return mapOf("type", "object");
        }
        unknownComponentNames.add(name);
        return mapOf("$ref", "#/components/schemas/" + name);
    }

    /**
     * Определяет, является ли поле обязательным.
     *
     * @param field поле
     * @return true, если поле обязательно
     */
    private boolean isRequiredField(FieldDoc field) {
        return !field.isOptional() && !field.isNullable() && field.getDefaultValue() == null;
    }

    /**
     * Преобразует строковое значение в примитивный тип.
     *
     * @param value значение
     * @return преобразованное значение
     */
    private Object coerceScalar(String value) {
        var raw = value.trim().replace("`", "");
        if ("true".equalsIgnoreCase(raw)) {
            return true;
        }
        if ("false".equalsIgnoreCase(raw)) {
            return false;
        }
        if ("null".equalsIgnoreCase(raw)) {
            return null;
        }
        if (raw.matches("-?\\d+")) {
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException ignored) {
                return raw;
            }
        }
        return raw;
    }

    /**
     * Генерирует operationId для метода.
     *
     * @param method HTTP-метод
     * @param path путь
     * @return operationId
     */
    private String makeOperationId(String method, String path) {
        var cleaned = path.replaceAll("^/+", "").replaceAll("/+$", "");
        if (cleaned.isBlank()) {
            cleaned = "root";
        }
        cleaned = cleaned.replaceAll("\\{([^}]+)}", "by_$1");
        cleaned = cleaned.replaceAll("[^A-Za-z0-9_]+", "_");
        cleaned = cleaned.replaceAll("_+", "_").replaceAll("^_+|_+$", "");
        return method.toLowerCase(Locale.ROOT) + "_" + cleaned;
    }

    /**
     * Применяет переопределения схем компонентов.
     *
     * @param componentsSchemas схемы компонентов
     * @param predefinedPlaceholders предопределённые placeholder-ы
     */
    private void applyOverrides(Map<String, Object> componentsSchemas, Map<String, Object> predefinedPlaceholders) {
        for (var name : ALWAYS_OVERRIDE) {
            if (predefinedPlaceholders.containsKey(name) && isGeneratedSchema(componentsSchemas.get(name))) {
                componentsSchemas.put(name, new LinkedHashMap<>(castMap(predefinedPlaceholders.get(name))));
            }
        }

        ensureProp(componentsSchemas, "Message", "recipient",
            mapOf("$ref", "#/components/schemas/Recipient", "description", "Получатель сообщения. Может быть пользователем или чатом"));
        ensureProp(componentsSchemas, "Message", "link",
            mapOf("$ref", "#/components/schemas/LinkedMessage", "nullable", true, "description", "Пересланное или ответное сообщение"));
        ensureProp(componentsSchemas, "Message", "body",
            mapOf("$ref", "#/components/schemas/MessageBody", "description", "Содержимое сообщения. Текст + вложения. Может быть null, если сообщение содержит только пересланное сообщение"));
        ensureProp(componentsSchemas, "Message", "stat",
            mapOf("$ref", "#/components/schemas/MessageStat", "nullable", true, "description", "Статистика сообщения. Возвращается только для постов в каналах"));
        ensureProp(componentsSchemas, "Chat", "icon",
            mapOf("$ref", "#/components/schemas/Image", "nullable", true, "description", "Иконка чата"));
        ensureProp(componentsSchemas, "MessageBody", "attachments",
            mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/Attachment"), "nullable", true,
                "description", "Вложения сообщения. Могут быть одним из типов Attachment"));
        ensureProp(componentsSchemas, "MessageBody", "markup",
            mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/MarkupElement"), "nullable", true,
                "description", "Разметка текста сообщения"));
        ensureProp(componentsSchemas, "NewMessageBody", "attachments",
            mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/AttachmentRequest"), "nullable", true,
                "description", "Вложения сообщения. Если пусто, все вложения будут удалены"));
        ensureProp(componentsSchemas, "LinkedMessage", "message",
            mapOf("$ref", "#/components/schemas/MessageBody", "description", "Тело связанного сообщения"));

        resolveMissingRefs(Map.of(), componentsSchemas, predefinedPlaceholders);
    }

    /**
     * Проверяет, является ли схема сгенерированным placeholder-ом.
     *
     * @param schema схема
     * @return true, если схема восстановлена эвристически
     */
    private boolean isGeneratedSchema(Object schema) {
        if (!(schema instanceof Map<?, ?> map) || map.isEmpty()) {
            return true;
        }
        if (map.get("x-source-url") != null) {
            return false;
        }
        var description = map.containsKey("description") ? String.valueOf(map.get("description")) : "";
        return description.contains("Частично восстановленная схема")
            || description.contains("Автоматически добавленный placeholder")
            || Boolean.TRUE.equals(map.get("additionalProperties"));
    }

    /**
     * Добавляет или обновляет свойство схемы компонента.
     *
     * @param componentsSchemas схемы компонентов
     * @param schemaName имя схемы
     * @param propName имя свойства
     * @param value значение свойства
     */
    private void ensureProp(Map<String, Object> componentsSchemas, String schemaName, String propName, Map<String, Object> value) {
        var schema = castMap(componentsSchemas.computeIfAbsent(schemaName, ignored -> mapOf("type", "object", "properties", new LinkedHashMap<String, Object>())));
        schema.putIfAbsent("type", "object");
        var props = castMap(schema.computeIfAbsent("properties", ignored -> new LinkedHashMap<String, Object>()));
        props.put(propName, value);
    }

    /**
     * Добавляет отсутствующие схемы компонентов для всех ссылок.
     *
     * @param paths структура paths
     * @param componentsSchemas схемы компонентов
     * @param predefinedPlaceholders предопределённые placeholder-ы
     */
    private void resolveMissingRefs(Map<String, Object> paths, Map<String, Object> componentsSchemas, Map<String, Object> predefinedPlaceholders) {
        while (true) {
            var referenced = new LinkedHashSet<String>();
            extractRefs(paths, referenced);
            extractRefs(componentsSchemas, referenced);
            referenced.removeAll(componentsSchemas.keySet());
            if (referenced.isEmpty()) {
                return;
            }
            for (var name : referenced) {
                componentsSchemas.putIfAbsent(name, predefinedPlaceholders.getOrDefault(name, genericPlaceholder(name)));
            }
        }
    }

    /**
     * Извлекает ссылки на компоненты из структуры.
     *
     * @param value структура данных
     * @param refs множество найденных ссылок
     */
    private void extractRefs(Object value, Set<String> refs) {
        if (value instanceof Map<?, ?> map) {
            var ref = map.get("$ref");
            if (ref instanceof String refValue && refValue.startsWith("#/components/schemas/")) {
                refs.add(refValue.substring("#/components/schemas/".length()));
            }
            for (var inner : map.values()) {
                extractRefs(inner, refs);
            }
            return;
        }
        if (value instanceof Iterable<?> iterable) {
            for (var inner : iterable) {
                extractRefs(inner, refs);
            }
        }
    }

    /**
     * Создаёт placeholder-схему для неизвестного типа.
     *
     * @param name имя типа
     * @return схема placeholder
     */
    private Map<String, Object> genericPlaceholder(String name) {
        return mapOf(
            "type", "object",
            "description", "Автоматически добавленный placeholder для типа " + name + ", найденного в документации.",
            "additionalProperties", true
        );
    }

    /**
     * Приводит объект к Map<String, Object>.
     *
     * @param value значение
     * @return Map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> castMap(Object value) {
        return (Map<String, Object>) value;
    }

    /**
     * Создаёт Map из набора ключ-значение.
     *
     * @param values пары ключ-значение
     * @return Map
     */
    private static Map<String, Object> mapOf(Object... values) {
        var map = new LinkedHashMap<String, Object>();
        for (var index = 0; index < values.length; index += 2) {
            map.put(String.valueOf(values[index]), values[index + 1]);
        }
        return map;
    }
}
