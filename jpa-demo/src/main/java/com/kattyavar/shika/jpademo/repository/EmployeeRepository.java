package com.kattyavar.shika.jpademo.repository;


import com.kattyavar.shika.jpademo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {


  List<Employee> findByName(String name);

}
