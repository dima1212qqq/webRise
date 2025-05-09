package ru.dovakun.webRise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.dovakun.webRise.dto.TopSubscriptionDTO;
import ru.dovakun.webRise.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Top Subscriptions API", description = "API для получения популярных подписок")
public class TopSubscriptionsController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получить топ подписок", description = "Возвращает список самых популярных подписок")
    @ApiResponse(responseCode = "200", description = "Список топ подписок")
    @GetMapping("/top")
    public Flux<TopSubscriptionDTO> getTopSubscriptions() {
        return subscriptionService.getTopSubscriptions();
    }
}