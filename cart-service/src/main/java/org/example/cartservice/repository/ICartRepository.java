package org.example.cartservice.repository;

import org.example.cartservice.model.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ICartRepository extends ReactiveCrudRepository<Cart, Long> {
  Mono<Cart> findByUserEmail(String userEmail);
}
