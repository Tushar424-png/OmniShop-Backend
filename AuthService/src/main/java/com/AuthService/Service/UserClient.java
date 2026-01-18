package com.AuthService.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.AuthService.DTO.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackAddUser")
    public void AddUser(UserResponse user) {
        webClient
            .post()
            .uri("http://USERSERVICE/user/add")
            .bodyValue(user)
            .retrieve()
            .toBodilessEntity()
            .block();  // still blocking, can switch to reactive if you want
    }

    // Fallback method
    public void fallbackAddUser(UserResponse user, Throwable throwable) {
        System.out.println("UserService is down, fallback triggered for user: " + user.getEmail());
        // You can log or store in Redis queue for retry later
    }
}
