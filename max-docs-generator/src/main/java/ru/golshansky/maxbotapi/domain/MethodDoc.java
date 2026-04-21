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
public class MethodDoc {

    String url;
    String method;
    String path;
    String summary;
    String description;
    @Builder.Default
    List<FieldDoc> params = new ArrayList<>();
    @Builder.Default
    List<FieldDoc> body = new ArrayList<>();
    @Builder.Default
    List<FieldDoc> result = new ArrayList<>();
    @Builder.Default
    String tag = "default";

}
