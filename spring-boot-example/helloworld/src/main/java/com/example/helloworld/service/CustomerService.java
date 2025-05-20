package com.example.helloworld.service;

import com.example.helloworld.dto.CustomerDTO;
import com.example.helloworld.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;


  public CustomerDTO createCustomer(CustomerDTO request) throws InterruptedException {


    Thread.sleep(5000);

    if (request.getName().equals("NOK")) {
      throw new RuntimeException("Not allowed for NOK users names");
    }

    return request;
  }
}
