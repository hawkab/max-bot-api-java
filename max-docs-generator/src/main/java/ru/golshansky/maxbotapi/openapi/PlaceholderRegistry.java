package ru.golshansky.maxbotapi.openapi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ru.golshansky.maxbotapi.support.ParsingUtils;

/**
 * Реестр предопределённых схем (placeholder-ов) для OpenAPI.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class PlaceholderRegistry {

    /**
     * Возвращает набор placeholder-схем для компонентов OpenAPI.
     *
     * @param rootUrl корневой URL документации
     * @return карта схем компонентов
     */
    public Map<String, Object> placeholders(String rootUrl) {
        var placeholders = new LinkedHashMap<String, Object>();
        placeholders.put("Image", schema(
            ParsingUtils.appendSourceLink("Иконка чата", rootUrl + "/objects/Chat", "Chat"),
            mapOf("url", mapOf("type", "string", "description", "URL изображения"))
        ));
        placeholders.put("PhotoAttachmentPayload", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/methods/GET/videos/-videoToken-", "GET /videos/{videoToken}"),
            mapOf("url", mapOf("type", "string", "description", "URL изображения"))
        ));
        placeholders.put("PhotoAttachmentRequestPayload", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/methods/PATCH/chats/-chatId-", "PATCH /chats/{chatId}"),
            mapOf("url", mapOf("type", "string", "description", "URL изображения"))
        ));
        placeholders.put("Recipient", schema(
            ParsingUtils.appendSourceLink("Получатель сообщения. Может быть пользователем или чатом", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "chat_id", mapOf("type", "integer", "nullable", true, "description", "ID чата"),
                "chat_type", mapOf("type", "string", "description", "Тип чата получателя"),
                "user_id", mapOf("type", "integer", "nullable", true, "description", "ID пользователя-получателя")
            ),
            List.of("chat_type")
        ));
        placeholders.put("LinkedMessage", schema(
            ParsingUtils.appendSourceLink("Пересланное или ответное сообщение", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип связи сообщения", "enum", List.of("forward", "reply")),
                "sender", mapOf("$ref", "#/components/schemas/User", "nullable", true, "description", "Отправитель связанного сообщения"),
                "chat_id", mapOf("type", "integer", "nullable", true, "description", "ID чата, откуда пришло связанное сообщение"),
                "message", mapOf("$ref", "#/components/schemas/MessageBody", "description", "Тело связанного сообщения")
            ),
            List.of("type", "message")
        ));
        placeholders.put("MessageBody", schema(
            ParsingUtils.appendSourceLink("Содержимое сообщения. Текст + вложения. Может быть null, если сообщение содержит только пересланное сообщение", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "mid", mapOf("type", "string", "description", "ID сообщения"),
                "seq", mapOf("type", "integer", "description", "Порядковый номер сообщения"),
                "text", mapOf("type", "string", "nullable", true, "description", "Текст сообщения"),
                "attachments", mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/Attachment"), "nullable", true, "description", "Вложения сообщения. Могут быть одним из типов Attachment"),
                "markup", mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/MarkupElement"), "nullable", true, "description", "Разметка текста сообщения")
            ),
            List.of("mid", "seq")
        ));
        placeholders.put("MessageStat", schema(
            ParsingUtils.appendSourceLink("Статистика сообщения. Возвращается только для постов в каналах", rootUrl + "/objects/Message", "Message"),
            mapOf("views", mapOf("type", "integer", "description", "Количество просмотров сообщения")),
            List.of("views")
        ));
        placeholders.put("AttachmentPayload", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "photo_id", mapOf("type", "integer", "format", "int64", "nullable", true, "description", "Уникальный ID изображения"),
                "token", mapOf("type", "string", "nullable", true, "description", "Токен вложения"),
                "url", mapOf("type", "string", "nullable", true, "description", "URL изображения или медиафайла"),
                "filename", mapOf("type", "string", "nullable", true, "description", "Имя файла"),
                "size", mapOf("type", "integer", "nullable", true, "description", "Размер файла"),
                "code", mapOf("type", "string", "nullable", true, "description", "Код стикера")
            )
        ));
        placeholders.put("AttachmentRequestPayload", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/NewMessageBody", "NewMessageBody"),
            mapOf(
                "token", mapOf("type", "string", "nullable", true, "description", "Токен загруженного медиафайла"),
                "url", mapOf("type", "string", "nullable", true, "description", "URL изображения")
            )
        ));
        placeholders.put("Attachment", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип вложения", "enum", List.of("image", "video", "audio", "file", "sticker", "contact", "inline_keyboard", "share", "location")),
                "payload", mapOf("$ref", "#/components/schemas/AttachmentPayload", "description", "Данные вложения"),
                "filename", mapOf("type", "string", "nullable", true, "description", "Имя файла"),
                "size", mapOf("type", "integer", "nullable", true, "description", "Размер файла"),
                "width", mapOf("type", "integer", "nullable", true, "description", "Ширина медиа"),
                "height", mapOf("type", "integer", "nullable", true, "description", "Высота медиа"),
                "duration", mapOf("type", "integer", "nullable", true, "description", "Длительность медиа в секундах"),
                "thumbnail", mapOf("type", "string", "nullable", true, "description", "Миниатюра видео"),
                "title", mapOf("type", "string", "nullable", true, "description", "Заголовок вложения"),
                "description", mapOf("type", "string", "nullable", true, "description", "Описание вложения"),
                "image_url", mapOf("type", "string", "nullable", true, "description", "URL изображения для share-вложения"),
                "latitude", mapOf("type", "number", "nullable", true, "description", "Широта"),
                "longitude", mapOf("type", "number", "nullable", true, "description", "Долгота")
            ),
            List.of("type")
        ));
        placeholders.put("AttachmentRequest", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/NewMessageBody", "NewMessageBody"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип вложения", "enum", List.of("image", "video", "audio", "file", "sticker", "contact", "inline_keyboard", "share", "location")),
                "payload", mapOf("$ref", "#/components/schemas/AttachmentRequestPayload", "description", "Данные вложения")
            ),
            List.of("type")
        ));
        placeholders.put("MarkupElement", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/Message", "Message"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип элемента разметки"),
                "from", mapOf("type", "integer", "description", "Начальная позиция"),
                "length", mapOf("type", "integer", "description", "Длина диапазона"),
                "user_link", mapOf("type", "string", "nullable", true, "description", "Ссылка на пользователя"),
                "user_id", mapOf("type", "integer", "nullable", true, "description", "ID пользователя")
            ),
            List.of("type", "from", "length")
        ));
        placeholders.put("NewMessageLink", schema(
            ParsingUtils.appendSourceLink("Ссылка на сообщение", rootUrl + "/objects/NewMessageBody", "NewMessageBody"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип связи сообщения", "enum", List.of("forward", "reply")),
                "mid", mapOf("type", "string", "nullable", true, "description", "ID сообщения"),
                "chat_id", mapOf("type", "integer", "nullable", true, "description", "ID чата")
            )
        ));
        placeholders.put("BotCommand", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/objects/BotInfo", "BotInfo"),
            mapOf(
                "name", mapOf("type", "string", "description", "Имя команды"),
                "description", mapOf("type", "string", "nullable", true, "description", "Описание команды")
            ),
            List.of("name")
        ));
        placeholders.put("ChatAdmin", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/methods/POST/chats/-chatId-/members/admins", "POST /chats/{chatId}/members/admins"),
            mapOf(
                "user_id", mapOf("type", "integer", "description", "ID пользователя"),
                "permissions", mapOf("type", "array", "items", mapOf("$ref", "#/components/schemas/ChatAdminPermission"), "nullable", true, "description", "Права администратора"),
                "alias", mapOf("type", "string", "nullable", true, "description", "Псевдоним администратора")
            ),
            List.of("user_id")
        ));
        placeholders.put("FailedUserDetails", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/methods/POST/chats/-chatId-/members", "POST /chats/{chatId}/members"),
            mapOf(
                "user_id", mapOf("type", "integer", "nullable", true, "description", "ID пользователя"),
                "message", mapOf("type", "string", "nullable", true, "description", "Текст ошибки"),
                "code", mapOf("type", "string", "nullable", true, "description", "Код ошибки")
            )
        ));
        placeholders.put("Subscription", schema(
            ParsingUtils.appendSourceLink("", rootUrl + "/methods/GET/subscriptions", "GET /subscriptions"),
            mapOf(
                "url", mapOf("type", "string", "description", "Webhook URL"),
                "update_types", mapOf("type", "array", "items", mapOf("type", "string"), "nullable", true, "description", "Типы обновлений"),
                "secret", mapOf("type", "string", "nullable", true, "description", "Секрет Webhook")
            ),
            List.of("url")
        ));
        placeholders.put("VideoUrls", schema(
            ParsingUtils.appendSourceLink("URL-ы для скачивания или воспроизведения видео. Может быть null, если видео недоступно", rootUrl + "/methods/GET/videos/-videoToken-", "GET /videos/{videoToken}"),
            mapOf(
                "mp4", mapOf("type", "string", "nullable", true, "description", "Ссылка на MP4"),
                "hls", mapOf("type", "string", "nullable", true, "description", "Ссылка на HLS"),
                "download", mapOf("type", "string", "nullable", true, "description", "Ссылка на скачивание")
            )
        ));
        placeholders.putAll(buildAttachmentPlaceholderSchemas(rootUrl));
        return placeholders;
    }

    /**
     * Возвращает расширенные placeholder-схемы вложений.
     *
     * @param rootUrl корневой URL документации
     * @return карта placeholder-схем
     */
    private Map<String, Object> buildAttachmentPlaceholderSchemas(String rootUrl) {
        var newMessageBodyUrl = rootUrl + "/objects/NewMessageBody";
        var messageUrl = rootUrl + "/objects/Message";
        var placeholders = new LinkedHashMap<String, Object>();
        placeholders.put("AttachmentRequestPayload", schema(
            ParsingUtils.appendSourceLink("Объединённая best-effort схема payload для AttachmentRequest. Поля объединены по всем типам вложений, которые переключаются в UI документации.", newMessageBodyUrl, "NewMessageBody"),
            mapOf(
                "token", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Токен загруженного медиафайла"), List.of("type in {video, audio, file}", "type=image при отправке изображения через upload token"), null),
                "url", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "URL изображения"), "type=image при отправке изображения по URL", null),
                "photos", annotateSchemaConditions(mapOf("type", "object", "nullable", true, "description", "Набор вариантов изображения по ключам"), "type=image при отправке изображения набором вариантов", null),
                "name", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Имя контакта"), "type=contact при передаче карточки контакта в явном виде", null),
                "contact_id", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "ID контакта"), "type=contact при передаче контакта по идентификатору", null),
                "vcf_info", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "VCF-данные контакта"), "type=contact при передаче контакта как vCard", null),
                "vcf_phone", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Телефон контакта"), "type=contact при передаче контакта как vCard/телефонной карточки", null),
                "code", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Код стикера"), "type=sticker", null),
                "buttons", annotateSchemaConditions(mapOf("type", "array", "items", mapOf("type", "array", "items", mapOf("type", "object")), "nullable", true, "description", "Кнопки inline-клавиатуры"), "type=inline_keyboard", null)
            )
        ));
        placeholders.put("AttachmentPayload", schema(
            ParsingUtils.appendSourceLink("Объединённая best-effort схема payload для Attachment. Поля объединены по всем типам вложений, которые переключаются в UI документации.", messageUrl, "Message"),
            mapOf(
                "photo_id", annotateSchemaConditions(mapOf("type", "integer", "format", "int64", "nullable", true, "description", "Уникальный ID изображения"), "type=image", null),
                "token", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Токен вложения"), null, "type in {image, video, audio, file} при наличии серверного media token"),
                "url", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "URL изображения или медиафайла"), "type=image", null),
                "filename", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Имя файла"), "type=file", null),
                "size", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "Размер файла"), "type=file", null),
                "code", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Код стикера"), "type=sticker", null)
            )
        ));
        placeholders.put("AttachmentRequest", schema(
            ParsingUtils.appendSourceLink("Вложение при отправке сообщения. Тип определяет, какие поля payload или координаты должны быть заполнены.", newMessageBodyUrl, "NewMessageBody"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип вложения", "enum", List.of("image", "video", "audio", "file", "sticker", "contact", "inline_keyboard", "share", "location")),
                "payload", mapOf("$ref", "#/components/schemas/AttachmentRequestPayload", "description", "Данные вложения. Набор обязательных полей зависит от type."),
                "latitude", annotateSchemaConditions(mapOf("type", "number", "nullable", true, "description", "Широта"), "type=location", null),
                "longitude", annotateSchemaConditions(mapOf("type", "number", "nullable", true, "description", "Долгота"), "type=location", null)
            ),
            List.of("type")
        ));
        castMap(placeholders.get("AttachmentRequest")).put("x-required-by-type", mapOf(
            "image", List.of("payload.url | payload.token | payload.photos"),
            "video", List.of("payload.token"),
            "audio", List.of("payload.token"),
            "file", List.of("payload.token"),
            "sticker", List.of("payload.code"),
            "contact", List.of("payload.contact_id | payload.name + payload.vcf_info | payload.name + payload.vcf_phone"),
            "inline_keyboard", List.of("payload.buttons"),
            "share", List.of(),
            "location", List.of("latitude", "longitude")
        ));
        placeholders.put("Attachment", schema(
            ParsingUtils.appendSourceLink("Вложение сообщения. Тип определяет, какие поля payload и дополнительные атрибуты обычно присутствуют.", messageUrl, "Message"),
            mapOf(
                "type", mapOf("type", "string", "description", "Тип вложения", "enum", List.of("image", "video", "audio", "file", "sticker", "contact", "inline_keyboard", "share", "location")),
                "payload", mapOf("$ref", "#/components/schemas/AttachmentPayload", "description", "Данные вложения. Набор обязательных полей зависит от type."),
                "filename", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Имя файла"), "type=file", null),
                "size", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "Размер файла"), "type=file", null),
                "width", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "Ширина медиа"), "type in {image, video}", null),
                "height", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "Высота медиа"), "type in {image, video}", null),
                "duration", annotateSchemaConditions(mapOf("type", "integer", "nullable", true, "description", "Длительность медиа в секундах"), "type in {video, audio}", null),
                "thumbnail", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Миниатюра видео"), "type=video", null),
                "title", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Заголовок вложения"), "type=share", null),
                "description", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "Описание вложения"), "type=share", null),
                "image_url", annotateSchemaConditions(mapOf("type", "string", "nullable", true, "description", "URL изображения для share-вложения"), "type=share", null),
                "latitude", annotateSchemaConditions(mapOf("type", "number", "nullable", true, "description", "Широта"), "type=location", null),
                "longitude", annotateSchemaConditions(mapOf("type", "number", "nullable", true, "description", "Долгота"), "type=location", null)
            ),
            List.of("type")
        ));
        castMap(placeholders.get("Attachment")).put("x-required-by-type", mapOf(
            "image", List.of("payload.photo_id", "payload.url"),
            "video", List.of("duration", "thumbnail"),
            "audio", List.of("duration"),
            "file", List.of("filename", "size"),
            "sticker", List.of("payload.code"),
            "contact", List.of(),
            "inline_keyboard", List.of(),
            "share", List.of("title", "description", "image_url"),
            "location", List.of("latitude", "longitude")
        ));
        return placeholders;
    }

    /**
     * Добавляет в схему условия использования и обязательности.
     *
     * @param schema исходная схема
     * @param requiredWhen условия обязательности
     * @param usedWhen условия использования
     * @return аннотированная схема
     */
    private Map<String, Object> annotateSchemaConditions(Map<String, Object> schema, Object requiredWhen, Object usedWhen) {
        var annotated = new LinkedHashMap<String, Object>(schema);
        var description = String.valueOf(annotated.getOrDefault("description", "")).trim();
        var notes = new java.util.ArrayList<String>();
        if (requiredWhen != null) {
            annotated.put("x-required-when", requiredWhen);
            notes.add("Обязательно, когда " + normalizeCondition(requiredWhen) + ".");
        }
        if (usedWhen != null) {
            annotated.put("x-used-when", usedWhen);
            notes.add("Используется, когда " + normalizeCondition(usedWhen) + ".");
        }
        if (!notes.isEmpty()) {
            annotated.put("description", (description + " " + String.join(" ", notes)).trim());
        }
        return annotated;
    }

    private String normalizeCondition(Object value) {
        if (value instanceof List<?> list) {
            return list.stream().map(String::valueOf).reduce((left, right) -> left + "; " + right).orElse("");
        }
        return String.valueOf(value);
    }

    /**
     * Создаёт схему объекта с описанием и набором свойств.
     *
     * @param description описание схемы
     * @param properties свойства объекта
     * @return схема объекта
     */
    private Map<String, Object> schema(String description, Map<String, Object> properties) {
        return schema(description, properties, List.of());
    }

    /**
     * Создаёт схему объекта с описанием, свойствами и обязательными полями.
     *
     * @param description описание схемы
     * @param properties свойства объекта
     * @param required список обязательных полей
     * @return схема объекта
     */
    private Map<String, Object> schema(String description, Map<String, Object> properties, List<String> required) {
        var schema = new LinkedHashMap<String, Object>();
        schema.put("type", "object");
        schema.put("description", description);
        schema.put("properties", properties);
        if (!required.isEmpty()) {
            schema.put("required", required);
        }
        return schema;
    }

    /**
     * Создаёт Map из набора ключ-значение.
     *
     * @param values пары ключ-значение
     * @return Map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> castMap(Object value) {
        return (Map<String, Object>) value;
    }

    private static Map<String, Object> mapOf(Object... values) {
        var map = new LinkedHashMap<String, Object>();
        for (var index = 0; index < values.length; index += 2) {
            map.put(String.valueOf(values[index]), values[index + 1]);
        }
        return map;
    }
}
