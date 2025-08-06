package org.example.cartservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CartDTO {
  private Long cartId;
  private String userEmail;
  private LocalDateTime createdDate;
  private List<ProductDTO> products;

  public CartDTO() {
  }

  public CartDTO(Long cartId, String userEmail, LocalDateTime createdDate, List<ProductDTO> products) {
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

  public List<ProductDTO> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDTO> products) {
    this.products = products;
  }
}
