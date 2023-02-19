package main.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.lang.constant.Constable;

public class Book {
    private int book_id;
    private Integer person_id;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 1, max = 249, message = "Длина названия книги должна быть между 1 и 250 символами")
    private String title;

    @NotEmpty(message = "У книги должен быть автор")
    @Size(max = 100, message = "Имя автора не может быть длиннее 100 символов")
    private String author;

    @Max(value = 2023, message = "Нельзя добавить книгу которая ещё не была опубликована")
    private int yearOfPublication;

    public Book() { }
    public Book(int book_id, Integer person_id, String title, String author, int yearOfPublication) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
