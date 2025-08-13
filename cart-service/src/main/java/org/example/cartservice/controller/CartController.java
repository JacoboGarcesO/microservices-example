package org.example.cartservice.controller;

import org.example.cartservice.dto.AddProductDTO;
import org.example.cartservice.dto.CartDTO;
import org.example.cartservice.model.Cart;
import org.example.cartservice.service.CartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/carts")
public class CartController {
  private final CartService service;

  public CartController(CartService service) {
    this.service = service;
  }

  @PostMapping("/{email}")
  public Mono<Cart> create(@PathVariable String email) {
    return service.create(email);
  }

  @PostMapping("/{email}/products")
  public Mono<CartDTO> addProduct(@PathVariable String email, @RequestBody AddProductDTO addProductDTO) {
    return service.addProduct(addProductDTO, email);
  }

  @PostMapping("/{email}/purchase")
  public Mono<CartDTO> purchase(@PathVariable String email) {
    return service.purchase(email);
  }
}
