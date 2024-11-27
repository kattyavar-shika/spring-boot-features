package com.kattyavar.shika.basic.security.service;

import com.kattyavar.shika.basic.security.model.Employee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

  List<Employee> employees = new ArrayList<>(List.of(new Employee("1", "Emp 1")
    , new Employee("2", "Emp 2")
    , new Employee("3", "New data from Service...")

  ));

  @PreAuthorize("hasRole('USER')")
  public List<Employee> getEmployee() {

    return employees;
  }


}
