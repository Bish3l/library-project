package main.dao;

import main.models.Book;
import main.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book getBook(int id) {
        for (Book b : jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))) {
            return b;
        }
        return null;
    }
    public void save(Book b) {
        jdbcTemplate.update("INSERT INTO Book(title, author, yearofpublication) VALUES(?, ?, ?)", b.getTitle(), b.getAuthor(), b.getYearOfPublication());
    }
    public void edit(Book b, int id) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, yearofpublication=? WHERE book_id=?", b.getTitle(), b.getAuthor(), b.getYearOfPublication(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
    public void assignBook(int bookId, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPerson_id(), bookId);
    }
    public void freeBook(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", null, bookId);
    }
    public List<Book> getPersonsBooks(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }
}
