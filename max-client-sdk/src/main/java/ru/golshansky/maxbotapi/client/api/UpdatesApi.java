package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.GetUpdates200Response;

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
public class UpdatesApi {
    private ApiClient apiClient;

    public UpdatesApi() {
        this(new ApiClient());
    }

    public UpdatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Получение обновлений
     * Этот метод можно использовать для получения обновлений при разработке и тестировании, если ваш бот не подписан на Webhook. Для production-окружения рекомендуем использовать Webhook  Метод использует долгий опрос (long polling). Каждое обновление имеет свой номер последовательности. Свойство marker в ответе указывает на следующее ожидаемое обновление.  Все предыдущие обновления считаются завершёнными после прохождения параметра marker. Если параметр marker не передан, бот получит все обновления, произошедшие после последнего подтверждения  Источник описания: [GET /updates](https://dev.max.ru/docs-api/methods/GET/updates)
     * <p><b>200</b> - Получение обновлений
     * @param limit По умолчанию:  100 Максимальное количество обновлений для получения  Диапазон: &#x60;[1-1000]&#x60;
     * @param timeout По умолчанию:  30 Тайм-аут в секундах для долгого опроса  Диапазон: &#x60;[0-90]&#x60;
     * @param marker Если передан, бот получит обновления, которые еще не были получены. Если не передан, получит все новые обновления
     * @param types Пример:  types&#x3D;message_created,message_callback Список типов обновлений, которые бот хочет получить (например, message_created, message_callback  Шаблон: &#x60;)&#x60;
     * @return GetUpdates200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getUpdatesRequestCreation(@jakarta.annotation.Nullable Integer limit, @jakarta.annotation.Nullable Integer timeout, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable List<String> types) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "limit", limit));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "timeout", timeout));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "marker", marker));
        queryParams.putAll(apiClient.parameterToMultiValueMap(ApiClient.CollectionFormat.valueOf("multi".toUpperCase(Locale.ROOT)), "types", types));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<GetUpdates200Response> localVarReturnType = new ParameterizedTypeReference<GetUpdates200Response>() {};
        return apiClient.invokeAPI("/updates", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение обновлений
     * Этот метод можно использовать для получения обновлений при разработке и тестировании, если ваш бот не подписан на Webhook. Для production-окружения рекомендуем использовать Webhook  Метод использует долгий опрос (long polling). Каждое обновление имеет свой номер последовательности. Свойство marker в ответе указывает на следующее ожидаемое обновление.  Все предыдущие обновления считаются завершёнными после прохождения параметра marker. Если параметр marker не передан, бот получит все обновления, произошедшие после последнего подтверждения  Источник описания: [GET /updates](https://dev.max.ru/docs-api/methods/GET/updates)
     * <p><b>200</b> - Получение обновлений
     * @param limit По умолчанию:  100 Максимальное количество обновлений для получения  Диапазон: &#x60;[1-1000]&#x60;
     * @param timeout По умолчанию:  30 Тайм-аут в секундах для долгого опроса  Диапазон: &#x60;[0-90]&#x60;
     * @param marker Если передан, бот получит обновления, которые еще не были получены. Если не передан, получит все новые обновления
     * @param types Пример:  types&#x3D;message_created,message_callback Список типов обновлений, которые бот хочет получить (например, message_created, message_callback  Шаблон: &#x60;)&#x60;
     * @return GetUpdates200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetUpdates200Response> getUpdates(@jakarta.annotation.Nullable Integer limit, @jakarta.annotation.Nullable Integer timeout, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable List<String> types) throws WebClientResponseException {
        ParameterizedTypeReference<GetUpdates200Response> localVarReturnType = new ParameterizedTypeReference<GetUpdates200Response>() {};
        return getUpdatesRequestCreation(limit, timeout, marker, types).bodyToMono(localVarReturnType);
    }

    /**
     * Получение обновлений
     * Этот метод можно использовать для получения обновлений при разработке и тестировании, если ваш бот не подписан на Webhook. Для production-окружения рекомендуем использовать Webhook  Метод использует долгий опрос (long polling). Каждое обновление имеет свой номер последовательности. Свойство marker в ответе указывает на следующее ожидаемое обновление.  Все предыдущие обновления считаются завершёнными после прохождения параметра marker. Если параметр marker не передан, бот получит все обновления, произошедшие после последнего подтверждения  Источник описания: [GET /updates](https://dev.max.ru/docs-api/methods/GET/updates)
     * <p><b>200</b> - Получение обновлений
     * @param limit По умолчанию:  100 Максимальное количество обновлений для получения  Диапазон: &#x60;[1-1000]&#x60;
     * @param timeout По умолчанию:  30 Тайм-аут в секундах для долгого опроса  Диапазон: &#x60;[0-90]&#x60;
     * @param marker Если передан, бот получит обновления, которые еще не были получены. Если не передан, получит все новые обновления
     * @param types Пример:  types&#x3D;message_created,message_callback Список типов обновлений, которые бот хочет получить (например, message_created, message_callback  Шаблон: &#x60;)&#x60;
     * @return ResponseEntity&lt;GetUpdates200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetUpdates200Response>> getUpdatesWithHttpInfo(@jakarta.annotation.Nullable Integer limit, @jakarta.annotation.Nullable Integer timeout, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable List<String> types) throws WebClientResponseException {
        ParameterizedTypeReference<GetUpdates200Response> localVarReturnType = new ParameterizedTypeReference<GetUpdates200Response>() {};
        return getUpdatesRequestCreation(limit, timeout, marker, types).toEntity(localVarReturnType);
    }

    /**
     * Получение обновлений
     * Этот метод можно использовать для получения обновлений при разработке и тестировании, если ваш бот не подписан на Webhook. Для production-окружения рекомендуем использовать Webhook  Метод использует долгий опрос (long polling). Каждое обновление имеет свой номер последовательности. Свойство marker в ответе указывает на следующее ожидаемое обновление.  Все предыдущие обновления считаются завершёнными после прохождения параметра marker. Если параметр marker не передан, бот получит все обновления, произошедшие после последнего подтверждения  Источник описания: [GET /updates](https://dev.max.ru/docs-api/methods/GET/updates)
     * <p><b>200</b> - Получение обновлений
     * @param limit По умолчанию:  100 Максимальное количество обновлений для получения  Диапазон: &#x60;[1-1000]&#x60;
     * @param timeout По умолчанию:  30 Тайм-аут в секундах для долгого опроса  Диапазон: &#x60;[0-90]&#x60;
     * @param marker Если передан, бот получит обновления, которые еще не были получены. Если не передан, получит все новые обновления
     * @param types Пример:  types&#x3D;message_created,message_callback Список типов обновлений, которые бот хочет получить (например, message_created, message_callback  Шаблон: &#x60;)&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getUpdatesWithResponseSpec(@jakarta.annotation.Nullable Integer limit, @jakarta.annotation.Nullable Integer timeout, @jakarta.annotation.Nullable Integer marker, @jakarta.annotation.Nullable List<String> types) throws WebClientResponseException {
        return getUpdatesRequestCreation(limit, timeout, marker, types);
    }
}
