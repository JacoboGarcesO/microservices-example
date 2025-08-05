package org.example.productsservice.repository;

import org.example.productsservice.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IProductsRepository extends ReactiveCrudRepository<Product, Long> {
}
