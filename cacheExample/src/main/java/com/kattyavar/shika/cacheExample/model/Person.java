package com.kattyavar.shika.cacheExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  String id;
  String name;
  String lastName;
}
