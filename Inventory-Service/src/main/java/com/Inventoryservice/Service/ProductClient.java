package com.Inventoryservice.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

	public void deactivateProduct(Long productId) {
		webClient.put()
        .uri("http://PRODUCT-SERVICE/api/products/internal/{id}/deactivate", productId)
        .retrieve()
        .toBodilessEntity()
        .block();
	}
    

}
