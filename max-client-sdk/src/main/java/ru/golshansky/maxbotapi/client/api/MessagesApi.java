package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.GetMessages200Response;
import ru.golshansky.maxbotapi.client.model.Message;
import ru.golshansky.maxbotapi.client.model.NewMessageBody;
import ru.golshansky.maxbotapi.client.model.PostAnswers200Response;
import ru.golshansky.maxbotapi.client.model.PostMessages200Response;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.15.0")
public class MessagesApi {
    private ApiClient apiClient;

    public MessagesApi() {
        this(new ApiClient());
    }

    public MessagesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Получение сообщений
     * Метод возвращает информацию о сообщении или массив сообщений из чата. Для выполнения запроса нужно указать один из параметров — chat_id или message_ids:  - chat_id — метод возвращает массив сообщений из указанного чата. Сообщения возвращаются в обратном порядке: последние сообщения будут первыми в массиве - message_ids — метод возвращает информацию о запрошенных сообщениях. Можно указать один идентификатор или несколько  Источник описания: [GET /messages](https://dev.max.ru/docs-api/methods/GET/messages)
     * <p><b>200</b> - Получение сообщений
     * @param chatId ID чата, чтобы получить сообщения из определённого чата. Обязательный параметр, если не указан
     * @param messageIds Список ID сообщений, которые нужно получить (через запятую). Обязательный параметр, если не указан chat_id
     * @param from Время начала для запрашиваемых сообщений (в формате Unix timestamp)
     * @param to Время окончания для запрашиваемых сообщений (в формате Unix timestamp)
     * @param count По умолчанию:  50 Максимальное количество сообщений в ответе  Диапазон: &#x60;[1-100]&#x60;
     * @return GetMessages200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMessagesRequestCreation(@jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Object messageIds, @jakarta.annotation.Nullable Integer from, @jakarta.annotation.Nullable Integer to, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "chat_id", chatId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "message_ids", messageIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "to", to));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "count", count));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<GetMessages200Response> localVarReturnType = new ParameterizedTypeReference<GetMessages200Response>() {};
        return apiClient.invokeAPI("/messages", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение сообщений
     * Метод возвращает информацию о сообщении или массив сообщений из чата. Для выполнения запроса нужно указать один из параметров — chat_id или message_ids:  - chat_id — метод возвращает массив сообщений из указанного чата. Сообщения возвращаются в обратном порядке: последние сообщения будут первыми в массиве - message_ids — метод возвращает информацию о запрошенных сообщениях. Можно указать один идентификатор или несколько  Источник описания: [GET /messages](https://dev.max.ru/docs-api/methods/GET/messages)
     * <p><b>200</b> - Получение сообщений
     * @param chatId ID чата, чтобы получить сообщения из определённого чата. Обязательный параметр, если не указан
     * @param messageIds Список ID сообщений, которые нужно получить (через запятую). Обязательный параметр, если не указан chat_id
     * @param from Время начала для запрашиваемых сообщений (в формате Unix timestamp)
     * @param to Время окончания для запрашиваемых сообщений (в формате Unix timestamp)
     * @param count По умолчанию:  50 Максимальное количество сообщений в ответе  Диапазон: &#x60;[1-100]&#x60;
     * @return GetMessages200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetMessages200Response> getMessages(@jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Object messageIds, @jakarta.annotation.Nullable Integer from, @jakarta.annotation.Nullable Integer to, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        ParameterizedTypeReference<GetMessages200Response> localVarReturnType = new ParameterizedTypeReference<GetMessages200Response>() {};
        return getMessagesRequestCreation(chatId, messageIds, from, to, count).bodyToMono(localVarReturnType);
    }

    /**
     * Получение сообщений
     * Метод возвращает информацию о сообщении или массив сообщений из чата. Для выполнения запроса нужно указать один из параметров — chat_id или message_ids:  - chat_id — метод возвращает массив сообщений из указанного чата. Сообщения возвращаются в обратном порядке: последние сообщения будут первыми в массиве - message_ids — метод возвращает информацию о запрошенных сообщениях. Можно указать один идентификатор или несколько  Источник описания: [GET /messages](https://dev.max.ru/docs-api/methods/GET/messages)
     * <p><b>200</b> - Получение сообщений
     * @param chatId ID чата, чтобы получить сообщения из определённого чата. Обязательный параметр, если не указан
     * @param messageIds Список ID сообщений, которые нужно получить (через запятую). Обязательный параметр, если не указан chat_id
     * @param from Время начала для запрашиваемых сообщений (в формате Unix timestamp)
     * @param to Время окончания для запрашиваемых сообщений (в формате Unix timestamp)
     * @param count По умолчанию:  50 Максимальное количество сообщений в ответе  Диапазон: &#x60;[1-100]&#x60;
     * @return ResponseEntity&lt;GetMessages200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetMessages200Response>> getMessagesWithHttpInfo(@jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Object messageIds, @jakarta.annotation.Nullable Integer from, @jakarta.annotation.Nullable Integer to, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        ParameterizedTypeReference<GetMessages200Response> localVarReturnType = new ParameterizedTypeReference<GetMessages200Response>() {};
        return getMessagesRequestCreation(chatId, messageIds, from, to, count).toEntity(localVarReturnType);
    }

    /**
     * Получение сообщений
     * Метод возвращает информацию о сообщении или массив сообщений из чата. Для выполнения запроса нужно указать один из параметров — chat_id или message_ids:  - chat_id — метод возвращает массив сообщений из указанного чата. Сообщения возвращаются в обратном порядке: последние сообщения будут первыми в массиве - message_ids — метод возвращает информацию о запрошенных сообщениях. Можно указать один идентификатор или несколько  Источник описания: [GET /messages](https://dev.max.ru/docs-api/methods/GET/messages)
     * <p><b>200</b> - Получение сообщений
     * @param chatId ID чата, чтобы получить сообщения из определённого чата. Обязательный параметр, если не указан
     * @param messageIds Список ID сообщений, которые нужно получить (через запятую). Обязательный параметр, если не указан chat_id
     * @param from Время начала для запрашиваемых сообщений (в формате Unix timestamp)
     * @param to Время окончания для запрашиваемых сообщений (в формате Unix timestamp)
     * @param count По умолчанию:  50 Максимальное количество сообщений в ответе  Диапазон: &#x60;[1-100]&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMessagesWithResponseSpec(@jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Object messageIds, @jakarta.annotation.Nullable Integer from, @jakarta.annotation.Nullable Integer to, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        return getMessagesRequestCreation(chatId, messageIds, from, to, count);
    }

    /**
     * Получить сообщение
     * Возвращает сообщение по его ID  Источник описания: [GET /messages/{messageId}](https://dev.max.ru/docs-api/methods/GET/messages/-messageId-)
     * <p><b>200</b> - Получить сообщение
     * @param messageId ID сообщения (mid), чтобы получить одно сообщение в чате  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return Message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMessagesByMessageIdRequestCreation(@jakarta.annotation.Nonnull String messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getMessagesByMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<Message> localVarReturnType = new ParameterizedTypeReference<Message>() {};
        return apiClient.invokeAPI("/messages/{messageId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получить сообщение
     * Возвращает сообщение по его ID  Источник описания: [GET /messages/{messageId}](https://dev.max.ru/docs-api/methods/GET/messages/-messageId-)
     * <p><b>200</b> - Получить сообщение
     * @param messageId ID сообщения (mid), чтобы получить одно сообщение в чате  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return Message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Message> getMessagesByMessageId(@jakarta.annotation.Nonnull String messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Message> localVarReturnType = new ParameterizedTypeReference<Message>() {};
        return getMessagesByMessageIdRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Получить сообщение
     * Возвращает сообщение по его ID  Источник описания: [GET /messages/{messageId}](https://dev.max.ru/docs-api/methods/GET/messages/-messageId-)
     * <p><b>200</b> - Получить сообщение
     * @param messageId ID сообщения (mid), чтобы получить одно сообщение в чате  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return ResponseEntity&lt;Message&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Message>> getMessagesByMessageIdWithHttpInfo(@jakarta.annotation.Nonnull String messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Message> localVarReturnType = new ParameterizedTypeReference<Message>() {};
        return getMessagesByMessageIdRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Получить сообщение
     * Возвращает сообщение по его ID  Источник описания: [GET /messages/{messageId}](https://dev.max.ru/docs-api/methods/GET/messages/-messageId-)
     * <p><b>200</b> - Получить сообщение
     * @param messageId ID сообщения (mid), чтобы получить одно сообщение в чате  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMessagesByMessageIdWithResponseSpec(@jakarta.annotation.Nonnull String messageId) throws WebClientResponseException {
        return getMessagesByMessageIdRequestCreation(messageId);
    }

    /**
     * Отправить сообщение
     * Отправляет сообщение в чат  Источник описания: [POST /messages](https://dev.max.ru/docs-api/methods/POST/messages)
     * <p><b>200</b> - Отправить сообщение
     * @param userId Если вы хотите отправить сообщение пользователю, укажите его ID
     * @param chatId Если сообщение отправляется в чат, укажите его ID
     * @param disableLinkPreview Если false, сервер не будет генерировать превью для ссылок в тексте сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return PostMessages200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postMessagesRequestCreation(@jakarta.annotation.Nullable Integer userId, @jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Boolean disableLinkPreview, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        Object postBody = newMessageBody;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "user_id", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "chat_id", chatId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "disable_link_preview", disableLinkPreview));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostMessages200Response> localVarReturnType = new ParameterizedTypeReference<PostMessages200Response>() {};
        return apiClient.invokeAPI("/messages", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Отправить сообщение
     * Отправляет сообщение в чат  Источник описания: [POST /messages](https://dev.max.ru/docs-api/methods/POST/messages)
     * <p><b>200</b> - Отправить сообщение
     * @param userId Если вы хотите отправить сообщение пользователю, укажите его ID
     * @param chatId Если сообщение отправляется в чат, укажите его ID
     * @param disableLinkPreview Если false, сервер не будет генерировать превью для ссылок в тексте сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return PostMessages200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostMessages200Response> postMessages(@jakarta.annotation.Nullable Integer userId, @jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Boolean disableLinkPreview, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        ParameterizedTypeReference<PostMessages200Response> localVarReturnType = new ParameterizedTypeReference<PostMessages200Response>() {};
        return postMessagesRequestCreation(userId, chatId, disableLinkPreview, newMessageBody).bodyToMono(localVarReturnType);
    }

    /**
     * Отправить сообщение
     * Отправляет сообщение в чат  Источник описания: [POST /messages](https://dev.max.ru/docs-api/methods/POST/messages)
     * <p><b>200</b> - Отправить сообщение
     * @param userId Если вы хотите отправить сообщение пользователю, укажите его ID
     * @param chatId Если сообщение отправляется в чат, укажите его ID
     * @param disableLinkPreview Если false, сервер не будет генерировать превью для ссылок в тексте сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return ResponseEntity&lt;PostMessages200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostMessages200Response>> postMessagesWithHttpInfo(@jakarta.annotation.Nullable Integer userId, @jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Boolean disableLinkPreview, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        ParameterizedTypeReference<PostMessages200Response> localVarReturnType = new ParameterizedTypeReference<PostMessages200Response>() {};
        return postMessagesRequestCreation(userId, chatId, disableLinkPreview, newMessageBody).toEntity(localVarReturnType);
    }

    /**
     * Отправить сообщение
     * Отправляет сообщение в чат  Источник описания: [POST /messages](https://dev.max.ru/docs-api/methods/POST/messages)
     * <p><b>200</b> - Отправить сообщение
     * @param userId Если вы хотите отправить сообщение пользователю, укажите его ID
     * @param chatId Если сообщение отправляется в чат, укажите его ID
     * @param disableLinkPreview Если false, сервер не будет генерировать превью для ссылок в тексте сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postMessagesWithResponseSpec(@jakarta.annotation.Nullable Integer userId, @jakarta.annotation.Nullable Integer chatId, @jakarta.annotation.Nullable Boolean disableLinkPreview, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        return postMessagesRequestCreation(userId, chatId, disableLinkPreview, newMessageBody);
    }

    /**
     * Редактировать сообщение
     * Редактирует сообщение в чате. Если поле attachments равно null, вложения текущего сообщения не изменяются. Если в этом поле передан пустой список, все вложения будут удалены  &gt; С помощью метода можно отредактировать сообщения, которые отправлены менее 24 часов назад  Источник описания: [PUT /messages](https://dev.max.ru/docs-api/methods/PUT/messages)
     * <p><b>200</b> - Редактировать сообщение
     * @param messageId от 1 символа ID редактируемого сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec putMessagesRequestCreation(@jakarta.annotation.Nonnull String messageId, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        Object postBody = newMessageBody;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling putMessages", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "message_id", messageId));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/messages", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Редактировать сообщение
     * Редактирует сообщение в чате. Если поле attachments равно null, вложения текущего сообщения не изменяются. Если в этом поле передан пустой список, все вложения будут удалены  &gt; С помощью метода можно отредактировать сообщения, которые отправлены менее 24 часов назад  Источник описания: [PUT /messages](https://dev.max.ru/docs-api/methods/PUT/messages)
     * <p><b>200</b> - Редактировать сообщение
     * @param messageId от 1 символа ID редактируемого сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> putMessages(@jakarta.annotation.Nonnull String messageId, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return putMessagesRequestCreation(messageId, newMessageBody).bodyToMono(localVarReturnType);
    }

    /**
     * Редактировать сообщение
     * Редактирует сообщение в чате. Если поле attachments равно null, вложения текущего сообщения не изменяются. Если в этом поле передан пустой список, все вложения будут удалены  &gt; С помощью метода можно отредактировать сообщения, которые отправлены менее 24 часов назад  Источник описания: [PUT /messages](https://dev.max.ru/docs-api/methods/PUT/messages)
     * <p><b>200</b> - Редактировать сообщение
     * @param messageId от 1 символа ID редактируемого сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> putMessagesWithHttpInfo(@jakarta.annotation.Nonnull String messageId, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return putMessagesRequestCreation(messageId, newMessageBody).toEntity(localVarReturnType);
    }

    /**
     * Редактировать сообщение
     * Редактирует сообщение в чате. Если поле attachments равно null, вложения текущего сообщения не изменяются. Если в этом поле передан пустой список, все вложения будут удалены  &gt; С помощью метода можно отредактировать сообщения, которые отправлены менее 24 часов назад  Источник описания: [PUT /messages](https://dev.max.ru/docs-api/methods/PUT/messages)
     * <p><b>200</b> - Редактировать сообщение
     * @param messageId от 1 символа ID редактируемого сообщения
     * @param newMessageBody The newMessageBody parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec putMessagesWithResponseSpec(@jakarta.annotation.Nonnull String messageId, @jakarta.annotation.Nullable NewMessageBody newMessageBody) throws WebClientResponseException {
        return putMessagesRequestCreation(messageId, newMessageBody);
    }
}
