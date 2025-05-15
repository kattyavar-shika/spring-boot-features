package com.example.helloworld.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

  @Id
  private Long id;

  private String passportNumber;

  private String nationality;


  @ManyToOne
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  private Person person;

}
