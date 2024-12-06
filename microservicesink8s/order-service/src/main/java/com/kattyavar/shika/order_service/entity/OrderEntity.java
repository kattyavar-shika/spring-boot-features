package com.kattyavar.shika.order_service.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer orderId;

  String userId;

  LocalDateTime orderDate;

  double totalPrice;

  public OrderEntity(Integer orderId, String userId, LocalDateTime orderDate, double totalPrice) {
    this.orderId = orderId;
    this.userId = userId;
    this.orderDate = orderDate;
    this.totalPrice = totalPrice;
  }

  public OrderEntity() {
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
