package ru.golshansky.maxbotapi.application;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ru.golshansky.maxbotapi.output.YamlWriter;

/**
 * Сервис генерации OpenAPI-документации и записи её в YAML-файл.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Service
public class MaxDocsGenerationService {

    private final GenerateOpenApiUseCase useCase;
    private final YamlWriter yamlWriter;

    public MaxDocsGenerationService(GenerateOpenApiUseCase useCase, YamlWriter yamlWriter) {
        this.useCase = useCase;
        this.yamlWriter = yamlWriter;
    }

    /**
     * Выполняет генерацию OpenAPI и записывает результат в файл.
     *
     * @param rootUrl корневой URL документации
     * @param outputFile путь к файлу для записи
     * @return результат генерации
     */
    public GenerationResult generate(String rootUrl, Path outputFile) {
        var openapi = useCase.execute(rootUrl);
        yamlWriter.write(outputFile, openapi);
        return new GenerationResult(rootUrl, outputFile.toString(), openapi);
    }

    /**
     * Результат генерации OpenAPI.
     *
     * @param rootUrl корневой URL документации
     * @param outputFile путь к выходному файлу
     * @param openapi сгенерированная OpenAPI-структура
     */
    public record GenerationResult(String rootUrl, String outputFile, Map<String, Object> openapi) {

        /**
         * Возвращает краткую сводку по сгенерированной спецификации.
         *
         * @return карта с основными параметрами (rootUrl, outputFile, title, version, pathsCount, schemasCount)
         */
        @SuppressWarnings("unchecked")
        public Map<String, Object> summary() {
            var summary = new LinkedHashMap<String, Object>();
            summary.put("rootUrl", rootUrl);
            summary.put("outputFile", outputFile);
            summary.put("title", ((Map<String, Object>) openapi.get("info")).get("title"));
            summary.put("version", ((Map<String, Object>) openapi.get("info")).get("version"));
            summary.put("pathsCount", ((Map<String, Object>) openapi.get("paths")).size());
            summary.put("schemasCount", ((Map<String, Object>) ((Map<String, Object>) openapi.get("components")).get("schemas")).size());
            return summary;
        }
    }
}
