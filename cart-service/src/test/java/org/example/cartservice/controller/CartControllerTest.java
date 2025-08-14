package org.example.cartservice.controller;

import org.example.cartservice.model.Cart;
import org.example.cartservice.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class CartControllerTest {
  private CartService cartService;
  private WebTestClient webTestClient;

  public CartControllerTest() {
    cartService = Mockito.mock(CartService.class);
    CartController controler = new CartController(cartService);
    webTestClient = WebTestClient.bindToController(controler).build();
  }

  @Test
  void create() {
    Cart cart = new Cart(1L, "jacobo@gmail.com", null);
    Mockito.when(cartService.create(Mockito.anyString())).thenReturn(Mono.just(cart));

    webTestClient
      .post()
      .uri("/api/carts/jacobo@gmail.com")
      .exchange()
      .expectStatus().isOk()
      .expectBody(Cart.class)
      .value(cartCreated -> {
        Assertions.assertEquals(1L, cartCreated.getId());
      });

    Mockito.verify(cartService).create(Mockito.anyString());
  }
}