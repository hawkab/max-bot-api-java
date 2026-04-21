package ru.golshansky.maxbotapi.fetcher;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link HtmlFetcher} на основе Jsoup.
 * Выполняет загрузку HTML-документов с кэшированием результатов.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class JsoupHtmlFetcher implements HtmlFetcher {

    private static final Logger log = LoggerFactory.getLogger(JsoupHtmlFetcher.class);
    private static final String USER_AGENT =
        "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 "
            + "(KHTML, like Gecko) Chrome/123.0 Safari/537.36";
    private static final int REQUEST_TIMEOUT_MILLIS = (int) Duration.ofSeconds(30).toMillis();

    private final Map<String, Document> cache = new ConcurrentHashMap<>();

    /**
     * Загружает HTML-документ по URL с использованием кэша.
     *
     * @param url адрес страницы
     * @return загруженный {@link Document}
     * @throws IllegalStateException если не удалось загрузить страницу
     */
    @Override
    public Document fetch(String url) {
        return cache.computeIfAbsent(url, this::download);
    }

    /**
     * Выполняет HTTP-запрос для загрузки HTML-документа.
     *
     * @param url адрес страницы
     * @return загруженный {@link Document}
     * @throws IllegalStateException при ошибке загрузки
     */
    private Document download(String url) {
        try {
            log.info("GET {}", url);
            Connection connection = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language", "ru,en;q=0.9")
                .timeout(REQUEST_TIMEOUT_MILLIS)
                .ignoreContentType(true)
                .followRedirects(true);
            return connection.get();
        } catch (IOException exception) {
            throw new IllegalStateException("Не удалось загрузить страницу: " + url, exception);
        }
    }
}
