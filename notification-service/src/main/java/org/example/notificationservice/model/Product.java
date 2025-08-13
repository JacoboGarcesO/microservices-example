package org.example.notificationservice.model;

public class Product {
  private Long productId;
  private String name;
  private Integer quantity;
  private Float totalPrice;

  public Product() {
  }

  public Product(Long productId, String name, Integer quantity, Float totalPrice) {
    this.productId = productId;
    this.name = name;
    this.quantity = quantity;
    this.totalPrice = totalPrice;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Float totalPrice) {
    this.totalPrice = totalPrice;
  }
}
