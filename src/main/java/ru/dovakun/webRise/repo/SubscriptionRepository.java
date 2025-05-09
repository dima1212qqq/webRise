package ru.dovakun.webRise.repo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.dovakun.webRise.dto.TopSubscriptionDTO;
import ru.dovakun.webRise.entity.Subscription;

public interface SubscriptionRepository extends ReactiveCrudRepository<Subscription, Long> {
    Flux<Subscription> findByUserId(Long userId);

    @Query("SELECT * FROM subscriptions WHERE id = :id AND user_id = :userId")
    Mono<Subscription> findByIdAndUserId(Long id, Long userId);

    @Query("SELECT service_name, COUNT(*) as count FROM subscriptions GROUP BY service_name ORDER BY count DESC LIMIT 5")
    Flux<TopSubscriptionDTO> findTopSubscriptions();
}