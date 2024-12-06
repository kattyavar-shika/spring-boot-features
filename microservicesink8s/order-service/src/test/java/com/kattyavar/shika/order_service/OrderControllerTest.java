package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kattyavar.shika.order_service.controller.OrderController;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  //If you want to check only status.
  @Test
  void testGetOrderCalls() throws Exception {
    mockMvc.perform(get("/api/v1/users/1/orders"))
      .andExpect(status().isOk());

  }

  // If you would like to verify the response from api.
  @Test
  void testGetOrderClassWithBody() throws Exception {

    MvcResult result = mockMvc.perform(get("/api/v1/users/1/orders")).andReturn();

    //To verify the status

    assertEquals(200, result.getResponse().getStatus());

    String responseBody = result.getResponse().getContentAsString();
    //Now convert the string to object
    OrderResponse orderResponse = objectMapper.readValue(responseBody, OrderResponse.class);
    //Now you can verify the object ...
    assertEquals("1", orderResponse.userId());
  }


}
