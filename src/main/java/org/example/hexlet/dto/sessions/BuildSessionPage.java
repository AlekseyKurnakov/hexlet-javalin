package org.example.hexlet.dto.sessions;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildSessionPage {
    private String nickname;
    private Map<String, List<ValidationError<Object>>> errors;
}