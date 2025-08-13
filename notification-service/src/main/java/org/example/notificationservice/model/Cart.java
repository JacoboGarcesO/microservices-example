package org.example.notificationservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class Cart {
  private Long cartId;
  private String userEmail;
  private LocalDateTime createdDate;
  private List<Product> products;

  public Cart() {
  }

  public Cart(Long cartId, String userEmail, LocalDateTime createdDate, List<Product> products) {
    this.cartId = cartId;
    this.userEmail = userEmail;
    this.createdDate = createdDate;
    this.products = products;
  }

  public Long getCartId() {
    return cartId;
  }

  public void setCartId(Long cartId) {
    this.cartId = cartId;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
