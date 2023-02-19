package main.controllers;

import main.dao.PersonDAO;
import main.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {
    PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String peoplePage(Model m) {
        m.addAttribute("people", personDAO.getPeople());
        return "/people/peoplePage";
    }
    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id, Model m) {
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
}
