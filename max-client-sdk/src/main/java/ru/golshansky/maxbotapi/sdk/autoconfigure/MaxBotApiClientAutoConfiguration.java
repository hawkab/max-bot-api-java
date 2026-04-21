package ru.golshansky.maxbotapi.sdk.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import ru.golshansky.maxbotapi.sdk.MaxBotApiClientFactory;

/**
 * Автоконфигурация Spring Boot, регистрирующая {@link ru.golshansky.maxbotapi.sdk.MaxBotApiClient}
 * для работы с MAX Bot API.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@AutoConfiguration
public class MaxBotApiClientAutoConfiguration {

    /**
     * Создаёт и регистрирует экземпляр {@link ru.golshansky.maxbotapi.sdk.MaxBotApiClient},
     * настроенный из переменных окружения.
     *
     * @return настроенный экземпляр {@link ru.golshansky.maxbotapi.sdk.MaxBotApiClient}
     * @throws IllegalStateException если не задан обязательный токен MAX Bot API
     */
    @Bean
    ru.golshansky.maxbotapi.sdk.MaxBotApiClient maxBotApiClient() {
        return MaxBotApiClientFactory.fromEnvironment();
    }
}
