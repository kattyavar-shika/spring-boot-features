package com.kattyavar.shika.user_service.model;

import java.util.List;

public record OrderResponse(String ip, String port, String serviceName, List<Order> orders) {
}
