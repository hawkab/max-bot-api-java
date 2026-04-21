package ru.golshansky.maxbotapi.output;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

/**
 * Компонент для записи данных в YAML-файл.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class YamlWriter {

    /**
     * Записывает данные в YAML-файл.
     * Создаёт директории при необходимости, выполняет сериализацию с заданными настройками форматирования.
     *
     * @param outputFile путь к выходному файлу
     * @param data данные для записи
     * @throws IllegalStateException при ошибке записи файла
     */
    public void write(Path outputFile, Map<String, Object> data) {
        try {
            Path parent = outputFile.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);
            options.setIndent(2);
            options.setIndicatorIndent(1);
            options.setWidth(120);

            Yaml yaml = new Yaml(options);
            try (Writer writer = new OutputStreamWriter(Files.newOutputStream(outputFile), StandardCharsets.UTF_8)) {
                yaml.dump(toPlainObject(data), writer);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Не удалось записать YAML в " + outputFile, exception);
        }
    }

    /**
     * Преобразует структуру данных в примитивное представление,
     * совместимое с YAML-сериализацией.
     * Рекурсивно обрабатывает Map и Iterable.
     *
     * @param value исходное значение
     * @return преобразованное значение
     */
    private Object toPlainObject(Object value) {
        if (value instanceof Map<?, ?> map) {
            Map<String, Object> plain = new LinkedHashMap<>();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                plain.put(String.valueOf(entry.getKey()), toPlainObject(entry.getValue()));
            }
            return plain;
        }
        if (value instanceof Iterable<?> iterable) {
            var list = new ArrayList<>();
            for (Object item : iterable) {
                list.add(toPlainObject(item));
            }
            return list;
        }
        return value;
    }
}
