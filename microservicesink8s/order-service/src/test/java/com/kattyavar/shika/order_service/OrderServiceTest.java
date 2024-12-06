package com.kattyavar.shika.order_service;

import com.kattyavar.shika.order_service.controller.OrderInfo;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import com.kattyavar.shika.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Test
  public void getOrderTest() {
    OrderResponse orderResponse = orderService.getOrderByUserId("1");
    System.out.println("We got the data as ..");

    assertNotNull(orderResponse, "Order response should not be null...");

    //To verify specific filed.
    assertEquals("1", orderResponse.userId());

    List<OrderInfo> orderInfos = orderResponse.orders();

    //To check object is not null...
    assertNotNull(orderInfos);

    //Orders should not be empty
    assertFalse(orderInfos.isEmpty());

    OrderInfo firstOrder = orderInfos.get(0);
    assertNotNull(firstOrder, "First order should not be null");
    assertEquals(10.0, firstOrder.totalPrice());
  }
}
