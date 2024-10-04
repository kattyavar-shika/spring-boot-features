package com.kattyavar.shika.prototypescopeexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PrototypeCarController {

  private static final Logger logger = LoggerFactory.getLogger(PrototypeCarController.class);
  @Autowired
  private PrototypeCarService prototypeCarService;

  @GetMapping
  private String getTimeStamp() {
    logger.info("In Controller...");
    return prototypeCarService.getTimeStamp();
  }

}
