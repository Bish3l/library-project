package main.dao;

import main.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person getPerson(int id) {
        for (Person p : jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))) {
            return p;
        };
        return null;
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, birthyear) VALUES(?, ?)", person.getName(), person.getBirthYear());
    }
}
