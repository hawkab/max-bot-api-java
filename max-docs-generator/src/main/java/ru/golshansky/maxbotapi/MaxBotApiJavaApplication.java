package ru.golshansky.maxbotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Точка входа в Spring Boot приложение MAX Bot API Java.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class MaxBotApiJavaApplication {

    /**
     * Запускает Spring Boot приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(MaxBotApiJavaApplication.class, args);
    }
}
