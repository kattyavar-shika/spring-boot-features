package com.example.helloworld.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Person {

  @Id
  private Long id;
  private String name;
  private String email;

  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  List<Passport> passports;
}
