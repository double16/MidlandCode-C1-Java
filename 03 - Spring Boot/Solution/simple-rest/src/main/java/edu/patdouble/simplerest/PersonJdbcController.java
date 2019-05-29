package edu.patdouble.simplerest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Returning Person objects using JDBC.
 */
@RestController
public class PersonJdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final class PersonMapper implements RowMapper<Person> {
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            return person;
        }
    }

    @RequestMapping(path = "/api/people2", method = RequestMethod.GET)
    public List<Person> allPeople(@RequestParam(value = "last_name", defaultValue = "") String lastName) {
        List<Person> results;
        if (lastName.isBlank()) {
            results = jdbcTemplate.query(
                    "select first_name, last_name from person",
                    new PersonMapper());
        } else {
            results = jdbcTemplate.query(
                    "select first_name, last_name from person where last_name = ?",
                    new Object[] { lastName },
                    new PersonMapper());
        }
        return results;
    }
}
