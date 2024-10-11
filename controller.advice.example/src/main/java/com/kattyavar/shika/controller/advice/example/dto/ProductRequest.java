package com.kattyavar.shika.controller.advice.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
  @NotBlank(message = "product name is mandatory ...")
  private String name;

  @NotBlank(message = "Username is mandatory")
  private String promText;
}
