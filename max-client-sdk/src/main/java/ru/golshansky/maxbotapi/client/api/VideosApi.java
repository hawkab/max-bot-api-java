package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.GetVideosByVideoToken200Response;

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
public class VideosApi {
    private ApiClient apiClient;

    public VideosApi() {
        this(new ApiClient());
    }

    public VideosApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Получить информацио о видео
     * Возвращает подробную информацию о прикреплённом видео. URL-адреса воспроизведения и дополнительные метаданные  Источник описания: [GET /videos/{videoToken}](https://dev.max.ru/docs-api/methods/GET/videos/-videoToken-)
     * <p><b>200</b> - Получить информацио о видео
     * @param videoToken Токен видео-вложения  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return GetVideosByVideoToken200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getVideosByVideoTokenRequestCreation(@jakarta.annotation.Nonnull String videoToken) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'videoToken' is set
        if (videoToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'videoToken' when calling getVideosByVideoToken", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("videoToken", videoToken);

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

        ParameterizedTypeReference<GetVideosByVideoToken200Response> localVarReturnType = new ParameterizedTypeReference<GetVideosByVideoToken200Response>() {};
        return apiClient.invokeAPI("/videos/{videoToken}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получить информацио о видео
     * Возвращает подробную информацию о прикреплённом видео. URL-адреса воспроизведения и дополнительные метаданные  Источник описания: [GET /videos/{videoToken}](https://dev.max.ru/docs-api/methods/GET/videos/-videoToken-)
     * <p><b>200</b> - Получить информацио о видео
     * @param videoToken Токен видео-вложения  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return GetVideosByVideoToken200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<GetVideosByVideoToken200Response> getVideosByVideoToken(@jakarta.annotation.Nonnull String videoToken) throws WebClientResponseException {
        ParameterizedTypeReference<GetVideosByVideoToken200Response> localVarReturnType = new ParameterizedTypeReference<GetVideosByVideoToken200Response>() {};
        return getVideosByVideoTokenRequestCreation(videoToken).bodyToMono(localVarReturnType);
    }

    /**
     * Получить информацио о видео
     * Возвращает подробную информацию о прикреплённом видео. URL-адреса воспроизведения и дополнительные метаданные  Источник описания: [GET /videos/{videoToken}](https://dev.max.ru/docs-api/methods/GET/videos/-videoToken-)
     * <p><b>200</b> - Получить информацио о видео
     * @param videoToken Токен видео-вложения  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return ResponseEntity&lt;GetVideosByVideoToken200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<GetVideosByVideoToken200Response>> getVideosByVideoTokenWithHttpInfo(@jakarta.annotation.Nonnull String videoToken) throws WebClientResponseException {
        ParameterizedTypeReference<GetVideosByVideoToken200Response> localVarReturnType = new ParameterizedTypeReference<GetVideosByVideoToken200Response>() {};
        return getVideosByVideoTokenRequestCreation(videoToken).toEntity(localVarReturnType);
    }

    /**
     * Получить информацио о видео
     * Возвращает подробную информацию о прикреплённом видео. URL-адреса воспроизведения и дополнительные метаданные  Источник описания: [GET /videos/{videoToken}](https://dev.max.ru/docs-api/methods/GET/videos/-videoToken-)
     * <p><b>200</b> - Получить информацио о видео
     * @param videoToken Токен видео-вложения  Шаблон: &#x60;[a-zA-Z0-9_\\-]+&#x60;
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getVideosByVideoTokenWithResponseSpec(@jakarta.annotation.Nonnull String videoToken) throws WebClientResponseException {
        return getVideosByVideoTokenRequestCreation(videoToken);
    }
}
