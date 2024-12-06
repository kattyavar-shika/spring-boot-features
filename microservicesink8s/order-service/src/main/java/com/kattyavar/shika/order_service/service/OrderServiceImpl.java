package com.kattyavar.shika.order_service.service;

import com.kattyavar.shika.order_service.controller.OrderInfo;
import com.kattyavar.shika.order_service.controller.OrderRequest;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.entity.OrderEntity;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import com.kattyavar.shika.order_service.service.integeration.UserServiceIntegration;
import com.kattyavar.shika.order_service.service.model.UserAccountInfo;
import com.kattyavar.shika.order_service.service.model.integeration.UserAccountInfoInt;
import com.kattyavar.shika.order_service.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  UserServiceIntegration userServiceIntegration;

  @Override
  public OrderResponse createOrder(OrderRequest orderRequest) {
    List<OrderEntity> orderEntityList = orderRequest.orders()
      .stream()
      .map(request -> {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(orderRequest.userId().toString());
        orderEntity.setOrderDate(request.orderDate());
        orderEntity.setTotalPrice(request.totalPrice());
        return orderEntity;
      })
      .collect(Collectors.toList());
    List<OrderEntity> ordersInfoResult = orderRepo.saveAll(orderEntityList);
    return new OrderResponse(orderRequest.userId().toString(), convertToOrderInfo(ordersInfoResult));
  }

  @Override
  public OrderResponse getOrderByUserId(String userId) {

    List<OrderEntity> orderEntities = orderRepo.findByUserId(userId);
    return new OrderResponse(userId, convertToOrderInfo(orderEntities));
  }


  @Override
  public UserAccountInfo getUserAccount(String userId) {
    UserAccountInfoInt userAccountInfoInt = userServiceIntegration.getUserAccount(userId);

    UserAccountInfo userAccountInfo = new UserAccountInfo();
    if (userAccountInfo != null) {
      userAccountInfo.setName(userAccountInfoInt.getName());
    }
    return userAccountInfo;
  }

  private List<OrderInfo> convertToOrderInfo(List<OrderEntity> list) {
    return ListUtils.emptyIfNull(list)
      .stream()
      .map(this::convertOrderEntity)
      .toList();
  }

  private OrderInfo convertOrderEntity(OrderEntity entity) {
    return new OrderInfo(entity.getOrderId().toString(), entity.getOrderDate(), entity.getTotalPrice());
  }
}

