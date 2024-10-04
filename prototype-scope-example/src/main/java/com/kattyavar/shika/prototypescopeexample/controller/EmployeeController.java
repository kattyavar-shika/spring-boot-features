package com.kattyavar.shika.prototypescopeexample.controller;

import com.kattyavar.shika.prototypescopeexample.model.Employee;
import com.kattyavar.shika.prototypescopeexample.service.EmployeeService;
import com.kattyavar.shika.prototypescopeexample.service.EmployeeServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/employee")
  public List<Employee> getAllEmployee() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/employee/{name}")
  public Employee add(@PathVariable String name) {
    Employee employee = new Employee();
    employee.setName(name);
    employee.setAddress("Address for " + name);
    return employeeService.save(employee);
  }

  @PostMapping("/employee")
  public Employee save(@RequestBody Employee emp) {
    return employeeService.save(emp);
  }

}
