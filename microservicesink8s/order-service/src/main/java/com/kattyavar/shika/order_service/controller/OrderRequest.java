package com.kattyavar.shika.order_service.controller;

import java.util.List;

public record OrderRequest(Integer userId, List<OrderInfo> orders
) {
}
