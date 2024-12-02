package com.kattyavar.shika.user_service.model;

public record UserOrderResponse(String ip, String port, String serviceName, OrderResponse orderResponse) {
}
