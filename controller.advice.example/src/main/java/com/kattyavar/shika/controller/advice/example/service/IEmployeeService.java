package com.kattyavar.shika.controller.advice.example.service;

import com.kattyavar.shika.controller.advice.example.dto.EmployeeRequest;
import com.kattyavar.shika.controller.advice.example.dto.EmployeeResponse;

public interface IEmployeeService {

  EmployeeResponse getEmployee(String id);
  EmployeeResponse createEmployee(EmployeeRequest request);
}
