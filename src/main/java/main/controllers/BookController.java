package main.controllers;

import main.dao.BookDAO;
import main.models.Book;
import main.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    @Autowired
    BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @GetMapping("")
    public String booksPage(Model m) {
        m.addAttribute("books", bookDAO.getBooks());
        return "/books/booksPage";
    }
    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model m) {
        m.addAttribute("book", bookDAO.getBook(id));
        return "/books/bookPage";
    }
    @GetMapping("/new")
    public String newBookPage(Model m) {
        m.addAttribute("book", new Book());
        return "/books/newBookPage";
    }
    @PostMapping("")
    public String addBook(@ModelAttribute("book") @Valid Book b, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/newBookPage";
        }
        bookDAO.save(b);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model m) {
        m.addAttribute("book", bookDAO.getBook(id));
        return "/books/editBookPage";
    }
    @PatchMapping("/{id}")
    public String confirmEdit(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/books/editBookPage";
        }

        bookDAO.edit(book, id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
