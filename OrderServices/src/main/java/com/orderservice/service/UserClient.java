package com.orderservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.orderservice.dto.*;
import java.util.List;

@Service
public class UserClient {

    private final WebClient webClient;
    private final FallbackService fallbackService; 
    
    UserClient(WebClient webClient,FallbackService fallbackService){
    	this.fallbackService=fallbackService;
    	this.webClient=webClient;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "getUserByIdFallback")
    public UserResponse getUserById(Long userId) {
        return webClient.get()
                .uri("http://USERSERVICE/user/getone/{id}", userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "getProductByIdFallback")
    public ProductResponse getProductById(Long productId) {
        return webClient.get()
                .uri("http://PRODUCTSERVICE/products/getone/{id}", productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "checkStockFallback")
    public List<InventoryCheckResponseDTO> checkStock(List<InventoryCheckRequestDTO> request) {
        return webClient.post()
                .uri("http://INVENTORY-SERVICE/Inventory/check")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(InventoryCheckResponseDTO.class)
                .collectList()
                .block();
    }
 // ... baki methods ...

    @CircuitBreaker(name = "userService", fallbackMethod = "deleteCartItemFallback")
    public void deleteCartItem(Long userId) {
        webClient
             .delete()
             .uri("http://USERSERVICE/user/deleteCartItem/{id}", userId)
             .retrieve()
             .toBodilessEntity()
             .block();
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "reduceStockFallback")
    public void reduceStock(List<InventoryReduceRequestDTO> request) {
        webClient.put()
                .uri("http://INVENTORY-SERVICE/Inventory/ReduceQuantity")
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    // --- FALLBACK REDIRECTS FOR VOID METHODS ---

    public void deleteCartItemFallback(Long userId, Throwable t) {
        fallbackService.deleteCartItemFallback(userId, t);
    }

    public void reduceStockFallback(List<InventoryReduceRequestDTO> request, Throwable t) {
        fallbackService.reduceStockFallback(request, t);
    }

    // --- FALLBACK REDIRECTS ---
    // These must match the signature of the original method + Exception
    
    public UserResponse getUserByIdFallback(Long userId, Throwable t) {
        return fallbackService.getUserByIdFallback(userId, t);
    }

    public ProductResponse getProductByIdFallback(Long productId, Throwable t) {
        return fallbackService.getProductByIdFallback(productId, t);
    }

    public List<InventoryCheckResponseDTO> checkStockFallback(List<InventoryCheckRequestDTO> request, Throwable t) {
        return fallbackService.checkStockFallback(request, t);
    }
}