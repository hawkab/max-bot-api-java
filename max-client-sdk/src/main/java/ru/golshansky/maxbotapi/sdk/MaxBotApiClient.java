package ru.golshansky.maxbotapi.sdk;

import ru.golshansky.maxbotapi.client.api.AnswersApi;
import ru.golshansky.maxbotapi.client.api.ChatsApi;
import ru.golshansky.maxbotapi.client.api.MeApi;
import ru.golshansky.maxbotapi.client.api.MessagesApi;
import ru.golshansky.maxbotapi.client.api.SubscriptionsApi;
import ru.golshansky.maxbotapi.client.api.UpdatesApi;
import ru.golshansky.maxbotapi.client.api.UploadsApi;
import ru.golshansky.maxbotapi.client.api.VideosApi;
import ru.golshansky.maxbotapi.client.invoker.ApiClient;

/**
 * Корневой клиент MAX Bot API.
 * Инкапсулирует сгенерированный {@link ApiClient} и предоставляет
 * удобные фабричные методы доступа к API-группам.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
public final class MaxBotApiClient {

    private final ApiClient apiClient;

    /**
     * Создаёт клиент-обёртку над сгенерированным {@link ApiClient}.
     *
     * @param apiClient базовый сгенерированный клиент
     */
    public MaxBotApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Возвращает базовый сгенерированный клиент.
     *
     * @return экземпляр {@link ApiClient}
     */
    public ApiClient apiClient() {
        return apiClient;
    }

    /**
     * Возвращает API профиля бота.
     *
     * @return API профиля бота
     */
    public MeApi meApi() {
        return new MeApi(apiClient);
    }

    /**
     * Возвращает API чатов.
     *
     * @return API чатов
     */
    public ChatsApi chatsApi() {
        return new ChatsApi(apiClient);
    }

    /**
     * Возвращает API подписок.
     *
     * @return API подписок
     */
    public SubscriptionsApi subscriptionsApi() {
        return new SubscriptionsApi(apiClient);
    }

    /**
     * Возвращает API обновлений.
     *
     * @return API обновлений
     */
    public UpdatesApi updatesApi() {
        return new UpdatesApi(apiClient);
    }

    /**
     * Возвращает API загрузок.
     *
     * @return API загрузок
     */
    public UploadsApi uploadsApi() {
        return new UploadsApi(apiClient);
    }

    /**
     * Возвращает API сообщений.
     *
     * @return API сообщений
     */
    public MessagesApi messagesApi() {
        return new MessagesApi(apiClient);
    }

    /**
     * Возвращает API видео.
     *
     * @return API видео
     */
    public VideosApi videosApi() {
        return new VideosApi(apiClient);
    }

    /**
     * Возвращает API callback-ответов.
     *
     * @return API callback-ответов
     */
    public AnswersApi answersApi() {
        return new AnswersApi(apiClient);
    }
}
