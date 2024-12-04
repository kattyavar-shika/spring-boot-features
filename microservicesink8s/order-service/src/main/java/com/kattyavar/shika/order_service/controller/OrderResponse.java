package com.kattyavar.shika.order_service.controller;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(String userId, List<OrderInfo> orders
) {
}
