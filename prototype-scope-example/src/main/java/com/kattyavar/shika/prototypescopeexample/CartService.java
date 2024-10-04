package com.kattyavar.shika.prototypescopeexample;

import org.springframework.beans.factory.annotation.Lookup;

import java.util.HashMap;
import java.util.Map;

public class CartService {

  Map<String, Cart> cartByUsers = new HashMap<>();

  public void createCart(String userName) {
    //Here i need new object of Cart
  }



}
