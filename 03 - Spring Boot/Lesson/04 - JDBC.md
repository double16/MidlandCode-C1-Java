# JDBC

JDBC is the Java DataBase Connector. It is an abstraction of database drivers that enable use of SQL. There are many methods to effectively query and process results, insert data, etc. For further reading, see [Data access with JDBC](https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html).

Spring provides the `JdbcTemplate` class to access JDBC. It configures and injects it into your beans.

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

## Query

The expected use case is querying for a single object or a list of zero or more objects. The methods in `JdbcTemplate` are intended to create objects based on a transformation you write.

```java
public class PersonMapper implements RowMapper<Person> {
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        return person;
    }
}

@RestController
public class PersonJdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/people", method = RequestMethod.GET)
    public List<Person> allPeople() {
        List<Person> results = jdbcTemplate.query(
                "select first_name, last_name from person",
                new PersonMapper());
        return results;
    }
}
```

The `RowMapper` implementation, here named `PersonMapper`, creates domain objects from the SQL query results. It is intended to be re-used in your code, so it's a separate class.

It's not necessary to only use domain objects, like JPA requires for entities. If the query is a computation, like a histogram, you can create a class to model that data and create a `RowMapper` for it.

## Insert / Update / Delete

`JdbcTemplate` uses the same `update` method for insert, update and delete. It returns the number of rows affected. Usually this will be 0 or 1 for inserts, and could be many for updates or deletes.

```java
@RestController
public class PersonJdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * This is only an example and a terrible idea!
     */
    @RequestMapping(path = "/people", method = RequestMethod.DELETE)
    public int deleteAllPeople() {
        return jdbcTemplate.update("delete from person");
    }

    @RequestMapping(path = "/people", method = RequestMethod.POST)
    public int update(@RequestParam("lastName") String lastName) {
        return jdbcTemplate.update("update person set last_modified=now() where last_name = ?", lastName);
    }
}
```
