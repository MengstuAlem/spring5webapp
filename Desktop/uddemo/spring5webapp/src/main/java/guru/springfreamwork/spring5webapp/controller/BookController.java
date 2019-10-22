package guru.springfreamwork.spring5webapp.controller;

import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.repositories.BookRepository;
import guru.springfreamwork.spring5webapp.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    BookRepository bookRepository;
   @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        model.addAttribute ("books",bookRepository.findAll ());
        return "books";
    }

}
