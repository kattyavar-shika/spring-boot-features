package com.kattyavar.shika.users_service.model;

import com.kattyavar.shika.users_service.service.integeration.dto.OrderInfo;

import java.util.List;

public record UserOrderResponse(UserInfo userInfo, List<OrderInfo> orders) {
}
