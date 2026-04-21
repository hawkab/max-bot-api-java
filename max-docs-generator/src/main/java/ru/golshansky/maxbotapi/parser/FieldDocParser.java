package ru.golshansky.maxbotapi.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.golshansky.maxbotapi.domain.FieldDoc;
import ru.golshansky.maxbotapi.support.ParsingUtils;

/**
 * Парсер описаний полей из текстового представления документации.
 * Преобразует строки в список объектов {@link FieldDoc}.
 *
 * @author g.olshansky
 * @since 21.04.2026
 */
@Component
public class FieldDocParser {

    /**
     * Парсит список строк и извлекает описания полей.
     * Определяет имя, тип, описание и метаданные поля (optional, nullable,
     * default, enum, диапазон, шаблон).
     *
     * @param lines строки документации
     * @return список объектов {@link FieldDoc}
     */
    public List<FieldDoc> parse(List<String> lines) {
        var fields = new ArrayList<FieldDoc>();
        var index = 0;
        while (index < lines.size()) {
            var line = ParsingUtils.normalizeInlineCode(lines.get(index));
            var nextLine = index + 1 < lines.size() ? ParsingUtils.normalizeInlineCode(lines.get(index + 1)) : "";
            if (!ParsingUtils.looksLikeFieldName(line) || !ParsingUtils.looksLikeTypeLine(nextLine)) {
                index++;
                continue;
            }

            var field = new FieldDoc(line, nextLine);
            index += 2;
            var descriptionParts = new ArrayList<String>();
            var metadataParts = new ArrayList<String>();
            metadataParts.add(nextLine);

            while (index < lines.size()) {
                var candidate = ParsingUtils.normalizeInlineCode(lines.get(index));
                var candidateNext = index + 1 < lines.size() ? ParsingUtils.normalizeInlineCode(lines.get(index + 1)) : "";
                if (ParsingUtils.looksLikeFieldName(candidate) && ParsingUtils.looksLikeTypeLine(candidateNext)) {
                    break;
                }
                metadataParts.add(candidate);

                var defaultValue = ParsingUtils.extractDefaultValue(candidate);
                if (defaultValue != null) {
                    field.setDefaultValue(defaultValue);
                    index++;
                    continue;
                }

                var pattern = ParsingUtils.extractPatternHint(candidate);
                if (pattern != null && field.getPattern() == null) {
                    field.setPattern(pattern);
                    index++;
                    continue;
                }

                var rangeHint = ParsingUtils.extractRangeHint(candidate);
                if (rangeHint != null && field.getRangeHint() == null) {
                    field.setRangeHint(rangeHint);
                    index++;
                    continue;
                }

                var enumValues = ParsingUtils.extractEnumValues(candidate);
                if (!enumValues.isEmpty()) {
                    for (var enumValue : enumValues) {
                        if (!field.getEnumValues().contains(enumValue)) {
                            field.getEnumValues().add(enumValue);
                        }
                    }
                    index++;
                    continue;
                }

                if (!ParsingUtils.isFieldMetadataLine(candidate)) {
                    descriptionParts.add(candidate);
                }
                index++;
            }

            var metadataText = String.join(" ", metadataParts);
            field.setOptional(ParsingUtils.containsOptionalMarker(metadataText));
            field.setNullable(ParsingUtils.containsNullableMarker(metadataText));
            if (field.getRangeHint() == null) {
                field.setRangeHint(ParsingUtils.extractRangeHint(metadataText));
            }
            if (field.getPattern() == null) {
                field.setPattern(ParsingUtils.extractPatternHint(metadataText));
            }
            if (field.getDefaultValue() == null) {
                field.setDefaultValue(ParsingUtils.extractDefaultValue(metadataText));
            }
            field.setDescription(ParsingUtils.cleanupFieldDescription(descriptionParts));
            fields.add(field);
        }
        return fields;
    }
}
