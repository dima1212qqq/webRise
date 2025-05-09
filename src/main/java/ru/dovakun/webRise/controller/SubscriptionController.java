package ru.dovakun.webRise.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dovakun.webRise.dto.SubscriptionDTO;
import ru.dovakun.webRise.entity.Subscription;
import ru.dovakun.webRise.service.SubscriptionService;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription API", description = "API для управления подписками пользователей")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Добавить подписку", description = "Добавляет подписку для пользователя")
    @ApiResponse(responseCode = "200", description = "Подписка добавлена")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @PostMapping
    public Mono<ResponseEntity<Subscription>> addSubscription(@PathVariable Long userId, @Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.addSubscription(userId, subscriptionDTO)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @Operation(summary = "Получить подписки пользователя", description = "Возвращает список подписок пользователя")
    @ApiResponse(responseCode = "200", description = "Список подписок")
    @GetMapping
    public Flux<Subscription> getUserSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    @Operation(summary = "Удалить подписку", description = "Удаляет подписку по ID")
    @ApiResponse(responseCode = "200", description = "Подписка удалена")
    @ApiResponse(responseCode = "404", description = "Подписка или пользователь не найдены")
    @DeleteMapping("/{subscriptionId}")
    public Mono<ResponseEntity<Void>> deleteSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId) {
        return subscriptionService.deleteSubscription(userId, subscriptionId)
                .map(deleted -> deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build());
    }
}