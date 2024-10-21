package com.kattyavar.shika.cacheExample.service;

import com.kattyavar.shika.cacheExample.model.Person;

import java.util.List;

public interface IPerson {
  Person getPerson(String personId);

  Person getPersonByName(String name);

  List<Person> getPersons();

  void remove(String key);
}
