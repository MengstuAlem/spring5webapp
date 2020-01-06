package guru.springfreamwork.spring5webapp.controller;


import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {
    private BookService bookService;
   @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")

    public @ResponseBody String getBooks(Model model){
        model.addAttribute ("books",bookService.getBook ());
        return "books";

    }
    @RequestMapping("/book/show/{id}")

    public String getBook(Model model,@PathVariable Long id) throws Exception {
       model.addAttribute ("book",bookService.getSingleBook (id));
       return "book";

    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    void newBook(@RequestBody Book newBook) {

    }

}
