package org.example.cartservice.service;

import org.example.cartservice.dto.CartDTO;
import org.example.cartservice.grpc.ProductConsumer;
import org.example.cartservice.model.Cart;
import org.example.cartservice.model.Product;
import org.example.cartservice.repository.ICartRepository;
import org.example.cartservice.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {
  private ICartRepository cartRepository;
  private IProductRepository productRepository;
  private ProductService productService;
  private CartOrdersPublisher cartOrdersPublisher;
  private ProductConsumer productConsumer;
  private CartService cartService;

  public CartServiceTest() {
    cartRepository = Mockito.mock(ICartRepository.class);
    productRepository = Mockito.mock(IProductRepository.class);
    productService = Mockito.mock(ProductService.class);
    cartOrdersPublisher = Mockito.mock(CartOrdersPublisher.class);
    productConsumer = Mockito.mock(ProductConsumer.class);
    cartService = new CartService(productRepository, cartRepository, productService, productConsumer, cartOrdersPublisher);
  }

  @Test
  void purchase() {
    // Arrange
    Cart cart = new Cart(1L, "jacobo@gmail.com", LocalDateTime.now());
    Mockito.when(cartRepository.findByUserEmail(Mockito.anyString())).thenReturn(Mono.just(cart));
    Mockito.when(productRepository.findAllByCartId(Mockito.anyLong())).thenReturn(Flux.just(
      new Product(1L, "Cocacola", "Cocacola desc", 20, 10.0f, 1L),
      new Product(2L, "Cocacola1", "Cocacola desc", 20, 10.0f, 1L),
      new Product(3L, "Cocacola2", "Cocacola desc", 20, 10.0f, 1L),
      new Product(4L, "Cocacola3", "Cocacola desc", 20, 10.0f, 1L),
      new Product(5L, "Cocacola4", "Cocacola desc", 20, 10.0f, 1L),
      new Product(6L, "Cocacola5", "Cocacola desc", 20, 10.0f, 1L)
    ));

    // Act
    Mono<CartDTO> result = cartService.purchase("jacobo@gmail.com");

    StepVerifier
      .create(result)
      .assertNext((cartDTO -> {
        // Assert
        Assertions.assertEquals(cart.getId(), cartDTO.getCartId());
        Assertions.assertEquals(cart.getUserEmail(), cartDTO.getUserEmail());
        Assertions.assertEquals(6, cartDTO.getProducts().size());
      }))
      .verifyComplete();

    // Assert
    Mockito.verify(productRepository).findAllByCartId(Mockito.anyLong());
    Mockito.verify(cartRepository).findByUserEmail(Mockito.anyString());
  }
}