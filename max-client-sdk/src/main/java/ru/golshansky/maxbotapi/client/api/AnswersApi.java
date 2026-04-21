package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.PostAnswers200Response;
import ru.golshansky.maxbotapi.client.model.PostAnswersRequest;

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
public class AnswersApi {
    private ApiClient apiClient;

    public AnswersApi() {
        this(new ApiClient());
    }

    public AnswersApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Ответ на callback
     * Этот метод используется для отправки ответа после того, как пользователь нажал на кнопку. Ответом может быть обновленное сообщение и/или одноразовое уведомление для пользователя  Источник описания: [POST /answers](https://dev.max.ru/docs-api/methods/POST/answers)
     * <p><b>200</b> - Ответ на callback
     * @param callbackId от 1 символа Идентификатор кнопки, по которой пользователь кликнул. Бот получает идентификатор как часть Update с типом message_callback.  Можно получить из  GET:/updates через поле updates[i].callback.callback_id  Шаблон: &#x60;^(?!\\s*$).+&#x60;
     * @param postAnswersRequest The postAnswersRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postAnswersRequestCreation(@jakarta.annotation.Nonnull String callbackId, @jakarta.annotation.Nullable PostAnswersRequest postAnswersRequest) throws WebClientResponseException {
        Object postBody = postAnswersRequest;
        // verify the required parameter 'callbackId' is set
        if (callbackId == null) {
            throw new WebClientResponseException("Missing the required parameter 'callbackId' when calling postAnswers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "callback_id", callbackId));

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
        return apiClient.invokeAPI("/answers", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Ответ на callback
     * Этот метод используется для отправки ответа после того, как пользователь нажал на кнопку. Ответом может быть обновленное сообщение и/или одноразовое уведомление для пользователя  Источник описания: [POST /answers](https://dev.max.ru/docs-api/methods/POST/answers)
     * <p><b>200</b> - Ответ на callback
     * @param callbackId от 1 символа Идентификатор кнопки, по которой пользователь кликнул. Бот получает идентификатор как часть Update с типом message_callback.  Можно получить из  GET:/updates через поле updates[i].callback.callback_id  Шаблон: &#x60;^(?!\\s*$).+&#x60;
     * @param postAnswersRequest The postAnswersRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> postAnswers(@jakarta.annotation.Nonnull String callbackId, @jakarta.annotation.Nullable PostAnswersRequest postAnswersRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postAnswersRequestCreation(callbackId, postAnswersRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Ответ на callback
     * Этот метод используется для отправки ответа после того, как пользователь нажал на кнопку. Ответом может быть обновленное сообщение и/или одноразовое уведомление для пользователя  Источник описания: [POST /answers](https://dev.max.ru/docs-api/methods/POST/answers)
     * <p><b>200</b> - Ответ на callback
     * @param callbackId от 1 символа Идентификатор кнопки, по которой пользователь кликнул. Бот получает идентификатор как часть Update с типом message_callback.  Можно получить из  GET:/updates через поле updates[i].callback.callback_id  Шаблон: &#x60;^(?!\\s*$).+&#x60;
     * @param postAnswersRequest The postAnswersRequest parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> postAnswersWithHttpInfo(@jakarta.annotation.Nonnull String callbackId, @jakarta.annotation.Nullable PostAnswersRequest postAnswersRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postAnswersRequestCreation(callbackId, postAnswersRequest).toEntity(localVarReturnType);
    }

    /**
     * Ответ на callback
     * Этот метод используется для отправки ответа после того, как пользователь нажал на кнопку. Ответом может быть обновленное сообщение и/или одноразовое уведомление для пользователя  Источник описания: [POST /answers](https://dev.max.ru/docs-api/methods/POST/answers)
     * <p><b>200</b> - Ответ на callback
     * @param callbackId от 1 символа Идентификатор кнопки, по которой пользователь кликнул. Бот получает идентификатор как часть Update с типом message_callback.  Можно получить из  GET:/updates через поле updates[i].callback.callback_id  Шаблон: &#x60;^(?!\\s*$).+&#x60;
     * @param postAnswersRequest The postAnswersRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postAnswersWithResponseSpec(@jakarta.annotation.Nonnull String callbackId, @jakarta.annotation.Nullable PostAnswersRequest postAnswersRequest) throws WebClientResponseException {
        return postAnswersRequestCreation(callbackId, postAnswersRequest);
    }
}
