package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorld {

  List<User> users = new ArrayList<>();


  record ImpUser(String name, Integer age, String gender, String mobileNumber ) {
  }


  @PostMapping("helloworld")
  String helloWorld() {
    return "Hello World";
  }


  @GetMapping("getListOfUsers")
  List<User> getUsers() {

    if (users.size() == 0) {
      users.add(new User("Dummy value", 22, "don't know"));
    }
    return users;

  }

  //http://localhost:8080/createuser + request payload
  @PostMapping("createuser")
  User createUser(@RequestBody User user) {
   users.add(user);
    return user;
  }


  //http://localhost:8080/createuser + request payload
  @PostMapping("/api/v2/createuser")
  ImpUser createUserv2(@RequestBody ImpUser user) {

    return user;
  }

}
