package main.controllers;

import main.dao.BookDAO;
import main.dao.PersonDAO;
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
    private PersonDAO personDAO;
    @Autowired
    BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping("")
    public String booksPage(Model m) {
        m.addAttribute("books", bookDAO.getBooks());
        return "/books/booksPage";
    }
    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id, Model m) {
        Book b = bookDAO.getBook(id);
        Person p;
        if (b.getPerson_id() != null) {
            p = personDAO.getPerson(b.getPerson_id());
        }
        else {
            p = null;
        }
        m.addAttribute("people", personDAO.getPeople());
        m.addAttribute("book", b);
        m.addAttribute("emptyPerson", new Person());
        m.addAttribute("person", p);
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
    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int bookId, @ModelAttribute("person") Person p) {
        bookDAO.assignBook(bookId, p);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int bookId) {
        bookDAO.freeBook(bookId);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
