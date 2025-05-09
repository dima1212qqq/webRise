package ru.dovakun.webRise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dovakun.webRise.dto.SubscriptionDTO;
import ru.dovakun.webRise.dto.TopSubscriptionDTO;
import ru.dovakun.webRise.entity.Subscription;
import ru.dovakun.webRise.repo.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public Mono<Subscription> addSubscription(Long userId, SubscriptionDTO subscriptionDTO) {
        return userService.getUser(userId)
                .flatMap(user -> {
                    Subscription subscription = new Subscription();
                    subscription.setServiceName(subscriptionDTO.getServiceName());
                    subscription.setUserId(userId);
                    return subscriptionRepository.save(subscription);
                });
    }

    public Flux<Subscription> getUserSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public Mono<Boolean> deleteSubscription(Long userId, Long subscriptionId) {
        return subscriptionRepository.findByIdAndUserId(subscriptionId, userId)
                .flatMap(subscription -> subscriptionRepository.delete(subscription).thenReturn(true))
                .switchIfEmpty(Mono.just(false));
    }

    public Flux<TopSubscriptionDTO> getTopSubscriptions() {
        return subscriptionRepository.findTopSubscriptions();
    }
}