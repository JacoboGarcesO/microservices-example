package org.example.cartservice.integration;

import org.checkerframework.checker.units.qual.C;
import org.example.cartservice.dto.AddProductDTO;
import org.example.cartservice.dto.CartDTO;
import org.example.cartservice.dto.GetProductDTO;
import org.example.cartservice.grpc.ProductConsumer;
import org.example.cartservice.model.Cart;
import org.example.cartservice.model.Product;
import org.example.cartservice.repository.ICartRepository;
import org.example.cartservice.repository.IProductRepository;
import org.example.cartservice.service.CartService;
import org.example.cartservice.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class CartServiceIntegrationTest {
  @Autowired
  private CartService cartService;
  @Autowired
  private ICartRepository  cartRepository;
  @Autowired
  private IProductRepository productRepository;

  @Autowired
  private DatabaseClient databaseClient;

  private ProductConsumer productConsumer;

  @BeforeEach
  void setUp() {
    productConsumer = Mockito.mock(ProductConsumer.class);
    ReflectionTestUtils.setField(cartService, "productConsumer", productConsumer);
    databaseClient.sql("DELETE FROM carts").then().block();
    databaseClient.sql("DELETE FROM products").then().block();

    cartRepository.save(new Cart(null, "jacobo@gmail.com", LocalDateTime.now())).block();
    cartRepository.save(new Cart(null, "jacobo2@gmail.com", LocalDateTime.now())).block();
    cartRepository.save(new Cart(null, "jacobo3@gmail.com", LocalDateTime.now())).block();
    cartRepository.save(new Cart(null, "jacobo4@gmail.com", LocalDateTime.now())).block();

    productRepository.save(new Product(null, "Jabon", "Jabon desc", 10, 10.0F, 1L)).block();
  }

  @Test
  void addProductToCart() {
    Mockito.when(productConsumer.getProduct(1L)).thenReturn(Mono.just(new GetProductDTO(1L, "Product 1", "Description 1", 10, 10.0F)));

    Mono<CartDTO> result = cartService.addProduct(new AddProductDTO(1L, 2), "jacobo@gmail.com");

    StepVerifier
      .create(result)
      .assertNext(cart -> {
        Assertions.assertEquals("jacobo@gmail.com", cart.getUserEmail());
        Assertions.assertEquals(2, cart.getProducts().size());
      })
      .verifyComplete();

    StepVerifier
      .create(productRepository.findAllByCartId(1L))
        .assertNext(product -> {
          Assertions.assertEquals("Jabon", product.getName());
        })
      .assertNext(product -> {
        Assertions.assertEquals("Product 1", product.getName());
      })
      .verifyComplete();

    Mockito.verify(productConsumer).getProduct(1L);
  }
}
