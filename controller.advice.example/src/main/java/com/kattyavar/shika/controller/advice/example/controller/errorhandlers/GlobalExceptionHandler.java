package com.kattyavar.shika.controller.advice.example.controller.errorhandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.directory.InvalidAttributesException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final ErrorAttributes errorAttributes;

  public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }


  @ExceptionHandler(InvalidAttributesException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleInvalidateException(InvalidAttributesException e) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "Internal server error");
    body.put("message", e.getMessage());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> defaultHandler(Exception e, WebRequest request) {
    Map<String, Object> body = errorAttributes.getErrorAttributes(request,
      ErrorAttributeOptions.of(
        ErrorAttributeOptions.Include.MESSAGE,
        ErrorAttributeOptions.Include.BINDING_ERRORS,
        ErrorAttributeOptions.Include.PATH
      )
    );
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
