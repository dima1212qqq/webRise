package ru.dovakun.webRise.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO для топ-подписок")
public class TopSubscriptionDTO {
    @Schema(description = "Название сервиса", example = "YouTube")
    private String serviceName;

    @Schema(description = "Количество подписок", example = "3")
    private Long count;
}