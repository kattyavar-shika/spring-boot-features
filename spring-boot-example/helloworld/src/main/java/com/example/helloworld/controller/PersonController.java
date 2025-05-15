package com.example.helloworld.controller;

import com.example.helloworld.entity.Person;
import com.example.helloworld.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

  @Autowired
  PersonRepository personRepository;

  @GetMapping("/api/v1/persons")
  List<Person> getUsers() {

    return personRepository.findAll();

  }


}
