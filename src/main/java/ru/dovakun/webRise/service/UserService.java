package ru.dovakun.webRise.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.dovakun.webRise.dto.UserDTO;
import ru.dovakun.webRise.entity.User;
import ru.dovakun.webRise.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Mono<User> createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public Mono<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    user.setName(userDTO.getName());
                    user.setEmail(userDTO.getEmail());
                    return userRepository.save(user);
                });
    }

    public Mono<Boolean> deleteUser(Long id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user).thenReturn(true))
                .switchIfEmpty(Mono.just(false));
    }
}