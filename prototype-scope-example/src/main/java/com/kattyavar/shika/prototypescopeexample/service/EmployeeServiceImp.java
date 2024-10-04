package com.kattyavar.shika.prototypescopeexample.service;

import com.kattyavar.shika.prototypescopeexample.model.Employee;
import com.kattyavar.shika.prototypescopeexample.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

  private EmployeeRepository employeeRepository;

  public EmployeeServiceImp(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee save(Employee employee) {
    //TODo before saving new record. Try to find if it exits.. if yes don't do anything...
    return employeeRepository.save(employee);
  }
}
