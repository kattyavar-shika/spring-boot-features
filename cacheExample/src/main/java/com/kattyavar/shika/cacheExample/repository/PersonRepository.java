package com.kattyavar.shika.cacheExample.repository;

import com.kattyavar.shika.cacheExample.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PersonRepository {

  List<Person> list = List.of(
    new Person("1", "Person 1 name", "Person 1 last name"),
    new Person("2", "Person 2 name", "Person 2 last name"),
    new Person("3", "Person 3 name", "Person 3 last name"),
    new Person("4", "Person 4 name", "Person 4 last name"),
    new Person("5", "Person 5 name", "Person 5 last name")
  );


  public Person getPerson(String personId) {
    log.info(" getPerson with person id {}", personId);
    return list.stream()
      .filter(e -> e.getId().compareToIgnoreCase(personId) == 0)
      .findFirst()
      .orElseThrow();
  }

  public Person getPersonByName(String name) {
    log.info(" getPersonByName with parameter name {}", name);
    return
      list
        .stream()
        .filter(e -> e.getName().compareToIgnoreCase(name) == 0)
        .findAny()
        .orElseThrow();
  }


  public List<Person> getPersons() {
    log.info("getPersons");
    return list;
  }


}
