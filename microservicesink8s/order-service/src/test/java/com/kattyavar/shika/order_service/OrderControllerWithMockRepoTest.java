package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kattyavar.shika.order_service.controller.OrderInfo;
import com.kattyavar.shika.order_service.controller.OrderRequest;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.entity.OrderEntity;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerWithMockRepoTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private OrderRepo orderRepo;

  @Test
  void createOrderWithMockTest() throws Exception {

    //Set up the Mock
    List<OrderEntity> ordersMockEntities = new ArrayList<>();
    ordersMockEntities.add(new OrderEntity(999, "2", LocalDateTime.now(), 100));

    Mockito.when(orderRepo.saveAll(Mockito.anyList())).thenReturn(ordersMockEntities);

    //Now call the end point ..
    //First we need to build request object

    List<OrderInfo> requestOrderInfos = new ArrayList<>();
    requestOrderInfos.add(new OrderInfo(null, LocalDateTime.now(), 300));
    OrderRequest orderRequest = new OrderRequest(1, requestOrderInfos);

    String orderRequestJson = objectMapper.writeValueAsString(orderRequest);

    MvcResult result = mockMvc.perform(post("/api/v1/users/1/orders")
        .contentType("application/json")
        .content(orderRequestJson))
      .andReturn();

    assertEquals(200, result.getResponse().getStatus());

    String responseBody = result.getResponse().getContentAsString();
    //Now convert the string to object
    OrderResponse orderResponse = objectMapper.readValue(responseBody, OrderResponse.class);
    //Now you can verify the object ...

    System.out.println(orderResponse);

    assertEquals("1", orderResponse.userId());
  }
}
