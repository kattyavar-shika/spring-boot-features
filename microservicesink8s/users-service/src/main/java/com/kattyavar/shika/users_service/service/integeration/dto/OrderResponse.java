package com.kattyavar.shika.users_service.service.integeration.dto;

import java.util.List;

public record OrderResponse(String userId, List<OrderInfo> orders
) {
}
