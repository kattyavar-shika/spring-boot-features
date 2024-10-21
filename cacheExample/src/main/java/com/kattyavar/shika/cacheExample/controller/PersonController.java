package com.kattyavar.shika.cacheExample.controller;

import com.kattyavar.shika.cacheExample.model.Person;
import com.kattyavar.shika.cacheExample.service.IPerson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

  IPerson iPerson;

  public PersonController(IPerson iPerson) {
    this.iPerson = iPerson;
  }


  @GetMapping("/persons")
  List<Person> getAll(){
    return iPerson.getPersons();
  }


  @GetMapping("/persons/{personId}")
  Person getPerson(@PathVariable String personId) {
    return iPerson.getPerson(personId);
  }

  @GetMapping("/persons/search")
  Person searchByName(@RequestParam String name) {
    return iPerson.getPersonByName(name);
  }

  @GetMapping("/persons/evict/{key}")
  void removeKeys(@PathVariable String key){
    iPerson.remove(key);
  }



}
