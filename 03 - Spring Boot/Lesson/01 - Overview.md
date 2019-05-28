# Spring Boot Overview

The [Spring Framework](https://spring.io) is a long time favorite for Java application. It applies well known patterns and makes some difficult tasks easier. There is a LOT in the Spring Framework. We'll cover a few things here.

## Beans

Beans is a term given to a Java class that adheres to a set of rules. However, in Spring the "Java Beans" rules aren't all followed. So a bean refers to an object managed by Spring, the developer doesn't write the code to create it.

## Dependency Injection

Dependency injection is when Spring creates and configures objects for you and then sets them into your classes. This keeps the configuration in one place and makes it easier to test.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ...

}
```

-- [Using JdbcTemplate](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-using-jdbc-template)

The `JdbcTemplate` object is created and configured with the database by Spring and given to your bean. When testing this class your test can create it's own `JdbcTemplate` object and configure it for testing instead of using the database. Your bean doesn't know the difference.

## Controllers

A Controller object handles HTTP requests. Spring makes heavy use of annotations to create the code necessary, leaving the business logic up to you. The following controller will respond to the `/greeting` URI, accept a parameter named `name`. The object returned will be converted to JSON. All this is handled by Spring.

```java
@RestController
public class GreetingController {
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        // ...
    }
}
```

-- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

There are other types of controllers, such as the `@RepositoryRestResource` that provides a REST endpoint for a database table.

```java
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByLastName(@Param("name") String name);

}
```

-- [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)

### REST

REST is an architecture style for designing networked applications over HTTP. It defines conventions for the HTTP paths and methods and the JSON responses. Accessing a RESTful API can be done with any HTTP client and the JSON can be used in the regular way. The Spring Boot `@RestController` and `@RepositoryRestResource` help you build RESTful APIs more easily. See <https://restfulapi.net/> for more discussion.

## Transactions

Transactions are database constructs that provide an "all or nothing" approach to database changes. Suppose you are creating an order with several items. If an error occurs, you'd want no changes in the database because it would be an incomplete order. A transaction will guarantee that.

Spring helps with transactions by managing that for you.

```java
@Service
@Transactional
public class OrderService {

    void createOrder(Order order) {
        // ...
    }
}
```

## Much more

Spring does much more than we can cover here. [Read the docs.](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/)
