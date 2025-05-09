package ru.dovakun.webRise.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.dovakun.webRise.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}