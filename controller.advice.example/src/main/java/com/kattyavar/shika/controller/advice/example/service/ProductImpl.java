package com.kattyavar.shika.controller.advice.example.service;

import com.kattyavar.shika.controller.advice.example.dto.ProductRequest;
import com.kattyavar.shika.controller.advice.example.dto.ProductResponse;
import com.kattyavar.shika.controller.advice.example.exceptions.InvalidProductInputException;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;

@Service
public class ProductImpl implements IProduct {

  @Override
  public ProductResponse getProductName(String id) throws InvalidAttributesException {
    //For demo purpose
    if (id.compareToIgnoreCase("1") == 0) {
      throw new InvalidAttributesException(" id can not be 1");
    }

    if (id.compareToIgnoreCase("2") == 0) {
      throw new RuntimeException("in case of 2");
    }
    return new ProductResponse(id, "Product name for " + id, " promo text for " + id);
  }

  @Override
  public ProductResponse createProduct(ProductRequest request) {

    if (request.getPromText().isEmpty()) {
      throw new InvalidProductInputException("Promo code can not be empty");
    }

    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return new ProductResponse("id0", "", "");
  }
}
