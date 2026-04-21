package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.GetSubscriptions200Response;
import ru.golshansky.maxbotapi.client.model.PostAnswers200Response;
import ru.golshansky.maxbotapi.client.model.PostSubscriptionsRequest;

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
public class SubscriptionsApi {
    private ApiClient apiClient;

    public SubscriptionsApi() {
        this(new ApiClient());
    }

    public SubscriptionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Отписка от обновлений
     * Отписывает бота от получения обновлений через Webhook. После вызова этого метода бот перестаёт получать уведомления о новых событиях, и становится доступна доставка уведомлений через API с длительным опросом  Источник описания: [DELETE /subscriptions](https://dev.max.ru/docs-api/methods/DELETE/subscriptions)
     * <p><b>200</b> - Отписка от обновлений
     * @param url URL, который нужно удалить из подписок на WebHook
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteSubscriptionsRequestCreation(@jakarta.annotation.Nonnull String url) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'url' is set
        if (url == null) {
            throw new WebClientResponseException("Missing the required parameter 'url' when calling deleteSubscriptions", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "url", url));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return apiClient.invokeAPI("/subscriptions", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Отписка от обновлений
     * Отписывает бота от получения обновлений через Webhook. После вызова этого метода бот перестаёт получать уведомления о новых событиях, и становится доступна доставка уведомлений через API с длительным опросом  Источник описания: [DELETE /subscriptions](https://dev.max.ru/docs-api/methods/DELETE/subscriptions)
     * <p><b>200</b> - Отписка от обновлений
     * @param url URL, который нужно удалить из подписок на WebHook
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> deleteSubscriptions(@jakarta.annotation.Nonnull String url) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteSubscriptionsRequestCreation(url).bodyToMono(localVarReturnType);
    }

    /**
     * Отписка от обновлений
     * Отписывает бота от получения обновлений через Webhook. После вызова этого метода бот перестаёт получать уведомления о новых событиях, и становится доступна доставка уведомлений через API с длительным опросом  Источник описания: [DELETE /subscriptions](https://dev.max.ru/docs-api/methods/DELETE/subscriptions)
     * <p><b>200</b> - Отписка от обновлений
     * @param url URL, который нужно удалить из подписок на WebHook
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> deleteSubscriptionsWithHttpInfo(@jakarta.annotation.Nonnull String url) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return deleteSubscriptionsRequestCreation(url).toEntity(localVarReturnType);
    }

    /**
     * Отписка от обновлений
     * Отписывает бота от получения обновлений через Webhook. После вызова этого метода бот перестаёт получать уведомления о новых событиях, и становится доступна доставка уведомлений через API с длительным опросом  Источник описания: [DELETE /subscriptions](https://dev.max.ru/docs-api/methods/DELETE/subscriptions)
     * <p><b>200</b> - Отписка от обновлений
     * @param url URL, который нужно удалить из подписок на WebHook
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteSubscriptionsWithResponseSpec(@jakarta.annotation.Nonnull String url) throws WebClientResponseException {
        return deleteSubscriptionsRequestCreation(url);
    }

    /**
     * Получение подписок
     * Если ваш бот получает данные через Webhook, этот метод возвращает список всех подписок. При настройке уведомлений для production-окружения рекомендуем использовать Webhook  &gt; Обратите внимание: для отправки вебхуков поддерживается только протокол HTTPS, включая самоподписанные сертификаты. HTTP не поддерживается  Источник описания: [GET /subscriptions](https://dev.max.ru/docs-api/methods/GET/subscriptions)
     * <p><b>200</b> - Получение подписок
     * @return GetSubscriptions200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getSubscriptionsRequestCreation() throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

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

        ParameterizedTypeReference<GetSubscriptions200Response> localVarReturnType = new ParameterizedTypeReference<GetSubscriptions200Response>() {};
        return apiClient.invokeAPI("/subscriptions", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение подписок
     * Если ваш бот получает данные через Webhook, этот метод возвращает список всех подписок. При настройке уведомлений для production-окружения рекомендуем использовать Webhook  &gt; Обратите внимание: для отправки вебхуков поддерживается только протокол HTTPS, включая самоподписанные сертификаты. HTTP не поддерживается  Источник описания: [GET /subscriptions](https://dev.max.ru/docs-api/methods/GET/subscriptions)
     * <p><b>200</b> - Получение подписок
     * @return GetSubscriptions200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetSubscriptions200Response> getSubscriptions() throws WebClientResponseException {
        ParameterizedTypeReference<GetSubscriptions200Response> localVarReturnType = new ParameterizedTypeReference<GetSubscriptions200Response>() {};
        return getSubscriptionsRequestCreation().bodyToMono(localVarReturnType);
    }

    /**
     * Получение подписок
     * Если ваш бот получает данные через Webhook, этот метод возвращает список всех подписок. При настройке уведомлений для production-окружения рекомендуем использовать Webhook  &gt; Обратите внимание: для отправки вебхуков поддерживается только протокол HTTPS, включая самоподписанные сертификаты. HTTP не поддерживается  Источник описания: [GET /subscriptions](https://dev.max.ru/docs-api/methods/GET/subscriptions)
     * <p><b>200</b> - Получение подписок
     * @return ResponseEntity&lt;GetSubscriptions200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetSubscriptions200Response>> getSubscriptionsWithHttpInfo() throws WebClientResponseException {
        ParameterizedTypeReference<GetSubscriptions200Response> localVarReturnType = new ParameterizedTypeReference<GetSubscriptions200Response>() {};
        return getSubscriptionsRequestCreation().toEntity(localVarReturnType);
    }

    /**
     * Получение подписок
     * Если ваш бот получает данные через Webhook, этот метод возвращает список всех подписок. При настройке уведомлений для production-окружения рекомендуем использовать Webhook  &gt; Обратите внимание: для отправки вебхуков поддерживается только протокол HTTPS, включая самоподписанные сертификаты. HTTP не поддерживается  Источник описания: [GET /subscriptions](https://dev.max.ru/docs-api/methods/GET/subscriptions)
     * <p><b>200</b> - Получение подписок
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getSubscriptionsWithResponseSpec() throws WebClientResponseException {
        return getSubscriptionsRequestCreation();
    }

    /**
     * Подписка на обновления
     * Метод настраивает доставку событий бота через Webhook — основной механизм получения событий в продуктовых интеграциях. При активной подписке Long Polling не работает  После вызова метода события отправляются на указанный Webhook-endpoint в виде HTTPS POST-запросов с объектом Update  Как обрабатывается событие:  - При наступлении события выполняется вызов Webhook-endpoint - Выполняется TLS-валидация целевого endpoint для безопасной передачи данных - На endpoint отправляется HTTP-запрос - Если при создании подписки указан secret, проверяется заголовок X-Max-Bot-Api-Secret - При успешной валидации возвращается HTTP 200 OK - Выполняется бизнес-логика обработки события - Инициируются вызовы MAX API  Webhook-endpoint должен быть доступен по HTTPS на порту 443. Ваш сервер должен прослушивать этот порт. Порт в URL не указывается:  &gt; Поддерживается только порт 443. Если endpoint недоступен, события не доставляются  Перед отправкой событий устанавливается HTTPS-соединение и проверяется TLS-сертификат Webhook-endpoint. Это необходимо для безопасной передачи информации  Требования к сертификату:  - сертификат выдан доверенным центром сертификации - самоподписанные сертификаты не поддерживаются - доменное имя в URL совпадает с CN или SAN сертификата - сервер предоставляет полную цепочку сертификатов  &gt; Если TLS-проверка не проходит, события не доставляются  Webhook-endpoint должен возвращать HTTP 200 в течение 30 секунд. Любой другой код ответа или превышение тайм-аута — ошибка доставки  Источник описания: [POST /subscriptions](https://dev.max.ru/docs-api/methods/POST/subscriptions)
     * <p><b>200</b> - Подписка на обновления
     * @param postSubscriptionsRequest The postSubscriptionsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postSubscriptionsRequestCreation(@jakarta.annotation.Nonnull PostSubscriptionsRequest postSubscriptionsRequest) throws WebClientResponseException {
        Object postBody = postSubscriptionsRequest;
        // verify the required parameter 'postSubscriptionsRequest' is set
        if (postSubscriptionsRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'postSubscriptionsRequest' when calling postSubscriptions", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

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
        return apiClient.invokeAPI("/subscriptions", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Подписка на обновления
     * Метод настраивает доставку событий бота через Webhook — основной механизм получения событий в продуктовых интеграциях. При активной подписке Long Polling не работает  После вызова метода события отправляются на указанный Webhook-endpoint в виде HTTPS POST-запросов с объектом Update  Как обрабатывается событие:  - При наступлении события выполняется вызов Webhook-endpoint - Выполняется TLS-валидация целевого endpoint для безопасной передачи данных - На endpoint отправляется HTTP-запрос - Если при создании подписки указан secret, проверяется заголовок X-Max-Bot-Api-Secret - При успешной валидации возвращается HTTP 200 OK - Выполняется бизнес-логика обработки события - Инициируются вызовы MAX API  Webhook-endpoint должен быть доступен по HTTPS на порту 443. Ваш сервер должен прослушивать этот порт. Порт в URL не указывается:  &gt; Поддерживается только порт 443. Если endpoint недоступен, события не доставляются  Перед отправкой событий устанавливается HTTPS-соединение и проверяется TLS-сертификат Webhook-endpoint. Это необходимо для безопасной передачи информации  Требования к сертификату:  - сертификат выдан доверенным центром сертификации - самоподписанные сертификаты не поддерживаются - доменное имя в URL совпадает с CN или SAN сертификата - сервер предоставляет полную цепочку сертификатов  &gt; Если TLS-проверка не проходит, события не доставляются  Webhook-endpoint должен возвращать HTTP 200 в течение 30 секунд. Любой другой код ответа или превышение тайм-аута — ошибка доставки  Источник описания: [POST /subscriptions](https://dev.max.ru/docs-api/methods/POST/subscriptions)
     * <p><b>200</b> - Подписка на обновления
     * @param postSubscriptionsRequest The postSubscriptionsRequest parameter
     * @return PostAnswers200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostAnswers200Response> postSubscriptions(@jakarta.annotation.Nonnull PostSubscriptionsRequest postSubscriptionsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postSubscriptionsRequestCreation(postSubscriptionsRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Подписка на обновления
     * Метод настраивает доставку событий бота через Webhook — основной механизм получения событий в продуктовых интеграциях. При активной подписке Long Polling не работает  После вызова метода события отправляются на указанный Webhook-endpoint в виде HTTPS POST-запросов с объектом Update  Как обрабатывается событие:  - При наступлении события выполняется вызов Webhook-endpoint - Выполняется TLS-валидация целевого endpoint для безопасной передачи данных - На endpoint отправляется HTTP-запрос - Если при создании подписки указан secret, проверяется заголовок X-Max-Bot-Api-Secret - При успешной валидации возвращается HTTP 200 OK - Выполняется бизнес-логика обработки события - Инициируются вызовы MAX API  Webhook-endpoint должен быть доступен по HTTPS на порту 443. Ваш сервер должен прослушивать этот порт. Порт в URL не указывается:  &gt; Поддерживается только порт 443. Если endpoint недоступен, события не доставляются  Перед отправкой событий устанавливается HTTPS-соединение и проверяется TLS-сертификат Webhook-endpoint. Это необходимо для безопасной передачи информации  Требования к сертификату:  - сертификат выдан доверенным центром сертификации - самоподписанные сертификаты не поддерживаются - доменное имя в URL совпадает с CN или SAN сертификата - сервер предоставляет полную цепочку сертификатов  &gt; Если TLS-проверка не проходит, события не доставляются  Webhook-endpoint должен возвращать HTTP 200 в течение 30 секунд. Любой другой код ответа или превышение тайм-аута — ошибка доставки  Источник описания: [POST /subscriptions](https://dev.max.ru/docs-api/methods/POST/subscriptions)
     * <p><b>200</b> - Подписка на обновления
     * @param postSubscriptionsRequest The postSubscriptionsRequest parameter
     * @return ResponseEntity&lt;PostAnswers200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostAnswers200Response>> postSubscriptionsWithHttpInfo(@jakarta.annotation.Nonnull PostSubscriptionsRequest postSubscriptionsRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PostAnswers200Response> localVarReturnType = new ParameterizedTypeReference<PostAnswers200Response>() {};
        return postSubscriptionsRequestCreation(postSubscriptionsRequest).toEntity(localVarReturnType);
    }

    /**
     * Подписка на обновления
     * Метод настраивает доставку событий бота через Webhook — основной механизм получения событий в продуктовых интеграциях. При активной подписке Long Polling не работает  После вызова метода события отправляются на указанный Webhook-endpoint в виде HTTPS POST-запросов с объектом Update  Как обрабатывается событие:  - При наступлении события выполняется вызов Webhook-endpoint - Выполняется TLS-валидация целевого endpoint для безопасной передачи данных - На endpoint отправляется HTTP-запрос - Если при создании подписки указан secret, проверяется заголовок X-Max-Bot-Api-Secret - При успешной валидации возвращается HTTP 200 OK - Выполняется бизнес-логика обработки события - Инициируются вызовы MAX API  Webhook-endpoint должен быть доступен по HTTPS на порту 443. Ваш сервер должен прослушивать этот порт. Порт в URL не указывается:  &gt; Поддерживается только порт 443. Если endpoint недоступен, события не доставляются  Перед отправкой событий устанавливается HTTPS-соединение и проверяется TLS-сертификат Webhook-endpoint. Это необходимо для безопасной передачи информации  Требования к сертификату:  - сертификат выдан доверенным центром сертификации - самоподписанные сертификаты не поддерживаются - доменное имя в URL совпадает с CN или SAN сертификата - сервер предоставляет полную цепочку сертификатов  &gt; Если TLS-проверка не проходит, события не доставляются  Webhook-endpoint должен возвращать HTTP 200 в течение 30 секунд. Любой другой код ответа или превышение тайм-аута — ошибка доставки  Источник описания: [POST /subscriptions](https://dev.max.ru/docs-api/methods/POST/subscriptions)
     * <p><b>200</b> - Подписка на обновления
     * @param postSubscriptionsRequest The postSubscriptionsRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postSubscriptionsWithResponseSpec(@jakarta.annotation.Nonnull PostSubscriptionsRequest postSubscriptionsRequest) throws WebClientResponseException {
        return postSubscriptionsRequestCreation(postSubscriptionsRequest);
    }
}
