package com.kattyavar.shika.controller.advice.example.controller;

import com.kattyavar.shika.controller.advice.example.dto.EmployeeRequest;
import com.kattyavar.shika.controller.advice.example.dto.EmployeeResponse;
import com.kattyavar.shika.controller.advice.example.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  private IEmployeeService iEmployeeService;

  public EmployeeController(IEmployeeService iEmployeeService) {
    this.iEmployeeService = iEmployeeService;
  }

  @GetMapping("/employess/{id}")
  EmployeeResponse getEmployee(@PathVariable String id) {



    return iEmployeeService.getEmployee(id);



  }

  @PostMapping("/employess")
  EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
    return iEmployeeService.createEmployee(employeeRequest);
  }

}
