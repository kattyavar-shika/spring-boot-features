package com.kattyavar.shika.controller.advice.example.service;

import com.kattyavar.shika.controller.advice.example.dto.EmployeeRequest;
import com.kattyavar.shika.controller.advice.example.dto.EmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

  @Override
  public EmployeeResponse getEmployee(String id) {

    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new EmployeeResponse(id, "Name for " + id, " Address for " + id);
  }

  @Override
  public EmployeeResponse createEmployee(EmployeeRequest request) {
    log.info("In am in service ... testing only please remove me...");
    if (request.getName() == null || request.getName().isEmpty()) {
      throw new RuntimeException("Name can not be empty..");
    }

    if (request.getAddress() == null || request.getAddress().isEmpty()) {
      throw new RuntimeException("Address can not be empty...");
    }


    String address = request.getAddress() + request.getName() + " house number " + request.getAddress().length();
    return new EmployeeResponse("id for " + request.getName(), request.getName(), address);
  }

}
