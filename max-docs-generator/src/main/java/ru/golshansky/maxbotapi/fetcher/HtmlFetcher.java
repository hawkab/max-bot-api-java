package ru.golshansky.maxbotapi.fetcher;

import org.jsoup.nodes.Document;

/**
 * Интерфейс для получения HTML-документов по URL.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
public interface HtmlFetcher {

    /**
     * Загружает HTML-документ по указанному URL.
     *
     * @param url адрес страницы
     * @return загруженный {@link Document}
     */
    Document fetch(String url);
}
