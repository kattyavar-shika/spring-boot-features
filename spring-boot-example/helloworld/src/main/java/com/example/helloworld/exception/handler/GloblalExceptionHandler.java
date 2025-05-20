package com.example.helloworld.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GloblalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleRuntimeException(Exception ex) {

    log.info("our handleRuntimeException is called... ");
    return ResponseEntity.status(HttpStatus.OK).body("An error occurs " + ex.getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {

    log.info("our handleRuntimeException is called... ");
    return ResponseEntity.status(HttpStatus.OK).body("An error occurs " + ex.getMessage());
  }
}
