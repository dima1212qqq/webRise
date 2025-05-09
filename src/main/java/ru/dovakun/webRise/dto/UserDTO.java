package ru.dovakun.webRise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO для создания/обновления пользователя")
public class UserDTO {
    @NotBlank(message = "Name is required")
    @Schema(description = "Имя пользователя", example = "string")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "Электронная почта", example = "Panteleev207@gmail.com")
    private String email;
}