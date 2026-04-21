package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.PostUploads200Response;

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
public class UploadsApi {
    private ApiClient apiClient;

    public UploadsApi() {
        this(new ApiClient());
    }

    public UploadsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Загрузка файлов
     * Возвращает URL для последующей загрузки файла  &gt; Параметр type&#x3D;photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image  Поддерживаются два типа загрузки:  - Multipart upload — более простой, но менее надёжный способ. В этом случае используется заголовок Content-Type: multipart/form-data. Файл отправляется целиком одним запросом. Если загрузка прервётся, невозможно её возобновить — придётся начать заново - Resumable upload — более надёжный способ, если заголовок Content-Type не равен multipart/form-data. Этот способ позволяет загружать файл частями и возобновить загрузку с последней успешно загруженной части в случае ошибок  Общие ограничения для обоих типов загрузки:  - Максимальный размер файла: 4 ГБ - Можно загружать только один файл за раз  Источник описания: [POST /uploads](https://dev.max.ru/docs-api/methods/POST/uploads)
     * <p><b>200</b> - Загрузка файлов
     * @param type Enum:  \&quot;image\&quot; \&quot;video\&quot; \&quot;audio\&quot; \&quot;file\&quot; Тип загружаемого файла  Поддерживаемые форматы:  image: JPG, JPEG, PNG, GIF, TIFF, BMP, HEIC video: MP4, MOV, MKV, WEBM, MATROSKA audio: MP3, WAV, M4A и другие file: любые типы файлов Значение photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image
     * @return PostUploads200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec postUploadsRequestCreation(@jakarta.annotation.Nonnull String type) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'type' is set
        if (type == null) {
            throw new WebClientResponseException("Missing the required parameter 'type' when calling postUploads", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "type", type));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] { "AccessTokenAuth" };

        ParameterizedTypeReference<PostUploads200Response> localVarReturnType = new ParameterizedTypeReference<PostUploads200Response>() {};
        return apiClient.invokeAPI("/uploads", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Загрузка файлов
     * Возвращает URL для последующей загрузки файла  &gt; Параметр type&#x3D;photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image  Поддерживаются два типа загрузки:  - Multipart upload — более простой, но менее надёжный способ. В этом случае используется заголовок Content-Type: multipart/form-data. Файл отправляется целиком одним запросом. Если загрузка прервётся, невозможно её возобновить — придётся начать заново - Resumable upload — более надёжный способ, если заголовок Content-Type не равен multipart/form-data. Этот способ позволяет загружать файл частями и возобновить загрузку с последней успешно загруженной части в случае ошибок  Общие ограничения для обоих типов загрузки:  - Максимальный размер файла: 4 ГБ - Можно загружать только один файл за раз  Источник описания: [POST /uploads](https://dev.max.ru/docs-api/methods/POST/uploads)
     * <p><b>200</b> - Загрузка файлов
     * @param type Enum:  \&quot;image\&quot; \&quot;video\&quot; \&quot;audio\&quot; \&quot;file\&quot; Тип загружаемого файла  Поддерживаемые форматы:  image: JPG, JPEG, PNG, GIF, TIFF, BMP, HEIC video: MP4, MOV, MKV, WEBM, MATROSKA audio: MP3, WAV, M4A и другие file: любые типы файлов Значение photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image
     * @return PostUploads200Response
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PostUploads200Response> postUploads(@jakarta.annotation.Nonnull String type) throws WebClientResponseException {
        ParameterizedTypeReference<PostUploads200Response> localVarReturnType = new ParameterizedTypeReference<PostUploads200Response>() {};
        return postUploadsRequestCreation(type).bodyToMono(localVarReturnType);
    }

    /**
     * Загрузка файлов
     * Возвращает URL для последующей загрузки файла  &gt; Параметр type&#x3D;photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image  Поддерживаются два типа загрузки:  - Multipart upload — более простой, но менее надёжный способ. В этом случае используется заголовок Content-Type: multipart/form-data. Файл отправляется целиком одним запросом. Если загрузка прервётся, невозможно её возобновить — придётся начать заново - Resumable upload — более надёжный способ, если заголовок Content-Type не равен multipart/form-data. Этот способ позволяет загружать файл частями и возобновить загрузку с последней успешно загруженной части в случае ошибок  Общие ограничения для обоих типов загрузки:  - Максимальный размер файла: 4 ГБ - Можно загружать только один файл за раз  Источник описания: [POST /uploads](https://dev.max.ru/docs-api/methods/POST/uploads)
     * <p><b>200</b> - Загрузка файлов
     * @param type Enum:  \&quot;image\&quot; \&quot;video\&quot; \&quot;audio\&quot; \&quot;file\&quot; Тип загружаемого файла  Поддерживаемые форматы:  image: JPG, JPEG, PNG, GIF, TIFF, BMP, HEIC video: MP4, MOV, MKV, WEBM, MATROSKA audio: MP3, WAV, M4A и другие file: любые типы файлов Значение photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image
     * @return ResponseEntity&lt;PostUploads200Response&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PostUploads200Response>> postUploadsWithHttpInfo(@jakarta.annotation.Nonnull String type) throws WebClientResponseException {
        ParameterizedTypeReference<PostUploads200Response> localVarReturnType = new ParameterizedTypeReference<PostUploads200Response>() {};
        return postUploadsRequestCreation(type).toEntity(localVarReturnType);
    }

    /**
     * Загрузка файлов
     * Возвращает URL для последующей загрузки файла  &gt; Параметр type&#x3D;photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image  Поддерживаются два типа загрузки:  - Multipart upload — более простой, но менее надёжный способ. В этом случае используется заголовок Content-Type: multipart/form-data. Файл отправляется целиком одним запросом. Если загрузка прервётся, невозможно её возобновить — придётся начать заново - Resumable upload — более надёжный способ, если заголовок Content-Type не равен multipart/form-data. Этот способ позволяет загружать файл частями и возобновить загрузку с последней успешно загруженной части в случае ошибок  Общие ограничения для обоих типов загрузки:  - Максимальный размер файла: 4 ГБ - Можно загружать только один файл за раз  Источник описания: [POST /uploads](https://dev.max.ru/docs-api/methods/POST/uploads)
     * <p><b>200</b> - Загрузка файлов
     * @param type Enum:  \&quot;image\&quot; \&quot;video\&quot; \&quot;audio\&quot; \&quot;file\&quot; Тип загружаемого файла  Поддерживаемые форматы:  image: JPG, JPEG, PNG, GIF, TIFF, BMP, HEIC video: MP4, MOV, MKV, WEBM, MATROSKA audio: MP3, WAV, M4A и другие file: любые типы файлов Значение photo больше не поддерживается. Если вы использовали type&#x3D;photo в ранее созданных интеграциях — замените его на type&#x3D;image
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec postUploadsWithResponseSpec(@jakarta.annotation.Nonnull String type) throws WebClientResponseException {
        return postUploadsRequestCreation(type);
    }
}
