package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kattyavar.shika.order_service.service.OrderService;
import com.kattyavar.shika.order_service.service.model.UserAccountInfo;
import com.kattyavar.shika.order_service.service.model.integeration.UserAccountInfoInt;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
public class OrderControllerWithMockWebClientTest {


  private MockWebServer mockWebServer;

  @Autowired
  OrderService orderService;

  @Autowired
  ObjectMapper objectMapper;


  @MockBean
  private WebClient.Builder webClientBuilder;

  @BeforeEach
  public void setUp() throws Exception {
    System.out.println("In setup");
    mockWebServer = new MockWebServer();
    mockWebServer.start();

    String mockBaseUrl = mockWebServer.url("/").toString();
    System.out.println("Mock base url " + mockBaseUrl);
    WebClient mockWebClient = WebClient.builder()
      .baseUrl(mockBaseUrl)
      .build();
    when(webClientBuilder.baseUrl(mockBaseUrl)).thenReturn(WebClient.builder());
    when(webClientBuilder.build()).thenReturn(mockWebClient);


  }

  @AfterEach
  public void tearDown() throws IOException {
    System.out.println("In shutdown.");
    mockWebServer.shutdown();
  }

  @Test
  void createOrderWithMockTest() throws Exception {

    //Now build the mock response.
    UserAccountInfoInt userAccountInfoInt = new UserAccountInfoInt("test user");

    mockWebServer.enqueue(new MockResponse()
      .setBody(objectMapper.writeValueAsString(userAccountInfoInt))
        .addHeader("Content-Type", "application/json")
          .setResponseCode(200));

    UserAccountInfo userAccountInfo = orderService.getUserAccount("1");

    assertEquals("1", "1");
    assertEquals("test user", userAccountInfo.getName());
  }
}
