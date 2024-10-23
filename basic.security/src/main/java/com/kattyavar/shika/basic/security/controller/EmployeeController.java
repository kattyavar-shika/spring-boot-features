package com.kattyavar.shika.basic.security.controller;

import com.kattyavar.shika.basic.security.model.Employee;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  List<Employee> employees = new ArrayList<>(List.of(new Employee("1", "Emp 1")
    , new Employee("2", "Emp 2")));

  @GetMapping("/employees")
  List<Employee> getEmployee() {

    return employees;
  }


  @GetMapping("/sessionid")
  String getSessionId(HttpServletRequest request) {
    return request.getSession().getId();

  }

  @GetMapping("/open/about")
  String getAbout() {
    return "This is security demo project ...";
  }
}
