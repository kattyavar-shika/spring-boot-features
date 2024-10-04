package com.kattyavar.shika.prototypescopeexample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {

  List<String> items = new ArrayList<>();

  public void addItem(String item) {
    items.add(item);
  }

  public List<String> getItems() {
    return items;
  }
}
