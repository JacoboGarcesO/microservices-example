package org.example.cartservice.repository;

import org.example.cartservice.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IProductRepository extends ReactiveCrudRepository<Product, Long> {
  Flux<Product> findAllByCartId(Long cartId);
}
