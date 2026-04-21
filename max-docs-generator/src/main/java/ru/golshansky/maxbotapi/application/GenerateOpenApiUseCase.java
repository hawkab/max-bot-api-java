package ru.golshansky.maxbotapi.application;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.golshansky.maxbotapi.domain.MethodDoc;
import ru.golshansky.maxbotapi.domain.ObjectDoc;
import ru.golshansky.maxbotapi.openapi.OpenApiBuilder;
import ru.golshansky.maxbotapi.openapi.OpenApiParityMerger;
import ru.golshansky.maxbotapi.parser.MaxDocsParser;

/**
 * Use case для генерации OpenAPI-спецификации на основе документации MAX API.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Service
public class GenerateOpenApiUseCase {

    private static final Logger log = LoggerFactory.getLogger(GenerateOpenApiUseCase.class);

    private final MaxDocsParser parser;
    private final OpenApiBuilder openApiBuilder;
    private final OpenApiParityMerger openApiParityMerger;

    public GenerateOpenApiUseCase(MaxDocsParser parser, OpenApiBuilder openApiBuilder, OpenApiParityMerger openApiParityMerger) {
        this.parser = parser;
        this.openApiBuilder = openApiBuilder;
        this.openApiParityMerger = openApiParityMerger;
    }

    /**
     * Выполняет полный процесс генерации OpenAPI:
     * сбор ссылок, парсинг методов и объектов, разрешение зависимостей и построение схемы.
     *
     * @param rootUrl корневой URL документации
     * @return OpenAPI-спецификация в виде Map
     * @throws IllegalStateException если не найдены ссылки на методы API
     */
    public Map<String, Object> execute(String rootUrl) {
        var rootLinks = parser.collectRootLinks(rootUrl);
        var methodUrls = rootLinks.methodUrls();
        if (methodUrls.isEmpty()) {
            throw new IllegalStateException("Не удалось найти ссылки на методы API на главной странице документации");
        }

        Set<String> objectUrls = new LinkedHashSet<>(rootLinks.objectUrls());
        objectUrls.addAll(parser.collectObjectLinksFromPages(rootUrl, methodUrls));
        objectUrls.addAll(parser.collectObjectLinksFromPages(rootUrl, new ArrayList<>(objectUrls)));

        List<ObjectDoc> objects = new ArrayList<>();
        Set<String> parsedObjectUrls = new LinkedHashSet<>();
        Set<String> parsedObjectNames = new LinkedHashSet<>();
        for (var objectUrl : objectUrls) {
            try {
                var objectDoc = parser.parseObjectPage(objectUrl, null);
                if (objectDoc != null && parsedObjectNames.add(objectDoc.getName())) {
                    objects.add(objectDoc);
                    parsedObjectUrls.add(objectUrl);
                }
            } catch (Exception exception) {
                log.warn("object parse failed {}: {}", objectUrl, exception.getMessage());
            }
        }

        List<MethodDoc> methods = new ArrayList<>();
        for (var methodUrl : methodUrls) {
            try {
                var methodDoc = parser.parseMethodPage(methodUrl);
                if (methodDoc != null) {
                    methods.add(methodDoc);
                }
            } catch (Exception exception) {
                log.warn("method parse failed {}: {}", methodUrl, exception.getMessage());
            }
        }

        parser.resolveHiddenObjectDocs(rootUrl, objects, methods, parsedObjectNames, parsedObjectUrls);
        var generated = openApiBuilder.build(rootUrl, methods, objects);
        return openApiParityMerger.mergeWithBaseline(generated);
    }
}
