package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloWorldNewWay {

  List<User> users = new ArrayList<>();

  @GetMapping("/api/v1/users")
  List<User> getAllUsers() {
    //i can delete here also ...
    return users;
  }

  @PostMapping("/api/v1/users")
  User createNewUser(@RequestBody User user) {
    users.add(user);
    return user;
  }

  //http://localhost:8080/api/v1/users/prasad?status=shipped
  @GetMapping("/api/v1/users/{name}")
  Optional<User> findByUserName(@PathVariable String name, @RequestParam(required = false) String status) {

    System.out.println("We have got status as --> " + status);
    return users
      .stream()
      .filter(x -> x.name().equals(name))
      .findAny();
  }

  //delete

  //@DeleteMapping

  //@PutMapping

  //@PatchMapping

}
