package com.kattyavar.shika.cacheExample.service;

import com.kattyavar.shika.cacheExample.model.Employee;

import java.util.List;

public interface IEmployee {
  Employee getEmployee(String empId);

  Employee getEmployeeByName(String name);

  List<Employee> getEmployees();
}
