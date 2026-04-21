package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.Chat;
import ru.golshansky.maxbotapi.client.model.ChatMember;
import ru.golshansky.maxbotapi.client.model.GetChats200Response;
import ru.golshansky.maxbotapi.client.model.GetChatsByChatIdMembers200Response;
import ru.golshansky.maxbotapi.client.model.GetChatsByChatIdPin200Response;
import ru.golshansky.maxbotapi.client.model.PatchChatsByChatIdRequest;
import ru.golshansky.maxbotapi.client.model.PostAnswers200Response;
import ru.golshansky.maxbotapi.client.model.PostChatsByChatIdActionsRequest;
import ru.golshansky.maxbotapi.client.model.PostChatsByChatIdMembers200Response;
import ru.golshansky.maxbotapi.client.model.PostChatsByChatIdMembersAdminsRequest;
import ru.golshansky.maxbotapi.client.model.PostChatsByChatIdMembersRequest;
import ru.golshansky.maxbotapi.client.model.PutChatsByChatIdPinRequest;

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
public class ChatsApi {
    private ApiClient apiClient;

    public ChatsApi() {
        this(new ApiClient());
    }

    public ChatsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Удаление группового чата
     * Удаляет групповой чат для всех участников  Источник описания: [DELETE /chats/{chatId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-)
     * <p><b>200</b> - Удаление группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteChatsByChatIdRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling deleteChatsByChatId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Удаление группового чата
     * Удаляет групповой чат для всех участников  Источник описания: [DELETE /chats/{chatId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-)
     * <p><b>200</b> - Удаление группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteChatsByChatId(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Удаление группового чата
     * Удаляет групповой чат для всех участников  Источник описания: [DELETE /chats/{chatId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-)
     * <p><b>200</b> - Удаление группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteChatsByChatIdWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Удаление группового чата
     * Удаляет групповой чат для всех участников  Источник описания: [DELETE /chats/{chatId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-)
     * <p><b>200</b> - Удаление группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteChatsByChatIdWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return deleteChatsByChatIdRequestCreation(chatId);
    }

    /**
     * Удаление участника из группового чата
     * Удаляет участника из группового чата. Для этого могут потребоваться дополнительные права  Источник описания: [DELETE /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members)
     * <p><b>200</b> - Удаление участника из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId ID пользователя, которого нужно удалить из чата
     * @param block Если установлено в  true, пользователь будет заблокирован в чате. Применяется только для чатов с публичной или приватной ссылкой. Игнорируется в остальных случаях
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteChatsByChatIdMembersRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId, @jakarta.annotation.Nullable Boolean block) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling deleteChatsByChatIdMembers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new WebClientResponseException("Missing the required parameter 'userId' when calling deleteChatsByChatIdMembers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "user_id", userId));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "block", block));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Удаление участника из группового чата
     * Удаляет участника из группового чата. Для этого могут потребоваться дополнительные права  Источник описания: [DELETE /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members)
     * <p><b>200</b> - Удаление участника из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId ID пользователя, которого нужно удалить из чата
     * @param block Если установлено в  true, пользователь будет заблокирован в чате. Применяется только для чатов с публичной или приватной ссылкой. Игнорируется в остальных случаях
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteChatsByChatIdMembers(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId, @jakarta.annotation.Nullable Boolean block) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersRequestCreation(chatId, userId, block).bodyToMono(localVarReturnType);
    }

    /**
     * Удаление участника из группового чата
     * Удаляет участника из группового чата. Для этого могут потребоваться дополнительные права  Источник описания: [DELETE /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members)
     * <p><b>200</b> - Удаление участника из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId ID пользователя, которого нужно удалить из чата
     * @param block Если установлено в  true, пользователь будет заблокирован в чате. Применяется только для чатов с публичной или приватной ссылкой. Игнорируется в остальных случаях
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteChatsByChatIdMembersWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId, @jakarta.annotation.Nullable Boolean block) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersRequestCreation(chatId, userId, block).toEntity(localVarReturnType);
    }

    /**
     * Удаление участника из группового чата
     * Удаляет участника из группового чата. Для этого могут потребоваться дополнительные права  Источник описания: [DELETE /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members)
     * <p><b>200</b> - Удаление участника из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId ID пользователя, которого нужно удалить из чата
     * @param block Если установлено в  true, пользователь будет заблокирован в чате. Применяется только для чатов с публичной или приватной ссылкой. Игнорируется в остальных случаях
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteChatsByChatIdMembersWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId, @jakarta.annotation.Nullable Boolean block) throws WebClientResponseException {
        return deleteChatsByChatIdMembersRequestCreation(chatId, userId, block);
    }

    /**
     * Отменить права администратора в групповом чате
     * Отменяет права администратора у пользователя в групповом чате, лишая его административных привилегий  Источник описания: [DELETE /chats/{chatId}/members/admins/{userId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/admins/-userId-)
     * <p><b>200</b> - Отменить права администратора в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId Идентификатор пользователя
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteChatsByChatIdMembersAdminsByUserIdRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling deleteChatsByChatIdMembersAdminsByUserId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new WebClientResponseException("Missing the required parameter 'userId' when calling deleteChatsByChatIdMembersAdminsByUserId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);
        pathParams.put("userId", userId);

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

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members/admins/{userId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Отменить права администратора в групповом чате
     * Отменяет права администратора у пользователя в групповом чате, лишая его административных привилегий  Источник описания: [DELETE /chats/{chatId}/members/admins/{userId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/admins/-userId-)
     * <p><b>200</b> - Отменить права администратора в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId Идентификатор пользователя
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteChatsByChatIdMembersAdminsByUserId(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersAdminsByUserIdRequestCreation(chatId, userId).bodyToMono(localVarReturnType);
    }

    /**
     * Отменить права администратора в групповом чате
     * Отменяет права администратора у пользователя в групповом чате, лишая его административных привилегий  Источник описания: [DELETE /chats/{chatId}/members/admins/{userId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/admins/-userId-)
     * <p><b>200</b> - Отменить права администратора в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId Идентификатор пользователя
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteChatsByChatIdMembersAdminsByUserIdWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersAdminsByUserIdRequestCreation(chatId, userId).toEntity(localVarReturnType);
    }

    /**
     * Отменить права администратора в групповом чате
     * Отменяет права администратора у пользователя в групповом чате, лишая его административных привилегий  Источник описания: [DELETE /chats/{chatId}/members/admins/{userId}](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/admins/-userId-)
     * <p><b>200</b> - Отменить права администратора в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userId Идентификатор пользователя
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteChatsByChatIdMembersAdminsByUserIdWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull Integer userId) throws WebClientResponseException {
        return deleteChatsByChatIdMembersAdminsByUserIdRequestCreation(chatId, userId);
    }

    /**
     * Удаление бота из группового чата
     * Удаляет бота из участников группового чата  Источник описания: [DELETE /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/me)
     * <p><b>200</b> - Удаление бота из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteChatsByChatIdMembersMeRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling deleteChatsByChatIdMembersMe", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members/me", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Удаление бота из группового чата
     * Удаляет бота из участников группового чата  Источник описания: [DELETE /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/me)
     * <p><b>200</b> - Удаление бота из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteChatsByChatIdMembersMe(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersMeRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Удаление бота из группового чата
     * Удаляет бота из участников группового чата  Источник описания: [DELETE /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/me)
     * <p><b>200</b> - Удаление бота из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteChatsByChatIdMembersMeWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdMembersMeRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Удаление бота из группового чата
     * Удаляет бота из участников группового чата  Источник описания: [DELETE /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/members/me)
     * <p><b>200</b> - Удаление бота из группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteChatsByChatIdMembersMeWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return deleteChatsByChatIdMembersMeRequestCreation(chatId);
    }

    /**
     * Удаление закреплённого сообщения в групповом чате
     * Удаляет закреплённое сообщение в групповом чате  Источник описания: [DELETE /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/pin)
     * <p><b>200</b> - Удаление закреплённого сообщения в групповом чате
     * @param chatId ID чата, из которого нужно удалить закреплённое сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteChatsByChatIdPinRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling deleteChatsByChatIdPin", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/pin", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Удаление закреплённого сообщения в групповом чате
     * Удаляет закреплённое сообщение в групповом чате  Источник описания: [DELETE /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/pin)
     * <p><b>200</b> - Удаление закреплённого сообщения в групповом чате
     * @param chatId ID чата, из которого нужно удалить закреплённое сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteChatsByChatIdPin(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdPinRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Удаление закреплённого сообщения в групповом чате
     * Удаляет закреплённое сообщение в групповом чате  Источник описания: [DELETE /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/pin)
     * <p><b>200</b> - Удаление закреплённого сообщения в групповом чате
     * @param chatId ID чата, из которого нужно удалить закреплённое сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteChatsByChatIdPinWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteChatsByChatIdPinRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Удаление закреплённого сообщения в групповом чате
     * Удаляет закреплённое сообщение в групповом чате  Источник описания: [DELETE /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/DELETE/chats/-chatId-/pin)
     * <p><b>200</b> - Удаление закреплённого сообщения в групповом чате
     * @param chatId ID чата, из которого нужно удалить закреплённое сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteChatsByChatIdPinWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return deleteChatsByChatIdPinRequestCreation(chatId);
    }

    /**
     * Получение списка всех групповых чатов
     * Возвращает список групповых чатов, в которых участвовал бот, информацию о каждом чате и маркер для перехода к следующей странице списка  Источник описания: [GET /chats](https://dev.max.ru/docs-api/methods/GET/chats)
     * <p><b>200</b> - Получение списка всех групповых чатов
     * @param count По умолчанию:  50 Количество запрашиваемых чатов  Диапазон: &#x60;[1-100]&#x60;
     * @param marker Указатель на следующую страницу данных. Для первой страницы передайте null
     * @return GetChats200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsRequestCreation(@jakarta.annotation.Nullable Integer count, @jakarta.annotation.Nullable Integer marker) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "count", count));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "marker", marker));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<GetChats200Response> localVarReturnType = new ParameterizedTypeReference<GetChats200Response>() {};
        return apiClient.invokeAPI("/chats", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение списка всех групповых чатов
     * Возвращает список групповых чатов, в которых участвовал бот, информацию о каждом чате и маркер для перехода к следующей странице списка  Источник описания: [GET /chats](https://dev.max.ru/docs-api/methods/GET/chats)
     * <p><b>200</b> - Получение списка всех групповых чатов
     * @param count По умолчанию:  50 Количество запрашиваемых чатов  Диапазон: &#x60;[1-100]&#x60;
     * @param marker Указатель на следующую страницу данных. Для первой страницы передайте null
     * @return GetChats200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetChats200Response> getChats(@jakarta.annotation.Nullable Integer count, @jakarta.annotation.Nullable Integer marker) throws WebClientResponseException {
        ParameterizedTypeReference<GetChats200Response> localVarReturnType = new ParameterizedTypeReference<GetChats200Response>() {};
        return getChatsRequestCreation(count, marker).bodyToMono(localVarReturnType);
    }

    /**
     * Получение списка всех групповых чатов
     * Возвращает список групповых чатов, в которых участвовал бот, информацию о каждом чате и маркер для перехода к следующей странице списка  Источник описания: [GET /chats](https://dev.max.ru/docs-api/methods/GET/chats)
     * <p><b>200</b> - Получение списка всех групповых чатов
     * @param count По умолчанию:  50 Количество запрашиваемых чатов  Диапазон: &#x60;[1-100]&#x60;
     * @param marker Указатель на следующую страницу данных. Для первой страницы передайте null
     * @return ResponseEntity&lt;GetChats200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetChats200Response>> getChatsWithHttpInfo(@jakarta.annotation.Nullable Integer count, @jakarta.annotation.Nullable Integer marker) throws WebClientResponseException {
        ParameterizedTypeReference<GetChats200Response> localVarReturnType = new ParameterizedTypeReference<GetChats200Response>() {};
        return getChatsRequestCreation(count, marker).toEntity(localVarReturnType);
    }

    /**
     * Получение списка всех групповых чатов
     * Возвращает список групповых чатов, в которых участвовал бот, информацию о каждом чате и маркер для перехода к следующей странице списка  Источник описания: [GET /chats](https://dev.max.ru/docs-api/methods/GET/chats)
     * <p><b>200</b> - Получение списка всех групповых чатов
     * @param count По умолчанию:  50 Количество запрашиваемых чатов  Диапазон: &#x60;[1-100]&#x60;
     * @param marker Указатель на следующую страницу данных. Для первой страницы передайте null
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsWithResponseSpec(@jakarta.annotation.Nullable Integer count, @jakarta.annotation.Nullable Integer marker) throws WebClientResponseException {
        return getChatsRequestCreation(count, marker);
    }

    /**
     * Получение информации о групповом чате
     * Возвращает информацию о групповом чате по его ID  Источник описания: [GET /chats/{chatId}](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-)
     * <p><b>200</b> - Получение информации о групповом чате
     * @param chatId ID запрашиваемого чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return Chat
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsByChatIdRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling getChatsByChatId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return apiClient.invokeAPI("/chats/{chatId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение информации о групповом чате
     * Возвращает информацию о групповом чате по его ID  Источник описания: [GET /chats/{chatId}](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-)
     * <p><b>200</b> - Получение информации о групповом чате
     * @param chatId ID запрашиваемого чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return Chat
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Chat> getChatsByChatId(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return getChatsByChatIdRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Получение информации о групповом чате
     * Возвращает информацию о групповом чате по его ID  Источник описания: [GET /chats/{chatId}](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-)
     * <p><b>200</b> - Получение информации о групповом чате
     * @param chatId ID запрашиваемого чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;Chat&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Chat>> getChatsByChatIdWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return getChatsByChatIdRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Получение информации о групповом чате
     * Возвращает информацию о групповом чате по его ID  Источник описания: [GET /chats/{chatId}](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-)
     * <p><b>200</b> - Получение информации о групповом чате
     * @param chatId ID запрашиваемого чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsByChatIdWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return getChatsByChatIdRequestCreation(chatId);
    }

    /**
     * Получение участников группового чата
     * Возвращает список участников группового чата  Источник описания: [GET /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members)
     * <p><b>200</b> - Получение участников группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userIds Список ID пользователей, чье членство нужно получить. Когда этот параметр передан, параметры count и marker игнорируются
     * @param marker Указатель на следующую страницу данных
     * @param count По умолчанию:  \&quot;20\&quot; Количество участников, которых нужно вернуть  Диапазон: &#x60;[1-100]&#x60;
     * @return GetChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsByChatIdMembersRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable List<Integer> userIds, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling getChatsByChatIdMembers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "user_ids", userIds));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "marker", marker));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "count", count));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение участников группового чата
     * Возвращает список участников группового чата  Источник описания: [GET /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members)
     * <p><b>200</b> - Получение участников группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userIds Список ID пользователей, чье членство нужно получить. Когда этот параметр передан, параметры count и marker игнорируются
     * @param marker Указатель на следующую страницу данных
     * @param count По умолчанию:  \&quot;20\&quot; Количество участников, которых нужно вернуть  Диапазон: &#x60;[1-100]&#x60;
     * @return GetChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetChatsByChatIdMembers200Response> getChatsByChatIdMembers(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable List<Integer> userIds, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return getChatsByChatIdMembersRequestCreation(chatId, userIds, marker, count).bodyToMono(localVarReturnType);
    }

    /**
     * Получение участников группового чата
     * Возвращает список участников группового чата  Источник описания: [GET /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members)
     * <p><b>200</b> - Получение участников группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userIds Список ID пользователей, чье членство нужно получить. Когда этот параметр передан, параметры count и marker игнорируются
     * @param marker Указатель на следующую страницу данных
     * @param count По умолчанию:  \&quot;20\&quot; Количество участников, которых нужно вернуть  Диапазон: &#x60;[1-100]&#x60;
     * @return ResponseEntity&lt;GetChatsByChatIdMembers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetChatsByChatIdMembers200Response>> getChatsByChatIdMembersWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable List<Integer> userIds, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return getChatsByChatIdMembersRequestCreation(chatId, userIds, marker, count).toEntity(localVarReturnType);
    }

    /**
     * Получение участников группового чата
     * Возвращает список участников группового чата  Источник описания: [GET /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members)
     * <p><b>200</b> - Получение участников группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param userIds Список ID пользователей, чье членство нужно получить. Когда этот параметр передан, параметры count и marker игнорируются
     * @param marker Указатель на следующую страницу данных
     * @param count По умолчанию:  \&quot;20\&quot; Количество участников, которых нужно вернуть  Диапазон: &#x60;[1-100]&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsByChatIdMembersWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable List<Integer> userIds, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable Integer count) throws WebClientResponseException {
        return getChatsByChatIdMembersRequestCreation(chatId, userIds, marker, count);
    }

    /**
     * Получение списка администраторов группового чата
     * Возвращает список всех администраторов группового чата. Бот должен быть администратором в запрашиваемом чате  Источник описания: [GET /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/admins)
     * <p><b>200</b> - Получение списка администраторов группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return GetChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsByChatIdMembersAdminsRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling getChatsByChatIdMembersAdmins", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members/admins", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение списка администраторов группового чата
     * Возвращает список всех администраторов группового чата. Бот должен быть администратором в запрашиваемом чате  Источник описания: [GET /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/admins)
     * <p><b>200</b> - Получение списка администраторов группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return GetChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetChatsByChatIdMembers200Response> getChatsByChatIdMembersAdmins(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return getChatsByChatIdMembersAdminsRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Получение списка администраторов группового чата
     * Возвращает список всех администраторов группового чата. Бот должен быть администратором в запрашиваемом чате  Источник описания: [GET /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/admins)
     * <p><b>200</b> - Получение списка администраторов группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;GetChatsByChatIdMembers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetChatsByChatIdMembers200Response>> getChatsByChatIdMembersAdminsWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdMembers200Response>() {};
        return getChatsByChatIdMembersAdminsRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Получение списка администраторов группового чата
     * Возвращает список всех администраторов группового чата. Бот должен быть администратором в запрашиваемом чате  Источник описания: [GET /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/admins)
     * <p><b>200</b> - Получение списка администраторов группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsByChatIdMembersAdminsWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return getChatsByChatIdMembersAdminsRequestCreation(chatId);
    }

    /**
     * Получение информации о членстве бота в групповом чате
     * Возвращает информацию о членстве текущего бота в групповом чате. Бот идентифицируется с помощью токена доступа  Источник описания: [GET /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/me)
     * <p><b>200</b> - Получение информации о членстве бота в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ChatMember
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsByChatIdMembersMeRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling getChatsByChatIdMembersMe", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<ChatMember> localVarReturnType = new ParameterizedTypeReference<ChatMember>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members/me", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение информации о членстве бота в групповом чате
     * Возвращает информацию о членстве текущего бота в групповом чате. Бот идентифицируется с помощью токена доступа  Источник описания: [GET /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/me)
     * <p><b>200</b> - Получение информации о членстве бота в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ChatMember
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ChatMember> getChatsByChatIdMembersMe(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<ChatMember> localVarReturnType = new ParameterizedTypeReference<ChatMember>() {};
        return getChatsByChatIdMembersMeRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Получение информации о членстве бота в групповом чате
     * Возвращает информацию о членстве текущего бота в групповом чате. Бот идентифицируется с помощью токена доступа  Источник описания: [GET /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/me)
     * <p><b>200</b> - Получение информации о членстве бота в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;ChatMember&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ChatMember>> getChatsByChatIdMembersMeWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<ChatMember> localVarReturnType = new ParameterizedTypeReference<ChatMember>() {};
        return getChatsByChatIdMembersMeRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Получение информации о членстве бота в групповом чате
     * Возвращает информацию о членстве текущего бота в групповом чате. Бот идентифицируется с помощью токена доступа  Источник описания: [GET /chats/{chatId}/members/me](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/members/me)
     * <p><b>200</b> - Получение информации о членстве бота в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsByChatIdMembersMeWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return getChatsByChatIdMembersMeRequestCreation(chatId);
    }

    /**
     * Получение закреплённого сообщения в групповом чате
     * Возвращает закреплённое сообщение в групповом чате  Источник описания: [GET /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/pin)
     * <p><b>200</b> - Получение закреплённого сообщения в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return GetChatsByChatIdPin200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChatsByChatIdPinRequestCreation(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling getChatsByChatIdPin", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

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

        ParameterizedTypeReference<GetChatsByChatIdPin200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdPin200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/pin", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение закреплённого сообщения в групповом чате
     * Возвращает закреплённое сообщение в групповом чате  Источник описания: [GET /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/pin)
     * <p><b>200</b> - Получение закреплённого сообщения в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return GetChatsByChatIdPin200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetChatsByChatIdPin200Response> getChatsByChatIdPin(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdPin200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdPin200Response>() {};
        return getChatsByChatIdPinRequestCreation(chatId).bodyToMono(localVarReturnType);
    }

    /**
     * Получение закреплённого сообщения в групповом чате
     * Возвращает закреплённое сообщение в групповом чате  Источник описания: [GET /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/pin)
     * <p><b>200</b> - Получение закреплённого сообщения в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseEntity&lt;GetChatsByChatIdPin200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetChatsByChatIdPin200Response>> getChatsByChatIdPinWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        ParameterizedTypeReference<GetChatsByChatIdPin200Response> localVarReturnType = new ParameterizedTypeReference<GetChatsByChatIdPin200Response>() {};
        return getChatsByChatIdPinRequestCreation(chatId).toEntity(localVarReturnType);
    }

    /**
     * Получение закреплённого сообщения в групповом чате
     * Возвращает закреплённое сообщение в групповом чате  Источник описания: [GET /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/GET/chats/-chatId-/pin)
     * <p><b>200</b> - Получение закреплённого сообщения в групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChatsByChatIdPinWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId) throws WebClientResponseException {
        return getChatsByChatIdPinRequestCreation(chatId);
    }

    /**
     * Изменение информации о групповом чате
     * Позволяет редактировать информацию о групповом чате, включая название, иконку и закреплённое сообщение  Источник описания: [PATCH /chats/{chatId}](https://dev.max.ru/docs-api/methods/PATCH/chats/-chatId-)
     * <p><b>200</b> - Изменение информации о групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param patchChatsByChatIdRequest The patchChatsByChatIdRequest parameter
     * @return Chat
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec patchChatsByChatIdRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable PatchChatsByChatIdRequest patchChatsByChatIdRequest) throws WebClientResponseException {
        Object postBody = patchChatsByChatIdRequest;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling patchChatsByChatId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return apiClient.invokeAPI("/chats/{chatId}", HttpMethod.PATCH, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Изменение информации о групповом чате
     * Позволяет редактировать информацию о групповом чате, включая название, иконку и закреплённое сообщение  Источник описания: [PATCH /chats/{chatId}](https://dev.max.ru/docs-api/methods/PATCH/chats/-chatId-)
     * <p><b>200</b> - Изменение информации о групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param patchChatsByChatIdRequest The patchChatsByChatIdRequest parameter
     * @return Chat
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Chat> patchChatsByChatId(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable PatchChatsByChatIdRequest patchChatsByChatIdRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return patchChatsByChatIdRequestCreation(chatId, patchChatsByChatIdRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Изменение информации о групповом чате
     * Позволяет редактировать информацию о групповом чате, включая название, иконку и закреплённое сообщение  Источник описания: [PATCH /chats/{chatId}](https://dev.max.ru/docs-api/methods/PATCH/chats/-chatId-)
     * <p><b>200</b> - Изменение информации о групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param patchChatsByChatIdRequest The patchChatsByChatIdRequest parameter
     * @return ResponseEntity&lt;Chat&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Chat>> patchChatsByChatIdWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable PatchChatsByChatIdRequest patchChatsByChatIdRequest) throws WebClientResponseException {
        ParameterizedTypeReference<Chat> localVarReturnType = new ParameterizedTypeReference<Chat>() {};
        return patchChatsByChatIdRequestCreation(chatId, patchChatsByChatIdRequest).toEntity(localVarReturnType);
    }

    /**
     * Изменение информации о групповом чате
     * Позволяет редактировать информацию о групповом чате, включая название, иконку и закреплённое сообщение  Источник описания: [PATCH /chats/{chatId}](https://dev.max.ru/docs-api/methods/PATCH/chats/-chatId-)
     * <p><b>200</b> - Изменение информации о групповом чате
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param patchChatsByChatIdRequest The patchChatsByChatIdRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec patchChatsByChatIdWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nullable PatchChatsByChatIdRequest patchChatsByChatIdRequest) throws WebClientResponseException {
        return patchChatsByChatIdRequestCreation(chatId, patchChatsByChatIdRequest);
    }

    /**
     * Отправка действия бота в групповой чат
     * Позволяет отправлять в групповой чат такие действия бота, как например: «набор текста» или «отправка фото»  Источник описания: [POST /chats/{chatId}/actions](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/actions)
     * <p><b>200</b> - Отправка действия бота в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdActionsRequest The postChatsByChatIdActionsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postChatsByChatIdActionsRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdActionsRequest postChatsByChatIdActionsRequest) throws WebClientResponseException {
        Object postBody = postChatsByChatIdActionsRequest;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling postChatsByChatIdActions", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'postChatsByChatIdActionsRequest' is set
        if (postChatsByChatIdActionsRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'postChatsByChatIdActionsRequest' when calling postChatsByChatIdActions", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

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
        return apiClient.invokeAPI("/chats/{chatId}/actions", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Отправка действия бота в групповой чат
     * Позволяет отправлять в групповой чат такие действия бота, как например: «набор текста» или «отправка фото»  Источник описания: [POST /chats/{chatId}/actions](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/actions)
     * <p><b>200</b> - Отправка действия бота в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdActionsRequest The postChatsByChatIdActionsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> postChatsByChatIdActions(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdActionsRequest postChatsByChatIdActionsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postChatsByChatIdActionsRequestCreation(chatId, postChatsByChatIdActionsRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Отправка действия бота в групповой чат
     * Позволяет отправлять в групповой чат такие действия бота, как например: «набор текста» или «отправка фото»  Источник описания: [POST /chats/{chatId}/actions](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/actions)
     * <p><b>200</b> - Отправка действия бота в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdActionsRequest The postChatsByChatIdActionsRequest parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> postChatsByChatIdActionsWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdActionsRequest postChatsByChatIdActionsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postChatsByChatIdActionsRequestCreation(chatId, postChatsByChatIdActionsRequest).toEntity(localVarReturnType);
    }

    /**
     * Отправка действия бота в групповой чат
     * Позволяет отправлять в групповой чат такие действия бота, как например: «набор текста» или «отправка фото»  Источник описания: [POST /chats/{chatId}/actions](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/actions)
     * <p><b>200</b> - Отправка действия бота в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdActionsRequest The postChatsByChatIdActionsRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postChatsByChatIdActionsWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdActionsRequest postChatsByChatIdActionsRequest) throws WebClientResponseException {
        return postChatsByChatIdActionsRequestCreation(chatId, postChatsByChatIdActionsRequest);
    }

    /**
     * Добавление участников в групповой чат
     * Добавляет участников в групповой чат. Для этого могут потребоваться дополнительные права  Источник описания: [POST /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members)
     * <p><b>200</b> - Добавление участников в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersRequest The postChatsByChatIdMembersRequest parameter
     * @return PostChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postChatsByChatIdMembersRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersRequest postChatsByChatIdMembersRequest) throws WebClientResponseException {
        Object postBody = postChatsByChatIdMembersRequest;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling postChatsByChatIdMembers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'postChatsByChatIdMembersRequest' is set
        if (postChatsByChatIdMembersRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'postChatsByChatIdMembersRequest' when calling postChatsByChatIdMembers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<PostChatsByChatIdMembers200Response>() {};
        return apiClient.invokeAPI("/chats/{chatId}/members", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Добавление участников в групповой чат
     * Добавляет участников в групповой чат. Для этого могут потребоваться дополнительные права  Источник описания: [POST /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members)
     * <p><b>200</b> - Добавление участников в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersRequest The postChatsByChatIdMembersRequest parameter
     * @return PostChatsByChatIdMembers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostChatsByChatIdMembers200Response> postChatsByChatIdMembers(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersRequest postChatsByChatIdMembersRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<PostChatsByChatIdMembers200Response>() {};
        return postChatsByChatIdMembersRequestCreation(chatId, postChatsByChatIdMembersRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Добавление участников в групповой чат
     * Добавляет участников в групповой чат. Для этого могут потребоваться дополнительные права  Источник описания: [POST /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members)
     * <p><b>200</b> - Добавление участников в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersRequest The postChatsByChatIdMembersRequest parameter
     * @return ResponseEntity&lt;PostChatsByChatIdMembers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostChatsByChatIdMembers200Response>> postChatsByChatIdMembersWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersRequest postChatsByChatIdMembersRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostChatsByChatIdMembers200Response> localVarReturnType = new ParameterizedTypeReference<PostChatsByChatIdMembers200Response>() {};
        return postChatsByChatIdMembersRequestCreation(chatId, postChatsByChatIdMembersRequest).toEntity(localVarReturnType);
    }

    /**
     * Добавление участников в групповой чат
     * Добавляет участников в групповой чат. Для этого могут потребоваться дополнительные права  Источник описания: [POST /chats/{chatId}/members](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members)
     * <p><b>200</b> - Добавление участников в групповой чат
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersRequest The postChatsByChatIdMembersRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postChatsByChatIdMembersWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersRequest postChatsByChatIdMembersRequest) throws WebClientResponseException {
        return postChatsByChatIdMembersRequestCreation(chatId, postChatsByChatIdMembersRequest);
    }

    /**
     * Назначить администратора группового чата
     * Возвращает значение true, если в групповой чат добавлены все администраторы  Источник описания: [POST /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members/admins)
     * <p><b>200</b> - Назначить администратора группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersAdminsRequest The postChatsByChatIdMembersAdminsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postChatsByChatIdMembersAdminsRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersAdminsRequest postChatsByChatIdMembersAdminsRequest) throws WebClientResponseException {
        Object postBody = postChatsByChatIdMembersAdminsRequest;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling postChatsByChatIdMembersAdmins", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'postChatsByChatIdMembersAdminsRequest' is set
        if (postChatsByChatIdMembersAdminsRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'postChatsByChatIdMembersAdminsRequest' when calling postChatsByChatIdMembersAdmins", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

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
        return apiClient.invokeAPI("/chats/{chatId}/members/admins", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Назначить администратора группового чата
     * Возвращает значение true, если в групповой чат добавлены все администраторы  Источник описания: [POST /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members/admins)
     * <p><b>200</b> - Назначить администратора группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersAdminsRequest The postChatsByChatIdMembersAdminsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> postChatsByChatIdMembersAdmins(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersAdminsRequest postChatsByChatIdMembersAdminsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postChatsByChatIdMembersAdminsRequestCreation(chatId, postChatsByChatIdMembersAdminsRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Назначить администратора группового чата
     * Возвращает значение true, если в групповой чат добавлены все администраторы  Источник описания: [POST /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members/admins)
     * <p><b>200</b> - Назначить администратора группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersAdminsRequest The postChatsByChatIdMembersAdminsRequest parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> postChatsByChatIdMembersAdminsWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersAdminsRequest postChatsByChatIdMembersAdminsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postChatsByChatIdMembersAdminsRequestCreation(chatId, postChatsByChatIdMembersAdminsRequest).toEntity(localVarReturnType);
    }

    /**
     * Назначить администратора группового чата
     * Возвращает значение true, если в групповой чат добавлены все администраторы  Источник описания: [POST /chats/{chatId}/members/admins](https://dev.max.ru/docs-api/methods/POST/chats/-chatId-/members/admins)
     * <p><b>200</b> - Назначить администратора группового чата
     * @param chatId ID чата  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param postChatsByChatIdMembersAdminsRequest The postChatsByChatIdMembersAdminsRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postChatsByChatIdMembersAdminsWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PostChatsByChatIdMembersAdminsRequest postChatsByChatIdMembersAdminsRequest) throws WebClientResponseException {
        return postChatsByChatIdMembersAdminsRequestCreation(chatId, postChatsByChatIdMembersAdminsRequest);
    }

    /**
     * Закрепление сообщения в групповом чате
     * Закрепляет сообщение в групповом чате  Источник описания: [PUT /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/PUT/chats/-chatId-/pin)
     * <p><b>200</b> - Закрепление сообщения в групповом чате
     * @param chatId ID чата, где должно быть закреплено сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param putChatsByChatIdPinRequest The putChatsByChatIdPinRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec putChatsByChatIdPinRequestCreation(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PutChatsByChatIdPinRequest putChatsByChatIdPinRequest) throws WebClientResponseException {
        Object postBody = putChatsByChatIdPinRequest;
        // verify the required parameter 'chatId' is set
        if (chatId == null) {
            throw new WebClientResponseException("Missing the required parameter 'chatId' when calling putChatsByChatIdPin", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'putChatsByChatIdPinRequest' is set
        if (putChatsByChatIdPinRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'putChatsByChatIdPinRequest' when calling putChatsByChatIdPin", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("chatId", chatId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

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
        return apiClient.invokeAPI("/chats/{chatId}/pin", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Закрепление сообщения в групповом чате
     * Закрепляет сообщение в групповом чате  Источник описания: [PUT /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/PUT/chats/-chatId-/pin)
     * <p><b>200</b> - Закрепление сообщения в групповом чате
     * @param chatId ID чата, где должно быть закреплено сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param putChatsByChatIdPinRequest The putChatsByChatIdPinRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> putChatsByChatIdPin(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PutChatsByChatIdPinRequest putChatsByChatIdPinRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return putChatsByChatIdPinRequestCreation(chatId, putChatsByChatIdPinRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Закрепление сообщения в групповом чате
     * Закрепляет сообщение в групповом чате  Источник описания: [PUT /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/PUT/chats/-chatId-/pin)
     * <p><b>200</b> - Закрепление сообщения в групповом чате
     * @param chatId ID чата, где должно быть закреплено сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param putChatsByChatIdPinRequest The putChatsByChatIdPinRequest parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> putChatsByChatIdPinWithHttpInfo(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PutChatsByChatIdPinRequest putChatsByChatIdPinRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return putChatsByChatIdPinRequestCreation(chatId, putChatsByChatIdPinRequest).toEntity(localVarReturnType);
    }

    /**
     * Закрепление сообщения в групповом чате
     * Закрепляет сообщение в групповом чате  Источник описания: [PUT /chats/{chatId}/pin](https://dev.max.ru/docs-api/methods/PUT/chats/-chatId-/pin)
     * <p><b>200</b> - Закрепление сообщения в групповом чате
     * @param chatId ID чата, где должно быть закреплено сообщение  Шаблон: &#x60;\\-?\\d+&#x60;
     * @param putChatsByChatIdPinRequest The putChatsByChatIdPinRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec putChatsByChatIdPinWithResponseSpec(@jakarta.annotation.Nonnull Integer chatId, @jakarta.annotation.Nonnull PutChatsByChatIdPinRequest putChatsByChatIdPinRequest) throws WebClientResponseException {
        return putChatsByChatIdPinRequestCreation(chatId, putChatsByChatIdPinRequest);
    }
}
