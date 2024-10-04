package com.kattyavar.shika.prototypescopeexample.service;

import com.kattyavar.shika.prototypescopeexample.model.Employee;

import java.util.List;

public interface EmployeeService {
  List<Employee> getAllEmployees();

  Employee save(Employee employee);
}
