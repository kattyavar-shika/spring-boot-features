package com.kattyavar.shika.users_service.service.integeration.dto;

import java.time.LocalDateTime;

public record OrderInfo(String orderId, LocalDateTime orderDate, double totalPrice) {
}
