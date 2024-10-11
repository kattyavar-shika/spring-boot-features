package com.kattyavar.shika.controller.advice.example.exceptions;

public class InvalidProductInputException extends RuntimeException {

  public static final String ERROR_ID = "ERR-001";

  public InvalidProductInputException(String message) {
    super(message);
  }

  public String getErrorId() {
    return ERROR_ID;
  }
}
