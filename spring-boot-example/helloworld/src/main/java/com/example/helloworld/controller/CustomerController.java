package com.example.helloworld.controller;

import com.example.helloworld.entity.Customer;
import com.example.helloworld.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;


  @GetMapping("/customers")
  List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @PostMapping("/customers")
  Customer createCustomer(@RequestBody Customer customer) {

    //customerRepository.findById(customer.getCustomerId());
    return customerRepository.save(customer);
  }

  @DeleteMapping("/customers/{id}")
  void deleteCustomer(@PathVariable Long id) {
    customerRepository.deleteById(id);
  }

}
