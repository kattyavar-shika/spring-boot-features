package com.kattyavar.shika.prototypescopeexample.repository;

import com.kattyavar.shika.prototypescopeexample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
