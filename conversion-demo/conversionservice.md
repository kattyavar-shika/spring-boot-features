# Conversion Service 

Creating a conversion service in Spring Boot typically involves using the Spring framework's ConversionService interface to handle type conversions. This can be useful when you need to convert between different object types, such as converting a custom object to a different representation or converting string inputs into specific types.

Implement a custom converter by extending Converter<S, T> interface. For example, convert a String to a User:

Example 

```java
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        String[] parts = source.split(",");
        User user = new User();
        user.setName(parts[0]);
        user.setAge(Integer.parseInt(parts[1]));
        return user;
    }
}

```

## Use of Conversion Service 

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/user")
    public User getUser(@RequestParam String userInput) {
        return conversionService.convert(userInput, User.class);
    }
}

```

# What is the Conversion Service in Spring?
The Conversion Service in Spring provides a framework for converting between different object types. It allows developers to define custom conversion logic for various types, facilitating data transformation, especially in scenarios like request/response handling in controllers. It enhances type safety and reduces boilerplate code.

# How do you register a custom converter in Spring Boot?
A custom converter can be registered by implementing the Converter<S, T> interface and annotating the class with @Component. This allows Spring to automatically detect and register the converter.

Example 

```java
@Component
public class StringToUserConverter implements Converter<String, User> {
    // Implementation
}

```

# What types of conversions can be handled by the Conversion Service?

The Conversion Service can handle both built-in conversions (e.g., String to Integer) and custom conversions defined by the user. It can convert simple types, collections, and complex object types, allowing for flexible data manipulation.

# How would you convert a String input to a custom object using the Conversion Service?

can create a custom converter as below 

```java 
@Component
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String source) {
        String[] parts = source.split(",");
        User user = new User();
        user.setName(parts[0]);
        user.setAge(Integer.parseInt(parts[1]));
        return user;
    }
}

```

# Can you demonstrate how to handle collections with the Conversion Service?

As below 

```java
@Component
public class StringToUserListConverter implements Converter<String, List<User>> {
    @Override
    public List<User> convert(String source) {
        return Arrays.stream(source.split(";"))
            .map(userString -> {
                String[] parts = userString.split(",");
                User user = new User();
                user.setName(parts[0]);
                user.setAge(Integer.parseInt(parts[1]));
                return user;
            })
            .collect(Collectors.toList());
    }
}

```

# How would you use the Conversion Service in a controller or any place ?

You can autowire the ConversionService and use it in your endpoints

Example 

```java
@RestController
public class UserController {
    @Autowired
    private ConversionService conversionService;

    @GetMapping("/user")
    public User getUser(@RequestParam String userInput) {
        return conversionService.convert(userInput, User.class);
    }
}

```

# How can you define and use multiple converters for the same type?
You can define multiple converters for the same type and annotate them with @Primary to specify which one should be preferred. If there’s no primary converter, you can specify the desired converter explicitly when invoking conversion.

# How can you integrate the Conversion Service with Spring Data?

You can use the Conversion Service in Spring Data repositories by allowing custom object types to be converted automatically when saving or retrieving entities. For example, you might convert DTOs to entities in repository methods.

# Given a scenario where you receive user input as a CSV string, how would you convert it into a list of objects?

You would implement a custom converter that splits the CSV string and maps it to the object. For instance:

Example 

```java
@Component
public class CsvToUserListConverter implements Converter<String, List<User>> {
    @Override
    public List<User> convert(String source) {
        return Arrays.stream(source.split(","))
            .map(userString -> {
                String[] parts = userString.split(";");
                User user = new User();
                user.setName(parts[0]);
                user.setAge(Integer.parseInt(parts[1]));
                return user;
            })
            .collect(Collectors.toList());
    }
}

```

# If a converter has dependencies on external services, how would you manage it?

You can inject the dependencies into the converter using constructor injection. This keeps your converters decoupled and manageable. For example:

```java
@Component
public class StringToUserConverter implements Converter<String, User> {
    private final UserService userService;

    @Autowired
    public StringToUserConverter(UserService userService) {
        this.userService = userService;
    }

    // Conversion logic
}

```
