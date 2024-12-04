package com.kattyavar.shika.order_service.controller;

import java.time.LocalDateTime;

public record OrderInfo(String orderId, LocalDateTime orderDate, double totalPrice) {
}
