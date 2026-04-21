package ru.golshansky.maxbotapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Конфигурационные свойства генерации документации MAX API.
 * Загружаются из настроек приложения с префиксом {@code max-docs}.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "max-docs")
public class MaxDocsProperties {

    /**
     * Корневой URL документации.
     */
    private String rootUrl = "https://dev.max.ru/docs-api";

    /**
     * Путь к выходному файлу OpenAPI.
     */
    private String outputFile = "target/generated-spec/max.openapi.yaml";

    /**
     * Пауза между запросами при парсинге (в секундах).
     */
    private double pauseSeconds = 0.15d;

}
