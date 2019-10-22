package guru.springfreamwork.spring5webapp.controller;

import guru.springfreamwork.spring5webapp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthorController {
    AuthorRepository authorRepository;
    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @RequestMapping("/authors")
    public String getAuthor(Model model){
        model.addAttribute ("authors",authorRepository.findAll ());
        return "authors";
    }
}
