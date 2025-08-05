package org.example.productsservice.service;

import org.example.productsservice.model.Product;
import org.example.productsservice.repository.IProductsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductsService {
  private final IProductsRepository repository;

  public ProductsService(IProductsRepository repository) {
    this.repository = repository;
  }

  public Flux<Product> getAll() {
    return repository.findAll();
  }

  public Mono<Product> getById(Long productId) {
    return repository
      .findById(productId)
      .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
  }
}
