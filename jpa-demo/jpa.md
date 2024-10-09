
# @Entity Annotation in Spring Data JPA 

The @Entity annotation in JPA (Java Persistence API) is used to indicate that a class is an entity and should be mapped to a database table.

# To define primary key.

## @Id

@Id annotation is used to specify the primary key of an entity. Each entity must have a primary key to uniquely identify its instances in the database. Below are the key aspects of using the @Id annotation:

## Composite key @EmbeddedId, @Embeddable or @IdClass

If primary is composite then, use @Embeddable and @EmbeddedId

using  @Embeddable and @EmbeddedId

```java 

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Embeddable
public class UserId implements Serializable {
    private String email;
    private String username;

    // Getters and Setters
   
}

//


@Entity
public class User {

  @EmbeddedId
  private UserId userId;

  private String fullName;

   
}

```

using @IdClass 

```java 

import java.io.Serializable;
import java.util.Objects;

public class UserCompositeKey implements Serializable {
    private String email;
    private String username;

    public UserCompositeKey() {}

    public UserCompositeKey(String email, String username) {
        this.email = email;
        this.username = username;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCompositeKey)) return false;
        UserCompositeKey that = (UserCompositeKey) o;
        return Objects.equals(email, that.email) && 
               Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }
}

// Usage 
@Entity
@IdClass(UserCompositeKey.class) // Specify the composite key class
public class User {

  @Id
  private String email; // First part of the composite key

  @Id
  private String username; // Second part of the composite key

  private String fullName;

  // Default constructor
  public User() {}

  // Parameterized constructor
  public User(String email, String username, String fullName) {
    this.email = email;
    this.username = username;
    this.fullName = fullName;
  }

  // Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
```

# @Table Annotation in Spring Data JPA

The @Table annotation in JPA is used to specify the details of the table that an entity will be mapped to in the database. While it is optional (if not provided, the table name will default to the entity class name), it provides flexibility and control over the mapping.

```java 
import javax.persistence.*;

@Entity
@Table(name = "users") // Specifies the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Getters and setters
}


```


# @Column Annotation in Spring Data JPA

The `@Column` annotation is used to specify the mapping of an entity field to a database column. It allows for
customization of the column's characteristics.

## Purpose

- **Mapping**: Explicitly maps a field in an entity to a specific column in the database table.

## Key Attributes

   ```java
  //Specifies the name of the column in the database. If not provided, the default column name is derived from the field name.
@Column(name = "username")
private String userName;

//length: Defines the length of the column (for string types). Useful for varchar columns.
@Column(length = 50)
private String firstName;

//nullable: Indicates whether the column can accept null values. The default is true.
@Column(nullable = false)
private String email;

// unique: Specifies whether the column should have unique values across the table. The default is false.
@Column(unique = true)
private String socialSecurityNumber;

// updatable: Indicates whether the column is updatable. The default is true.
@Column(updatable = false)
private LocalDateTime createdDate;

//insertable: Indicates whether the column can be included in SQL INSERT statements. The default is true.
@Column(insertable = false)
private LocalDateTime updatedDate;

```

Here's a simple example of an entity using the @Column annotation:

```java

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", length = 30, unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(name = "email", unique = true)
  private String email;

  // Getters and setters
}

```

### Summary

The @Column annotation provides fine-grained control over how entity fields are mapped to database columns, allowing
developers to customize the behavior and characteristics of each column to meet specific requirements. This is essential
for ensuring data integrity and proper interaction with the underlying database.

# @OneToOne Annotation in Spring Data JPA

Imagine you have two tables in your database:

## Database Table Structures

### User Table

| Column Name | Data Type | Description                                  |
|-------------|-----------|----------------------------------------------|
| id          | Long      | Primary key, uniquely identifies the user.   |
| username    | String    | The username of the user.                    |
| profile_id  | Long      | Foreign key referencing the `Profile` table. |

### Profile Table

| Column Name | Data Type | Description                                   |
|-------------|-----------|-----------------------------------------------|
| id          | Long      | Primary key, uniquely identifies the profile. |
| bio         | String    | A brief biography or description of the user. |

# @OneToOne Annotation in Spring Data JPA

The `@OneToOne` annotation is used to define a one-to-one relationship between two entities in a JPA context. This means
that for each instance of one entity, there is exactly one corresponding instance of another entity.

## Purpose

- **Relationship Mapping**: It establishes a link between two entities, allowing them to reference each other directly.

## Key Attributes

  ```java
   //Specifies the entity class that is the target of the association. This is optional if the target type can be inferred.
@OneToOne(targetEntity = Address.class)
private Address address;

//mappedBy: Specifies the field that owns the relationship. This is used in bidirectional relationships to indicate the owning side.
@OneToOne(mappedBy = "profile")
private User user; // // This references the owning side of the relationship.

// cascade: Specifies the cascade type for operations like persist, remove, etc. This allows you to automatically propagate changes to related entities.
@OneToOne(cascade = CascadeType.ALL)
private Profile profile;

//fetch: Defines the fetching strategy (e.g., FetchType.EAGER, FetchType.LAZY). This determines when related entities are loaded.

@OneToOne(fetch = FetchType.LAZY)
private Profile profile;

//optional: Indicates whether the relationship is optional. The default is true, meaning the relationship can be null.
@OneToOne(optional = false)
private Profile profile;

```

java class

```java

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id", referencedColumnName = "id")
  private Profile profile;

  // Getters and setters
}

@Entity
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String bio;

  @OneToOne(mappedBy = "profile")
  private User user;

  // Getters and setters
}

```

# @OneToMany

The @OneToMany annotation establishes a relationship where a single entity (the "one" side) is associated with multiple
instances of another entity (the "many" side).

@OneToMany and @ManyToOne are commonly used together to define bidirectional relationships.

## Key Attributes

targetEntity: Specifies the entity class that is the target of the association. This is optional if the target type can
be inferred.

mappedBy: Used in the "one" side to indicate that the relationship is managed by the "many" side. This is typically a
reference to the field in the child entity that owns the relationship.

cascade: Specifies which operations (like persist, remove) should be cascaded to the associated entities.

fetch: Determines how related entities are fetched. It can be FetchType.LAZY or FetchType.EAGER.

Let's consider a simple example with User and Order entities:

User Entity (One Side)

```java 
import javax.persistence.*;
import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Order> orders; // A user can have multiple orders.

  // Getters and setters
}

```

Order Entity (Many Side)

```java 
 import javax.persistence.*;

@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String product;

  @ManyToOne // Indicates that many orders can belong to one user.
  @JoinColumn(name = "user_id") // Foreign key in the Order table.
  private User user;

  // Getters and setters
}


```

#  @ManyToMany

The @ManyToMany annotation indicates that multiple instances of one entity can be associated with multiple instances of another entity. This type of relationship is common in scenarios where entities can belong to each other in a non-hierarchical way.

## Key Attributes

targetEntity: Specifies the entity class that is the target of the association. This is optional if the target type can be inferred.

mappedBy: Used in one of the entities to indicate that the relationship is managed by the other entity.

cascade: Specifies which operations should be cascaded to the associated entities.

fetch: Determines how related entities are fetched. It can be FetchType.LAZY or FetchType.EAGER.

Example 

Let’s consider an example with Student and Course entities:

Student Entity 
```java 
import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_course", // Join table name
        joinColumns = @JoinColumn(name = "student_id"), // Foreign key for Student
        inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course
    )
    private Set<Course> courses; // A student can enroll in multiple courses.

    // Getters and setters
}

```
Course entity 

```java 
import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Student> students; // A course can have multiple students.

    // Getters and setters
}


```

### Student Class

- The `Student` entity has a `@ManyToMany` relationship with `Course`.
- **`@JoinTable`**: Specifies the join table that will be created to manage the relationship between `Student` and `Course`.
    - **`name`**: The name of the join table.
    - **`joinColumns`**: Defines the foreign key that references the `Student` entity.
    - **`inverseJoinColumns`**: Defines the foreign key that references the `Course` entity.
- The `Set<Course> courses` field represents the courses that a student can enroll in.

### Course Class

- The `Course` entity also has a `@ManyToMany` relationship with `Student`.
- **`mappedBy = "courses"`**: Indicates that the relationship is managed by the `courses` field in the `Student` entity. This means `Course` does not own the relationship.


# @JoinColumn Annotation

- The `@JoinColumn` annotation is used in JPA to specify the column that is used for joining two entities in a relationship.
- It defines the foreign key column in the entity where the relationship is being managed.

## Key Attributes

- **name**: Specifies the name of the foreign key column in the database table.
- **referencedColumnName**: Specifies the column name of the referenced entity's primary key. This is optional if the referenced column is the primary key.
- **nullable**: Specifies whether the foreign key column can be null. Default is `true`.
- **unique**: Specifies whether the foreign key column should be unique. Default is `false`.

## Example Usage

In a `@ManyToOne` relationship, `@JoinColumn` is used to define the foreign key:

```java
@ManyToOne
@JoinColumn(name = "user_id") // Foreign key in the Order table.
private User user;

//you can also use multiple column using joinColumn as below 
@ManyToOne
@JoinColumns({
  @JoinColumn(name = "user_id", referencedColumnName = "id"),
  @JoinColumn(name = "product_id", referencedColumnName = "product_id")
})
private User user;

```

# @Query 

In Spring Data JPA, you can define queries to retrieve data from the database in several ways. Here are the main methods for creating queries:

## 1. Method Naming Conventions

Spring Data JPA allows you to create queries based on method names. You can define repository methods using specific prefixes that translate to queries.

Example 

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByUsernameAndAge(String username, int age);
}


```

## 2. JPQL Queries with @Query Annotation

You can define JPQL queries directly in your repository interface using the @Query annotation.

Example 
```java 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(Long userId);
}

```

## 3. Native Queries

If you need to use SQL directly, you can define native queries using the @Query annotation with the nativeQuery attribute set to true.

Example 
```java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
    @Query(value = "SELECT * FROM products WHERE price > ?1", nativeQuery = true)
    List<Product> findProductsByPriceGreaterThan(Double price);
}
```

When to use this 

Complex SQL Logic: If you need to use advanced SQL features that JPQL does not support.
Database-Specific Functions: When you want to use database-specific functions or syntax that aren't available in JPQL.
Performance: In some cases, native queries may perform better for certain types of queries.


# @Transactional 

@Transactional annotation is essential, particularly in scenarios involving multiple database operations that must succeed or fail as a unit. This ensures data integrity and consistency.

1. Creating an Order with Payment Processing

In an e-commerce application, placing an order typically involves creating an order record and processing a payment. Both operations must either succeed together or fail together.

Example 

```java 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public void placeOrder(Order order, Payment payment) {
        // Save the order to the database
        orderRepository.save(order);

        // Process the payment
        paymentService.processPayment(payment); // This method could throw an exception

        // If any operation fails, the transaction will roll back
    }
}

```

### Key Points about @Transactional

#### Best Practices for Using @Transactional

##### Avoid Internal Calls

If a method annotated with `@Transactional` calls another method within the same class (even if that method is also marked as `@Transactional`), the transaction management will not apply. This is because the call bypasses the proxy that manages transactions.

##### Use External Calls

For transaction management to work correctly, call `@Transactional` methods from outside the class. This ensures that the call goes through the Spring proxy, allowing the transaction management to function as expected.

##### Separate Service Classes

If you have logic that should be transactional but needs to be called from another method, consider moving that logic to a separate service class. This way, when you call the method, it goes through the proxy and benefits from transaction management.


## Incorrect Usage 

```java
@Service
public class OrderService {

  @Transactional
  public void placeOrder(Order order) {
    // This is transactional
    validateOrder(order); // This call will NOT be transactional
  }

  private void validateOrder(Order order) {
    // Validation logic here
    // Not transactional; if this throws an exception, it won't trigger a rollback
  }
}
 
```

## Correct usage 

```java 
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService; // Separate class for payment logic

    @Transactional
    public void placeOrder(Order order, Payment payment) {
        // This method is transactional
        orderRepository.save(order);

        // Calling a method from another service class
        paymentService.processPayment(payment); // Transaction management applies here
    }
}

```

# Sprint data rest 

Add below dependency 

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>
```

Define entity 

```java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

}

// And repository 

public interface UserRepository extends JpaRepository<AppUser, Long> {
}

```


## AppUser API Endpoints

### Get All Users

- **Endpoint**: `GET /appUsers`
- **Description**: Retrieves a list of all `AppUser` records.

### Get User by ID

- **Endpoint**: `GET /appUsers/{id}`
- **Description**: Retrieves a single `AppUser` by its ID.

### Create a New User

- **Endpoint**: `POST /appUsers`
- **Description**: Creates a new `AppUser`. The request body should contain the user details (e.g., `{"id": 1, "username": "john_doe"}`).

### Update an Existing User

- **Endpoint**: `PUT /appUsers/{id}`
- **Description**: Updates an existing `AppUser` by its ID. The request body should contain the updated user details.

### Delete a User

- **Endpoint**: `DELETE /appUsers/{id}`
- **Description**: Deletes an `AppUser` by its ID.


# Controlling Exposed Endpoints in Spring Data REST


### Spring Data REST automatically exposes CRUD operations for your JPA entities through HTTP endpoints. However, there may be situations where you want to limit the available operations to enhance security or meet specific application requirements.


### Disabling Specific HTTP Methods

```java 

import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    
    @RestResource(exported = false) // Disable DELETE method
    @Override
    void deleteById(Long id);
    
    @RestResource(exported = false) // Disable PUT method
    @Override
    <S extends AppUser> S save(S entity);
    
    //In case you want to also disable POST then use below 

  @RestResource(exported = false) // Disable POST method
  @Override
  <S extends AppUser> S saveAll(Iterable<S> entities);
}

```

# DB Connection Thread pool.
## HikariCP
To configure HikariCP in a Spring Boot application, you will typically use the application.properties or application.yml file to set the connection pool properties


Example

```yaml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourdb
    username: root
    password: yourpassword
    hikari:
      maximum-pool-size: 10                   # Maximum number of connections in the pool
      minimum-idle: 5                         # Minimum number of idle connections
      idle-timeout: 600000                    # Idle time before a connection is removed (in milliseconds)
      connection-timeout: 30000               # Maximum time to wait for a connection (in milliseconds)
      max-lifetime: 1800000                   # Maximum lifetime of a connection (in milliseconds)

```


#  @NamedQuery and @NamedNativeQuery 

@NamedQuery and @NamedNativeQuery are powerful features in JPA that allow you to define queries in a reusable manner. They can be used to encapsulate queries in your entity classes, which helps in managing and maintaining them.

## Using @NamedQuery 

@NamedQuery is used for defining JPQL (Java Persistence Query Language) queries. You typically define these at the class level in your entity.

Example 

```java 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
    name = "User.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username"
)
public class User {

    @Id
    private Long id;
    private String username;
    private String fullName;

    // Getters and Setters
}

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username); // This will use the NamedQuery
}
```

## Using @NamedNativeQuery

@NamedNativeQuery is used for defining native SQL queries. This is useful when you need to run SQL directly, particularly for database-specific queries or performance optimizations.

Example

```java 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQuery(
    name = "User.findByEmail",
    query = "SELECT * FROM users WHERE email = :email",
    resultClass = User.class // Specify the result class
)
public class User {

    @Id
    private Long id;
    private String username;
    private String email;
    private String fullName;

    // Getters and Setters
}

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email); // This will use the NamedNativeQuery
}
```

We can combine both at the same time. 

Example 

```java 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(
    name = "User.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username"
)
@NamedNativeQuery(
    name = "User.findByEmail",
    query = "SELECT * FROM users WHERE email = :email",
    resultClass = User.class // Specify the result class
)
public class User {

    @Id
    private Long id;
    private String username;
    private String email;
    private String fullName;

    // Getters and Setters
}

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username); // Uses NamedQuery
  User findByEmail(String email); // Will use NamedNativeQuery if defined properly
}
```

# Lazy loading

Using @Transactional is crucial when working with lazy loading in JPA to ensure that the Hibernate session remains open for accessing lazily-loaded properties. This allows you to fetch related data without running into LazyInitializationException.


Example 

```java 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void displayUserOrders(Long userId) {
        // Load the user by ID
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            System.out.println("Username: " + user.getUsername());

            // At this point, orders are not loaded yet (due to lazy loading)
            System.out.println("Orders (not yet loaded): " + user.getOrders());

            // Now we explicitly access the orders, triggering the load
            for (Order order : user.getOrders()) {
                System.out.println("Order ID: " + order.getId() + ", Product: " + order.getProduct());
            }
        }
    }
}

```

## What Happens Without @Transactional?

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void displayUserOrders(Long userId) {
    User user = userRepository.findById(userId).orElse(null);

    if (user != null) {
      // Accessing the lazy-loaded orders
      for (Order order : user.getOrders()) { // This will throw LazyInitializationException
        System.out.println("Order ID: " + order.getId());
      }
    }
  }
}

```

# pagination

Implementing pagination in JPA allows you to efficiently retrieve a subset of records from a larger dataset, improving performance and user experience, especially in applications displaying lists of entities. Here’s how you can implement pagination using JPA:

## Spring Data JPA

Example 

```java
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityRepository extends JpaRepository<MyEntity, Long> {
    Page<MyEntity> findAll(Pageable pageable);
}

// Usage 
@Service
public class MyEntityService {

  @Autowired
  private MyEntityRepository myEntityRepository;

  public Page<MyEntity> getPaginatedEntities(int page, int size) {
    return myEntityRepository.findAll(PageRequest.of(page, size));
  }
}
```

