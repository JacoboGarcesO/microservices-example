package org.example.cartservice.service;

import org.example.cartservice.dto.GetProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
  private final WebClient.Builder webClientBuilder;

  @Value("${products-service.url}")
  private String productsServiceUrl;

  public ProductService(final WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  public Mono<GetProductDTO> getProduct(Long productId) {
    return webClientBuilder
      .build()
      .get()
      .uri(productsServiceUrl + "/" + productId)
      .retrieve()
      .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new RuntimeException("Product not found")))
      .bodyToMono(GetProductDTO.class);
  }
}
