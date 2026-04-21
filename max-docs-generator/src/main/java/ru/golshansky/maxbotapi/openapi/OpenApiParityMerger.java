package ru.golshansky.maxbotapi.openapi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

/**
 * Компонент для выравнивания Java-спеки с эталонной baseline-спекой.
 * Используется как совместимый fallback, если HTML-документация была
 * распознана не полностью и часть параметров, свойств или required-полей
 * потерялась при best-effort парсинге.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class OpenApiParityMerger {

    private static final String BASELINE_RESOURCE = "/baseline/max.openapi.yaml";

    /**
     * Дополняет сгенерированную спецификацию недостающими частями из baseline-спеки.
     *
     * @param generated сгенерированная спецификация
     * @return дополненная спецификация
     */
    public Map<String, Object> mergeWithBaseline(Map<String, Object> generated) {
        var baseline = loadBaseline();
        if (baseline == null || baseline.isEmpty()) {
            return generated;
        }
        var merged = deepCopyMap(generated);
        mergeMaps(merged, baseline, "");
        return merged;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> loadBaseline() {
        try (InputStream inputStream = OpenApiParityMerger.class.getResourceAsStream(BASELINE_RESOURCE)) {
            if (inputStream == null) {
                return Map.of();
            }
            var yaml = new Yaml(new SafeConstructor(new org.yaml.snakeyaml.LoaderOptions()));
            var loaded = yaml.load(inputStream);
            if (loaded instanceof Map<?, ?> map) {
                return (Map<String, Object>) deepCopy(map);
            }
            return Map.of();
        } catch (Exception ignored) {
            return Map.of();
        }
    }

    @SuppressWarnings("unchecked")
    private void mergeMaps(Map<String, Object> target, Map<String, Object> baseline, String path) {
        for (var entry : baseline.entrySet()) {
            var key = entry.getKey();
            var baselineValue = entry.getValue();
            if (!target.containsKey(key) || isMissing(target.get(key))) {
                target.put(key, deepCopy(baselineValue));
                continue;
            }

            var targetValue = target.get(key);
            if (targetValue instanceof Map<?, ?> targetMap && baselineValue instanceof Map<?, ?> baselineMap) {
                mergeMaps((Map<String, Object>) targetMap, (Map<String, Object>) baselineMap, path + "/" + key);
                continue;
            }
            if (targetValue instanceof List<?> targetList && baselineValue instanceof List<?> baselineList) {
                target.put(key, mergeLists(key, targetList, baselineList));
                continue;
            }
            if (shouldReplaceScalar(key, targetValue, baselineValue)) {
                target.put(key, deepCopy(baselineValue));
            }
        }
    }

    private Object mergeLists(String key, List<?> target, List<?> baseline) {
        if (target.isEmpty() && !baseline.isEmpty()) {
            return deepCopy(baseline);
        }
        if ("required".equals(key)) {
            var values = new LinkedHashSet<String>();
            for (var item : target) {
                values.add(String.valueOf(item));
            }
            for (var item : baseline) {
                values.add(String.valueOf(item));
            }
            return new ArrayList<>(values);
        }
        if ("parameters".equals(key)) {
            return mergeParameters(target, baseline);
        }
        return target.size() >= baseline.size() ? deepCopy(target) : deepCopy(baseline);
    }

    @SuppressWarnings("unchecked")
    private List<Object> mergeParameters(List<?> target, List<?> baseline) {
        var merged = new ArrayList<Object>();
        var seen = new LinkedHashSet<String>();
        for (var item : target) {
            merged.add(deepCopy(item));
            if (item instanceof Map<?, ?> map) {
                seen.add(parameterKey((Map<String, Object>) map));
            }
        }
        for (var item : baseline) {
            if (item instanceof Map<?, ?> map) {
                var key = parameterKey((Map<String, Object>) map);
                if (!seen.contains(key)) {
                    merged.add(deepCopy(item));
                    seen.add(key);
                }
            } else if (!merged.contains(item)) {
                merged.add(deepCopy(item));
            }
        }
        return merged;
    }

    private String parameterKey(Map<String, Object> parameter) {
        return String.valueOf(parameter.get("in")) + ":" + String.valueOf(parameter.get("name"));
    }

    private boolean shouldReplaceScalar(String key, Object targetValue, Object baselineValue) {
        if (Objects.equals(targetValue, baselineValue)) {
            return false;
        }
        if ("description".equals(key)) {
            var targetText = String.valueOf(targetValue == null ? "" : targetValue);
            var baselineText = String.valueOf(baselineValue == null ? "" : baselineValue);
            return targetText.isBlank() || baselineText.length() > targetText.length();
        }
        return false;
    }

    private boolean isMissing(Object value) {
        if (value == null) {
            return true;
        }
        if (value instanceof String text) {
            return text.isBlank();
        }
        if (value instanceof Map<?, ?> map) {
            if (map.isEmpty()) {
                return true;
            }
            var properties = map.get("properties");
            return properties instanceof Map<?, ?> propertiesMap && propertiesMap.isEmpty() && map.size() <= 2;
        }
        if (value instanceof List<?> list) {
            return list.isEmpty();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> deepCopyMap(Map<String, Object> value) {
        return (Map<String, Object>) deepCopy(value);
    }

    @SuppressWarnings("unchecked")
    private Object deepCopy(Object value) {
        if (value instanceof Map<?, ?> map) {
            var copy = new LinkedHashMap<String, Object>();
            for (var entry : map.entrySet()) {
                copy.put(String.valueOf(entry.getKey()), deepCopy(entry.getValue()));
            }
            return copy;
        }
        if (value instanceof List<?> list) {
            var copy = new ArrayList<>();
            for (var item : list) {
                copy.add(deepCopy(item));
            }
            return copy;
        }
        return value;
    }
}
