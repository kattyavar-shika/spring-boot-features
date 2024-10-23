# Spring Boot Security 

Enabling Spring Boot Security in a Spring Boot 3.x application involves adding the necessary dependencies, configuring security settings, and optionally defining user roles and authentication methods.

## Add Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

```

When you add the spring-boot-starter-security dependency to your Spring Boot application, Spring Security automatically configures some basic security settings for you, including user authentication. Here's a breakdown of what happens:


## Default Security Configuration
Basic Authentication:

Spring Security sets up basic authentication by default. This means that when you try to access any secured endpoint, you will be prompted for a username and password.


Generated Username and Password:

- By default, Spring Boot creates a user with a username of user.
- It generates a random password for this user, which is printed to the console when you start the application.

## How to See the Default Password

When you run your Spring Boot application, look in the console output for a line similar to this:
```textmate
Using generated security password: abcdefgh-ijkl-mnop-qrst-uvwxyz123456

```

The actual password will be a random string each time you start the application, and you can use this password along with the username user to log in.

## Customizing the Username and Password

If you want to customize the default username and password, you can do so by adding properties to your application.properties or application.yml file:

```yaml
spring.security.user.name=myuser
spring.security.user.password=mypassword

or
spring:
  security:
    user:
      name: myuser
      password: mypassword

```


# What is CSRF?

CSRF, or Cross-Site Request Forgery, is an important security concept that you should understand, especially when working with web applications.

## Definition

CSRF is an attack that tricks the user’s browser into making unwanted requests to a different site on which the user is authenticated. This can lead to actions being performed on behalf of the user without their consent.

## How CSRF Works

1. **User Authentication**: A user logs into a website (e.g., a banking site) and receives a session cookie.
2. **Malicious Request**: The user then visits a malicious site that sends a request to the banking site, including the session cookie.
3. **Unwanted Action**: Because the user is authenticated, the banking site processes the request, potentially allowing the attacker to perform actions like transferring funds.

## Protecting Against CSRF

Spring Security provides built-in protection against CSRF attacks. Here’s how it works:

- **CSRF Tokens**: When a user submits a form, a unique CSRF token is generated and included in the request. The server checks this token to ensure that the request is legitimate.
- **Session Validation**: The server validates the session and checks if the CSRF token matches the one stored in the session.

## Configuring CSRF in Spring Security

By default, CSRF protection is enabled in Spring Security. You can configure it or disable it if your application does not require it.

### 1. Enabling/Configuring CSRF (Default Behavior)

You generally don’t need to change anything, as CSRF protection is enabled by default. However, if you want to customize it, you can do so in your `SecurityFilterChain`:

```java
http
    .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // Example of using a cookie-based CSRF token
        .and()
    .authorizeRequests()
        // Your authorization configuration
```

###  Disabling CSRF Protection

In some scenarios (e.g., when building stateless REST APIs), you may choose to disable CSRF protection:

```java
http
    .csrf().disable(); // Disable CSRF protection

```

### When to Disable CSRF

Disabling CSRF protection is generally safe for stateless APIs (e.g., those using token-based authentication) because they don’t rely on cookies for authentication. However, if your application uses session-based authentication and handles sensitive actions, you should keep CSRF protection enabled.


## how to disable login form

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .formLogin(formLogin -> formLogin.disable())
      .httpBasic(Customizer.withDefaults())
      .build();

  }
}
```

## Secure some API and allow few are open.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/v1/open/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(formeLoing -> formeLoing.disable())
      .httpBasic(Customizer.withDefaults())
      .build();

  }
} 
```

## To make state less 

Description: This policy means that the application will not create or use HTTP sessions to store authentication information. Each request must contain all the necessary information to authenticate the user (like a JWT or other token).

Use Case: Ideal for RESTful APIs or microservices where you want to maintain a stateless architecture. Each request is treated independently, which is useful for scaling and security.


```java 
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/v1/open/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(formeLoing -> formeLoing.disable())
      .httpBasic(Customizer.withDefaults())
      .build();

  }
}
```

Ref : https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html

https://docs.spring.io/spring-security/reference/servlet/architecture.html
