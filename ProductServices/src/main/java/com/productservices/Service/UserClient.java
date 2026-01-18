package com.productservices.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.productservices.dto.Requestdto;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // Original Method
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "createInventoryFallback")
    public void createInventoryOnProductCreate(Requestdto request) {
        webClient
            .post()
            .uri("http://Inventory-Service/Inventory/add")
            .bodyValue(request)
            .retrieve()
            .toBodilessEntity()
            .block();
    }

    // Fallback Method (Same class mein)
    public void createInventoryFallback(Requestdto request, Throwable t) {
        // Red color mein print hoga console pe
        System.err.println("CRITICAL ERROR: Inventory Service is DOWN!");
        System.err.println("Product create ho gaya par inventory fail ho gayi.");
        System.err.println("Error Message: " + t.getMessage());
    }
}