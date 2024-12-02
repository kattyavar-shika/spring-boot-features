package com.kattyavar.shika.option_service.controller;


import com.kattyavar.shika.option_service.model.Option;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/options")
public class OptionController {

  List<Option> options = Arrays.asList(
    new Option(1, "Option 1 ", "Option 1 description", "Option 1 Title"),
    new Option(2, "Option 2 ", "Option 2 description", "Option 2 Title"),
    new Option(3, "Option 3 ", "Option 3 description", "Option 3 Title")
  );

  @GetMapping
  List<Option> getOptions() {
    return options;
  }

  @GetMapping("/{optionId}")
  Option getOption(@PathVariable Integer optionId) {
    return options
      .stream()
      .filter(option -> option.optionId().equals(optionId))
      .findAny()
      .orElse(null);
  }
}
