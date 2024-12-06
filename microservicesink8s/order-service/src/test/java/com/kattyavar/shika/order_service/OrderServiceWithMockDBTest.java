package com.kattyavar.shika.order_service;

import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import com.kattyavar.shika.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceWithMockDBTest {


  @Autowired
  OrderService orderService;

  @MockBean
  private OrderRepo orderRepo;

  @Test
  public void getOrderWithMockRepo() {

    //Here you can set the what ever response you want give it back to db method.
    OrderResponse mockResponse = new OrderResponse("1", null);
    Mockito.when(orderRepo.findByUserId("1")).thenReturn(null);

    OrderResponse orderResponse = orderService.getOrderByUserId("1");
    System.out.println("Please check the response...");
    System.out.println(orderResponse);
    assertEquals("1", orderResponse.userId());
    assertNotNull(orderResponse.orders());

  }
}
