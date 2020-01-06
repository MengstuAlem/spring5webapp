package guru.springfreamwork.spring5webapp.service;

import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    BookRepository bookRepository;
@Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBook(){
        return  bookRepository.findAll ();
    }



    public Optional <Book> getSingleBook(Long id) throws Exception {
    if (!bookRepository.findById (id).isPresent ()){
         throw new Exception ("book not found");
    }
    return bookRepository.findById (id);

    }
}
