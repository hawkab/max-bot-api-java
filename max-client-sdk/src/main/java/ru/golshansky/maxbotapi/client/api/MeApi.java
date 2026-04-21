package ru.golshansky.maxbotapi.client.api;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

import ru.golshansky.maxbotapi.client.model.BotInfo;

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
public class MeApi {
    private ApiClient apiClient;

    public MeApi() {
        this(new ApiClient());
    }

    public MeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Получение информации о боте
     * Метод возвращает информацию о боте, который идентифицируется с помощью токена доступа access_token. В ответе приходит объект User с вариантом наследования BotInfo, который содержит идентификатор бота, его название, никнейм, время последней активности, описание и аватар (при наличии)  Источник описания: [GET /me](https://dev.max.ru/docs-api/methods/GET/me)
     * <p><b>200</b> - Получение информации о боте
     * @return BotInfo
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMeRequestCreation() throws WebClientResponseException {
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

        ParameterizedTypeReference<BotInfo> localVarReturnType = new ParameterizedTypeReference<BotInfo>() {};
        return apiClient.invokeAPI("/me", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Получение информации о боте
     * Метод возвращает информацию о боте, который идентифицируется с помощью токена доступа access_token. В ответе приходит объект User с вариантом наследования BotInfo, который содержит идентификатор бота, его название, никнейм, время последней активности, описание и аватар (при наличии)  Источник описания: [GET /me](https://dev.max.ru/docs-api/methods/GET/me)
     * <p><b>200</b> - Получение информации о боте
     * @return BotInfo
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<BotInfo> getMe() throws WebClientResponseException {
        ParameterizedTypeReference<BotInfo> localVarReturnType = new ParameterizedTypeReference<BotInfo>() {};
        return getMeRequestCreation().bodyToMono(localVarReturnType);
    }

    /**
     * Получение информации о боте
     * Метод возвращает информацию о боте, который идентифицируется с помощью токена доступа access_token. В ответе приходит объект User с вариантом наследования BotInfo, который содержит идентификатор бота, его название, никнейм, время последней активности, описание и аватар (при наличии)  Источник описания: [GET /me](https://dev.max.ru/docs-api/methods/GET/me)
     * <p><b>200</b> - Получение информации о боте
     * @return ResponseEntity&lt;BotInfo&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<BotInfo>> getMeWithHttpInfo() throws WebClientResponseException {
        ParameterizedTypeReference<BotInfo> localVarReturnType = new ParameterizedTypeReference<BotInfo>() {};
        return getMeRequestCreation().toEntity(localVarReturnType);
    }

    /**
     * Получение информации о боте
     * Метод возвращает информацию о боте, который идентифицируется с помощью токена доступа access_token. В ответе приходит объект User с вариантом наследования BotInfo, который содержит идентификатор бота, его название, никнейм, время последней активности, описание и аватар (при наличии)  Источник описания: [GET /me](https://dev.max.ru/docs-api/methods/GET/me)
     * <p><b>200</b> - Получение информации о боте
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMeWithResponseSpec() throws WebClientResponseException {
        return getMeRequestCreation();
    }
}
