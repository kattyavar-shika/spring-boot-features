package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kattyavar.shika.order_service.repo.OrderRepo;
import com.kattyavar.shika.order_service.service.OrderService;
import com.kattyavar.shika.order_service.service.OrderServiceImpl;
import com.kattyavar.shika.order_service.service.integeration.UserServiceIntegrationImpl;
import com.kattyavar.shika.order_service.service.model.UserAccountInfo;
import com.kattyavar.shika.order_service.service.model.integeration.UserAccountInfoInt;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ActiveProfiles("test")
public class OrderControllerWithMockWebClientTest {


  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  ObjectMapper objectMapper;

  private MockWebServer mockWebServer;
  private OrderService orderService;

  private UserServiceIntegrationImpl userServiceIntegration;


  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("Before each test case...");
    mockWebServer = new MockWebServer();
    mockWebServer.start();

    WebClient webClient = WebClient.create(mockWebServer.url("/").toString());

    userServiceIntegration = new UserServiceIntegrationImpl(webClient);

    orderService = new OrderServiceImpl(orderRepo, userServiceIntegration);
  }

  @AfterEach
  public void tearDown() throws IOException {
    mockWebServer.shutdown();
    System.out.println("In shutdown.");

  }

  @Test
  void createOrderWithMockTest() throws Exception {
    UserAccountInfoInt userAccountInfoInt = new UserAccountInfoInt("This is comming from mock");
    mockWebServer.enqueue(new MockResponse()
      .setBody(objectMapper.writeValueAsString(userAccountInfoInt))
      .addHeader("Content-Type", "application/json"));

    UserAccountInfo userAccount = orderService.getUserAccount("1");
    //Now build the mock response.
    assertEquals("This is comming from mock", userAccount.getName());
  }

  @Test
  void createOrderWithMockTestWithOrder2() throws Exception {
    UserAccountInfoInt userAccountInfoInt = new UserAccountInfoInt("For user 2");
    mockWebServer.enqueue(new MockResponse()
      .setBody(objectMapper.writeValueAsString(userAccountInfoInt))
      .addHeader("Content-Type", "application/json"));
    UserAccountInfo userAccount = orderService.getUserAccount("1");
    //Now build the mock response.
    assertEquals("For user 2", userAccount.getName());
  }
}
