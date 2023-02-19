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

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    PersonDAO personDAO;
    BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("")
    public String peoplePage(Model m) {
        m.addAttribute("people", personDAO.getPeople());
        return "/people/peoplePage";
    }
    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id, Model m) {
        List<Book> books = bookDAO.getPersonsBooks(id);
        m.addAttribute("books", books);
        m.addAttribute("person", personDAO.getPerson(id));
        return "/people/personPage";
    }
    @GetMapping("/new")
    public String newPersonPage(Model m) {
        m.addAttribute("person", new Person());
        return "/people/newPersonPage";
    }
    @PostMapping("")
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/people/newPersonPage";
        }

        personDAO.save(person);

        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model m) {
        m.addAttribute("person", personDAO.getPerson(id));
        return "/people/editPersonPage";
    }
    @PatchMapping("/{id}")
    public String confirmEdit(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/people/editPersonPage";
        }

        personDAO.edit(person, id);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
