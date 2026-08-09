package org.example.hexlet.dto.users;
import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditUserPage {
    private Long id;
    private String name;
    private String email;
    private Map<String, List<ValidationError<Object>>> errors;
}
