package guru.springfreamwork.spring5webapp.service;

import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {
    public static final String MY_BOOK = "myBook";
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks (bookRepository);
        bookService= new BookService (bookRepository);
    }


    @Test
    public void getBook() {
        Book book = new Book (MY_BOOK,"123");
        List<Book> bookList = new ArrayList <> ();
        bookList.add (book);
      when (bookService.getBook ()).thenReturn (bookList);

    }
    @Test
   public void addTwoBooksReturnTwo() {
        Book book = new Book ("MY_BOOK","123");
        Book book1 = new Book ("MY_BOOK","123");
        List<Book> bookList = new ArrayList <> ();
        bookList.add (book);
        bookList.add (book1);
        when (bookRepository.findAll ()).thenReturn (bookList);
        assertEquals ("MY_BOOK",bookService.getBook ().get (1).getTitle ());
       verify (bookRepository,times (1)).findAll ();


    }
    @Test
    public void getOneBook() throws Exception {
        Book book = new Book ("MY_BOOK","123");
        book.setId (1L);
        when (bookRepository.findById (1l)).thenReturn (java.util.Optional.ofNullable (book));
        assertEquals ("MY_BOOK",bookService.getSingleBook (1l).get ().getTitle ());
        verify (bookRepository,times(2)).findById (1l);
    }



}