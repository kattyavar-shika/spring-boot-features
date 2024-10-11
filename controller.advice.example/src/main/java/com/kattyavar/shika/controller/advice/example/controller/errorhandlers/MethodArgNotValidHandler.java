package com.kattyavar.shika.controller.advice.example.controller.errorhandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class MethodArgNotValidHandler {

  private final ErrorAttributes errorAttributes;

  public MethodArgNotValidHandler(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException e, WebRequest request) {
    Map<String, Object> body = errorAttributes.getErrorAttributes(request,
      ErrorAttributeOptions.of(
        ErrorAttributeOptions.Include.MESSAGE,
        ErrorAttributeOptions.Include.BINDING_ERRORS,
        ErrorAttributeOptions.Include.PATH
      )
    );

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}
