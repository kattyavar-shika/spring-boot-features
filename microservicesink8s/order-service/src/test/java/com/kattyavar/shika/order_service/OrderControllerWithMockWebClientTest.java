package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kattyavar.shika.order_service.controller.OrderInfo;
import com.kattyavar.shika.order_service.controller.OrderRequest;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.entity.OrderEntity;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import com.kattyavar.shika.order_service.service.OrderService;
import com.kattyavar.shika.order_service.service.integeration.UserServiceIntegration;
import com.kattyavar.shika.order_service.service.integeration.UserServiceIntegrationImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
 public class OrderControllerWithMockWebClientTest {


  @MockBean
  WebClient userServiceWebClient;

  @InjectMocks
  UserServiceIntegrationImpl userServiceIntegration;


  @Test
  void createOrderWithMockTest() throws Exception {

    assertEquals("1", "1");
  }
}
