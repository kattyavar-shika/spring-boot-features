
# @RestControllerAdvice

@RestControllerAdvice is a specialized annotation in Spring Framework, particularly used in Spring Boot applications. It combines the functionality of @ControllerAdvice and @ResponseBody.


@ControllerAdvice: This annotation allows you to handle exceptions globally across your application and can be used to define model attributes that should be available to all controllers.
@ResponseBody: This indicates that the return value of methods should be serialized directly to the HTTP response body, typically in JSON format.
When you use @RestControllerAdvice, you're essentially telling Spring that this class will handle exceptions and return data directly to the client in a RESTful way.


Example 

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
  }
  // with own attributes .. 
  @ExceptionHandler(InvalidAttributesException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, Object>> handleInvalidateException(InvalidAttributesException e) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "Internal server error");
    body.put("message", e.getMessage());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  //Get the attributes ... 
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

// another usage as ResponseStatus
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception class
@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Not Found
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}

// Controller using the custom exception
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @GetMapping("/resource/{id}")
  public String getResource(@PathVariable String id) {
    // Simulate resource lookup
    if ("unknown".equals(id)) {
      throw new ResourceNotFoundException("Resource not found");
    }
    return "Resource details for ID: " + id;
  }
}


```


## Differences between @ControllerAdvice and @RestControllerAdvice

| Feature                     | @ControllerAdvice                         | @RestControllerAdvice                     |
|-----------------------------|------------------------------------------|------------------------------------------|
| Response Body               | Does not automatically serialize response | Automatically serializes response to JSON |
| Use Case                    | General controller advice for MVC apps   | Specifically for REST APIs with JSON responses |
| Inclusion of @ResponseBody  | Needs to be explicitly added             | Implicitly includes `@ResponseBody`     |



# how to apply validation 

Spring Boot, validation is commonly done using the javax.validation API, which includes various annotations. Here’s a quick overview of some common validation annotations along with examples.


## Common Validation Annotations

- **@NotNull**: Ensures that a field is not null.
- **@NotEmpty**: Ensures that a string field is not empty (for `String`).
- **@Size**: Checks that a collection or string has a specified size.
- **@Min**: Checks that a numeric field has a minimum value.
- **@Max**: Checks that a numeric field has a maximum value.
- **@Email**: Validates that a string is a well-formed email address.
- **@Pattern**: Validates that a string matches a specified regular expression.

## Dependency 

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

```

Example 

```java 
import jakarta.validation.constraints.*;

public class UserDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18, message = "Age should be at least 18")
    @Max(value = 100, message = "Age should be less than or equal to 100")
    private Integer age;

    @Size(min = 5, max = 10, message = "Username must be between 5 and 10 characters")
    private String username;

  @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,14}$",
    message = "Username must start with a letter and be 5-15 characters long, containing letters, digits, and underscores.")
  private String patternExample;
    // Getters and Setters
}

```

## To validate use @Valid in controller. 

Example 

```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        // Logic to create user
        return ResponseEntity.ok("User created successfully");
    }
}

```

If you would like to handle error globally ..

Example 

```java 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder("Validation errors:");
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.append("\n").append(error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
    }
}

```

### if not @Valid or 

if you manually create an instance of UserDTO and want to validate its fields without using the @Valid annotation, you'll need to call a validation method programmatically.

#### Manual Validation Using javax.validation

You can achieve manual validation of an object by using the `Validator` interface provided by the `javax.validation` package. Below are the steps to perform this validation.

##### Steps to Manually Validate the Object

1. **Get a Validator Instance**: Use the `Validation` class to obtain a `Validator` instance.
2. **Validate the Object**: Call the `validate` method, passing in the object you want to validate.
3. **Handle Validation Results**: Process any validation errors returned by the `validate` method.

##### Example

```java
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class ManualValidationExample {

    public static void main(String[] args) {
        // Create a ValidatorFactory
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Create a UserDTO object
        UserDTO userDTO = new UserDTO();
        userDTO.setName(""); // Invalid
        userDTO.setEmail("invalid-email"); // Invalid
        userDTO.setAge(17); // Invalid
        userDTO.setUsername("usr"); // Invalid

        // Validate the UserDTO object
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Check for validation errors
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserDTO> violation : violations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("UserDTO is valid");
        }
    }
}

```
# Differences Between @Valid and @Validated

## @Valid
- Part of the Java Bean Validation API.
- Can be used on method parameters, return types, and fields.
- Triggers validation based on constraints defined in the object's class.

## @Validated
- Part of the Spring framework.
- Can be applied to methods in Spring beans.
- Allows for validation groups, enabling more fine-grained control over which validations to apply based on context.

## When to Use
- Use `@Valid` when you need standard validation without the need for Spring's additional features.
- Use `@Validated` when working within a Spring application and you want to leverage Spring's capabilities, especially with validation groups.


# Logging in Spring boot. 

## Logging Levels

Explain the different logging levels: TRACE, DEBUG, INFO, WARN, ERROR, and FATAL.

## To create log instance 

```java 

private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

```


Specify log level in conf file 

```yaml
# Set the logging level
logging.level.root=INFO
logging.level.com.example.loggingdemo=DEBUG

# Log file configuration
logging.file.name=app.log
logging.file.path=logs

```

 Another way to do it.. 

The @Slf4j annotation is part of the Lombok library, which provides a convenient way to use SLF4J logging without explicitly defining a logger. With Lombok, you can avoid boilerplate code and automatically generate a logger instance for your class.

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version> <!-- Check for the latest version -->
    <scope>provided</scope>
</dependency>

```
Usage 

```java
package com.example.loggingdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello endpoint was called");
        return "Hello, World!";
    }
}


```