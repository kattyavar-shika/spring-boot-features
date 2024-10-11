package com.kattyavar.shika.controller.advice.example.controller;

import com.kattyavar.shika.controller.advice.example.dto.ProductRequest;
import com.kattyavar.shika.controller.advice.example.dto.ProductResponse;
import com.kattyavar.shika.controller.advice.example.service.IProduct;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributesException;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

  IProduct iProduct;

  public DemoController(IProduct iProduct) {
    this.iProduct = iProduct;
  }

  @GetMapping("/products/{productId}")
  ProductResponse invalidInputData(@PathVariable String productId) throws InvalidAttributesException {
    return iProduct.getProductName(productId);
  }


  @PostMapping("/products")
  ProductResponse createProduct(@Validated @RequestBody ProductRequest productRequest) {
    return iProduct.createProduct(productRequest);
  }


}
