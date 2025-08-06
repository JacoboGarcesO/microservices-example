package org.example.cartservice.service;

import org.example.cartservice.dto.AddProductDTO;
import org.example.cartservice.dto.CartDTO;
import org.example.cartservice.dto.ProductDTO;
import org.example.cartservice.model.Cart;
import org.example.cartservice.model.Product;
import org.example.cartservice.repository.ICartRepository;
import org.example.cartservice.repository.IProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CartService {
  private final IProductRepository productRepository;
  private final ICartRepository cartRepository;
  private final ProductService productService;

  public CartService(IProductRepository productRepository, ICartRepository cartRepository, ProductService productService) {
    this.productRepository = productRepository;
    this.cartRepository = cartRepository;
    this.productService = productService;
  }

  public Mono<Cart> create(String email) {
    return this.cartRepository.save(new Cart(null, email, LocalDateTime.now()));
  }

  public Mono<CartDTO> addProduct(AddProductDTO addProductDTO, String userEmail) {
    return productService.getProduct(addProductDTO.getProductId())
      .flatMap(product -> {
        if (product.getStock() < addProductDTO.getQuantity()) {
          return Mono.error(new RuntimeException("Not enough stock"));
        }

        return cartRepository.findByUserEmail(userEmail)
          .flatMap(cart ->
            productRepository
              .save(new Product(null, product.getName(), product.getDescription(), addProductDTO.getQuantity(), product.getPrice(), cart.getId()))
              .flatMap(newProduct -> productRepository
                .findAllByCartId(cart.getId())
                .map(pro -> new ProductDTO(pro.getId(), pro.getName(), pro.getQuantity(), pro.getPrice() * pro.getQuantity()))
                .collectList()
                .map(products -> new CartDTO(cart.getId(), cart.getUserEmail(), cart.getCreatedDate(), products)))
          );
      })
      .onErrorResume(error -> Mono.error(new RuntimeException(error.getMessage())));
  }


}
