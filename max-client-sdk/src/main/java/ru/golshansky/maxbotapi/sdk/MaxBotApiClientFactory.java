package ru.golshansky.maxbotapi.sdk;

import java.lang.reflect.Method;
import java.util.Objects;

import ru.golshansky.maxbotapi.client.invoker.ApiClient;

/**
 * Фабрика для создания и настройки экземпляров {@link MaxBotApiClient}
 * для работы с MAX Bot API.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
public final class MaxBotApiClientFactory {
    public static final String TOKEN_ENV = "MAX_BOT_API_TOKEN";
    public static final String BASE_URL_ENV = "MAX_BOT_API_BASE_URL";
    public static final String DEFAULT_BASE_URL = "https://platform-api.max.ru";

    private MaxBotApiClientFactory() { }

    /**
     * Создаёт {@link MaxBotApiClient}, используя значения из переменных окружения.
     * Токен берётся из {@value #TOKEN_ENV}, базовый URL — из {@value #BASE_URL_ENV}.
     * Если URL не задан, используется {@value #DEFAULT_BASE_URL}.
     *
     * @return настроенный экземпляр {@link MaxBotApiClient}
     * @throws IllegalStateException если токен не задан или пустой
     */
    public static MaxBotApiClient fromEnvironment() {
        var token = requireToken(System.getenv(TOKEN_ENV));
        var baseUrl = normalizeBaseUrl(System.getenv(BASE_URL_ENV));
        return create(token, baseUrl);
    }

    /**
     * Создаёт {@link MaxBotApiClient} с указанными токеном и базовым URL.
     * Выполняет проверку токена, нормализацию URL и настройку авторизации.
     *
     * @param token токен доступа к MAX Bot API
     * @param baseUrl базовый URL API; если не задан или пустой, используется URL по умолчанию
     * @return настроенный экземпляр {@link MaxBotApiClient}
     * @throws IllegalStateException если токен не задан или пустой
     */
    public static MaxBotApiClient create(String token, String baseUrl) {
        var normalizedToken = requireToken(token);
        var normalizedBaseUrl = normalizeBaseUrl(baseUrl);
        var apiClient = new ApiClient();
        apiClient.setBasePath(normalizedBaseUrl);
        applyAuthorization(apiClient, normalizedToken);
        return new MaxBotApiClient(apiClient);
    }

    /**
     * Проверяет, что токен задан и не пустой, затем удаляет крайние пробелы.
     *
     * @param token исходное значение токена
     * @return токен без крайних пробелов
     * @throws IllegalStateException если токен не задан или пустой
     */
    private static String requireToken(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                "Не задан токен MAX Bot API. Укажите переменную окружения " + TOKEN_ENV + "."
            );
        }
        return token.trim();
    }

    /**
     * Возвращает нормализованный базовый URL API.
     * Если URL не задан или пустой, возвращает URL по умолчанию.
     *
     * @param baseUrl исходное значение базового URL
     * @return базовый URL без крайних пробелов либо URL по умолчанию
     */
    private static String normalizeBaseUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.isBlank()) {
            return DEFAULT_BASE_URL;
        }
        return baseUrl.trim();
    }

    /**
     * Настраивает авторизацию для {@link ApiClient}.
     * Пытается настроить объект аутентификации клиента, а при невозможности
     * добавляет заголовок {@code Authorization}.
     *
     * @param apiClient клиент, для которого настраивается авторизация
     * @param token токен доступа
     */
    private static void applyAuthorization(ApiClient apiClient, String token) {
        var authorizationValue = token.startsWith("Bearer ") ? token : "Bearer " + token;
        try {
            var authentications = apiClient.getAuthentications();
            var authentication = authentications.get("AccessTokenAuth");
            if (authentication != null && applyAuthObject(authentication, token)) {
                return;
            }
        } catch (RuntimeException ignored) {}
        apiClient.addDefaultHeader("Authorization", authorizationValue);
    }

    /**
     * Пытается записать токен в объект аутентификации через поддерживаемые сеттеры.
     *
     * @param authentication объект аутентификации
     * @param token токен доступа
     * @return {@code true}, если токен успешно установлен; иначе {@code false}
     * @throws NullPointerException если {@code authentication} равен {@code null}
     */
    private static boolean applyAuthObject(Object authentication, String token) {
        Objects.requireNonNull(authentication, "authentication");
        var authType = authentication.getClass();
        if (invokeSetter(authType, authentication, "setApiKey", token)) {
            invokeSetter(authType, authentication, "setApiKeyPrefix", "Bearer");
            return true;
        }
        return invokeSetter(authType, authentication, "setBearerToken", token);
    }

    /**
     * Вызывает строковый setter у объекта через reflection.
     *
     * @param authType класс объекта, у которого ищется метод
     * @param authObject объект, для которого вызывается метод
     * @param methodName имя метода
     * @param value значение, передаваемое в метод
     * @return {@code true}, если метод найден и успешно вызван; иначе {@code false}
     */
    private static boolean invokeSetter(Class<?> authType, Object authObject, String methodName, String value) {
        try {
            Method method = authType.getMethod(methodName, String.class);
            method.invoke(authObject, value);
            return true;
        } catch (ReflectiveOperationException ignored) {
            return false;
        }
    }
}
