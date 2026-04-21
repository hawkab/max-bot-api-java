package ru.golshansky.maxbotapi.cli;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import ru.golshansky.maxbotapi.MaxBotApiJavaApplication;
import ru.golshansky.maxbotapi.application.MaxDocsGenerationService;
import ru.golshansky.maxbotapi.config.MaxDocsProperties;

/**
 * CLI-приложение для генерации OpenAPI-спецификации.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
public final class MaxDocsSpecCli {

    private MaxDocsSpecCli() {
    }

    /**
     * Точка входа CLI-приложения.
     * Запускает Spring-контекст, читает параметры, выполняет генерацию и выводит результат.
     *
     * @param args аргументы командной строки в формате --key=value
     */
    public static void main(String[] args) {
        var cliArgs = parseArgs(args);
        try (ConfigurableApplicationContext context = new SpringApplicationBuilder(MaxBotApiJavaApplication.class)
            .web(WebApplicationType.NONE)
            .run()) {

            var properties = context.getBean(MaxDocsProperties.class);
            var generationService = context.getBean(MaxDocsGenerationService.class);

            var rootUrl = cliArgs.getOrDefault("root-url", properties.getRootUrl());
            var output = cliArgs.getOrDefault("output", properties.getOutputFile());
            if (cliArgs.containsKey("pause")) {
                properties.setPauseSeconds(Double.parseDouble(cliArgs.get("pause")));
            }

            var result = generationService.generate(rootUrl, Path.of(output));
            System.out.println("OpenAPI YAML saved to " + output);
            System.out.println(result.summary());
        }
    }

    /**
     * Парсит аргументы командной строки в формат ключ-значение.
     *
     * @param args аргументы командной строки
     * @return карта параметров
     */
    private static Map<String, String> parseArgs(String[] args) {
        var values = new HashMap<String, String>();
        for (var arg : args) {
            if (arg == null || !arg.startsWith("--") || !arg.contains("=")) {
                continue;
            }
            var index = arg.indexOf('=');
            values.put(arg.substring(2, index), arg.substring(index + 1));
        }
        return values;
    }
}
