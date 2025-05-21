package com.example.helloworld.controller;

import com.example.helloworld.dto.CustomerDTO;
import com.example.helloworld.entity.Customer;
import com.example.helloworld.repository.CustomerRepository;
import com.example.helloworld.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

  @Autowired
  private ConversionService conversionService;

  private CustomerRepository customerRepository;

  private CustomerService customerService;

  public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
    this.customerRepository = customerRepository;
    this.customerService = customerService;
  }


  //Secure... the what is the way out...

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
    System.out.println("Delete is called....");
    customerRepository.deleteById(id);
  }

  @PostMapping("/customers/test")
  CustomerDTO validateRequestObject(@Validated @RequestBody CustomerDTO customerDTO) throws InterruptedException {
    //You take control and validate all business rules. before processing request object...


    log.info("We have request object as {}", customerDTO);
    return customerService.createCustomer(customerDTO);
  }


  //Def
  class CustomDTOToCustomer implements Converter<CustomerDTO, Customer> {
    @Override
    public Customer convert(CustomerDTO source) {
      Customer customer = new Customer();

      customer.setFirstName(source.getName());
      customer.setLastName(source.getLastName());
      return customer;
    }
  }

  @GetMapping("/version")
  String getCurrentVersion() {
    return "current version = 1.0.1";
  }

}
