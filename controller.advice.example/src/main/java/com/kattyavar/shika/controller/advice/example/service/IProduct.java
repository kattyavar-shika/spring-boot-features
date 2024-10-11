package com.kattyavar.shika.controller.advice.example.service;

import com.kattyavar.shika.controller.advice.example.dto.ProductRequest;
import com.kattyavar.shika.controller.advice.example.dto.ProductResponse;

import javax.naming.directory.InvalidAttributesException;

public interface IProduct {
  ProductResponse getProductName(String id) throws InvalidAttributesException;
  ProductResponse createProduct(ProductRequest request);
}
