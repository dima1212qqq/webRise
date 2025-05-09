package ru.dovakun.webRise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "DTO для создания подписки")
public class SubscriptionDTO {
    @NotBlank(message = "Service name is required")
    @Schema(description = "Название сервиса", example = "YouTube")
    private String serviceName;
}