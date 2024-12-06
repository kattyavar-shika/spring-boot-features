package com.kattyavar.shika.order_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class SecuredTestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;


  //If you want to check only status.
  @Test
  @WithMockUser(username = "user", password = "user", roles = "USER")
  void testGetOrderCalls() throws Exception {
    mockMvc.perform(get("/api/v2/secure"))
      .andExpect(status().isOk());

    MvcResult result =    mockMvc.perform(get("/api/v2/secure")).andReturn();

    assertEquals("Hello from secure end point", result.getResponse().getContentAsString());


  }
}
