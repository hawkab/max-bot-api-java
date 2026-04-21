package ru.golshansky.maxbotapi.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FieldDoc {

    String name;
    String rawType;
    @Builder.Default
    String description = "";
    boolean optional;
    boolean nullable;
    String defaultValue;
    @Builder.Default
    List<String> enumValues = new ArrayList<>();
    String rangeHint;
    String pattern;

    public FieldDoc(String name, String rawType) {
        this.name = name;
        this.rawType = rawType;
        this.description = "";
        this.enumValues = new ArrayList<>();
    }

}
