# JPA CRUD

Create a Spring Boot application that reads and writes a record in a database based on [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/). We'll use H2, an in-memory database, in this exercise.

We'll re-use the application in the `REST Greeting` exercise.

## Create a Person Entity

1. In `Project` tab on the left, expand `src > main > java > edu.midlandu.codeacademy.simplerest`
2. Right-click on package `edu.midlandu.codeacademy.simplerest`
3. `New > Java Class`
4. Enter `Person` for `Name`
5. Click `OK`
6. Replace `public class Person { }` with the following:

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```

## Create a Person Repository

1. In `Project` tab on the left, expand `src > main > java > edu.midlandu.codeacademy.simplerest`
2. Right-click on package `edu.midlandu.codeacademy.simplerest`
3. `New > Java Class`
4. Enter `PersonRepository` for `Name`
5. Click `OK`
6. Replace `public class PersonRepository { }` with the following:

```java
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByLastName(@Param("name") String name);

}
```

## Verify It's Working

Visit <http://localhost:8080>. You should get something like:

```java
{
  "_links" : {
    "people" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    }
  }
}
```

Visit <http://localhost:8080/people>. You should get something like:

```java
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 0,
    "totalPages" : 0,
    "number" : 0
  }
}
```

## Create New Data

Use the browser extension "Postman" to post the following to `http://localhost:8080/people`:

```json
{
    "firstName": "Frodo",
    "lastName": "Baggins"
}
```

## Verify It Worked

Visit <http://localhost:8080/people>:

```json
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "_embedded" : {
    "persons" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/people/1"
        }
      }
    } ]
  },
  "page" : {
    "size" : 20,
    "totalElements" : 1,
    "totalPages" : 1,
    "number" : 0
  }
}
```

## Get an Individual Record

<http://localhost:8080/people/1>:

```json
{
  "firstName" : "Frodo",
  "lastName" : "Baggins",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1"
    }
  }
}
```

## Get Available Queries

<http://localhost:8080/people/search>:

```json
{
  "_links" : {
    "findByLastName" : {
      "href" : "http://localhost:8080/people/search/findByLastName{?name}",
      "templated" : true
    }
  }
}
```

## Last Name Search

<http://localhost:8080/people/search/findByLastName?name=Baggins>:

```json
{
  "_embedded" : {
    "persons" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/people/1"
        }
      }
    } ]
  }
}
```

## Extra

Install MySQL on your laptop and get your REST API reading and writing to it. You'll need a configuration similar to the following in `src/main/resources/application.properties`:

```properties
# The ddl-auto allows Hibernate to automatically create and update the database schema
spring.jpa.hibernate.ddl-auto=create
# db_example should be replaced by the name of your database
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
```
