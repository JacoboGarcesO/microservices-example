package org.example.productsservice.controller;

import org.example.productsservice.model.Product;
import org.example.productsservice.service.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
  private final ProductsService service;

  public ProductsController(ProductsService service) {
    this.service = service;
  }

  @GetMapping
  public Flux<Product> getAll() {
    return service.getAll();
  }

  @GetMapping("/{productId}")
  public Mono<Product> getById(@PathVariable Long productId) {
    return service.getById(productId);
  }
}
