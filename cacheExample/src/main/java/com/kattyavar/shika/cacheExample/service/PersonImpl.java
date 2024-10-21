package com.kattyavar.shika.cacheExample.service;

import com.kattyavar.shika.cacheExample.model.Person;
import com.kattyavar.shika.cacheExample.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonImpl implements IPerson {

  PersonRepository personRepository;

  public PersonImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  @Cacheable(value = "personCache")
  public Person getPerson(String personId) {
    return personRepository.getPerson(personId);
  }

  @Override
  @Cacheable(value = "personCache", key = "#name")
  public Person getPersonByName(String name) {
    return personRepository.getPersonByName(name);
  }

  @Override
  @Cacheable(value = "personCacheList", key = "'allEmployees'")
  public List<Person> getPersons() {
    return personRepository.getPersons();
  }

  @Override
  @CacheEvict(value = "personCache", key = "#key")
  public void remove(String key) {
  }

}
