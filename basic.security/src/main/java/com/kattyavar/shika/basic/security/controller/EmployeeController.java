package com.kattyavar.shika.basic.security.controller;

import com.kattyavar.shika.basic.security.model.Employee;
import com.kattyavar.shika.basic.security.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  List<Employee> employees = new ArrayList<>(List.of(new Employee("1", "Emp 1")
    , new Employee("2", "Emp 2")));

  /**
   * Summary of Useful @PreAuthorize Expressions:
   * Role-based: hasRole('ROLE_USER'), hasRole('ADMIN')
   * Authority-based: hasAuthority('USER'), hasAuthority('ROLE_ADMIN')
   * Authentication checks: isAuthenticated(), isAnonymous()
   * Custom expressions: #username == authentication.name, authentication.principal.username == 'admin'
   * Permissions checks: hasPermission()
   * Logical operators: and, or, not
   *
   * @return
   */
  @GetMapping("/employees")
  //@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
//  @PreAuthorize("isAuthenticated()")  //This ensures the user is authenticated before accessing a method.
//@PreAuthorize("hasRole('USER') and authentication.principal.username == 'john_doe'") // You can combine conditions using logical operators.
  List<Employee> getEmployee(String userName) {
    return this.employeeService.getEmployee();
  }

  //@PreAuthorize()
  //@PostAuthorize()
  //@PreFilter()
  //@PostFilter()

  @GetMapping("/sessionid")
  String getSessionId(HttpServletRequest request) {
    return request.getSession().getId();

  }

  @GetMapping("/open/about")
  String getAbout() {
    return "This is security demo project ...";
  }

  /**
   * The @PostAuthorize annotation is used to perform authorization checks after a method has executed, i.e., it allows you to check the method result or some other information after the method has been invoked.
   * Unlike @PreAuthorize (which checks before method execution), @PostAuthorize performs checks based on the result returned by the method.
   * This can be useful when you need to restrict access to a method’s return value
   */

  /**
   * Summary of Method Security Annotations:
   * @PreAuthorize: Used for pre-method authorization checks. Supports Spring Expression Language (SpEL).
   *
   * Example: @PreAuthorize("hasRole('ADMIN')")
   * @Secured: Used for role-based authorization. Simpler than @PreAuthorize but does not support SpEL.
   *
   * Example: @Secured("ROLE_ADMIN")
   * @RolesAllowed: Role-based authorization, similar to @Secured, but from the Java EE security specification.
   *
   * Example: @RolesAllowed("ADMIN")
   * @PostAuthorize: Used for authorization checks after the method has executed, typically on the return value.
   *
   * Example: @PostAuthorize("returnObject.owner == authentication.name")
   * @PreFilter: Filters input collections before the method is executed based on authorization logic.
   *
   * Example: @PreFilter("filterObject.owner == authentication.name")
   * @PostFilter: Filters output collections after the method is executed based on authorization logic.
   *
   * Example: @PostFilter("filterObject.owner == authentication.name")
   * @EnableMethodSecurity: Enables method-level security annotations such as @PreAuthorize, @Secured, etc.
   *
   * Example: @EnableMethodSecurity(prePostEnabled = true)
   */
}
