package main.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "Book title can't be empty")
    @Size(min = 1, max = 249, message = "Book title length must be between 1 and 250 characters")
    private String title;
    private String author;
    private int yearOfPublication;

    Book() { }

    public Book(int book_id, String title, String author, int yearOfPublication) {
        this.book_id = book_id;
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
