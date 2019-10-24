package guru.springfreamwork.spring5webapp.controller;

import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
class BookControllerTest {
    @InjectMocks
    @Autowired
    private BookController bookController;
    @Mock
    private BookService bookService;
    @Mock
    private Model model;

    @BeforeEach

    public void setUp(){
        MockitoAnnotations.initMocks (this);
        bookController = new BookController (bookService);
    }
    @Test
    public void getListOfBooks(){
      List<Book> bookList = new ArrayList <> ();
      bookList.add (new Book ("title","23"));
      bookList.add (new Book ("te","90"));
      when (bookService.getBook ()).thenReturn (bookList);
      ArgumentCaptor <List<Book>> argumentCaptor= ArgumentCaptor.forClass (List.class);
      bookController.getBooks (model);
      verify (bookService,times (1)).getBook ();
      verify (model,times (1)).addAttribute (eq("books"),argumentCaptor.capture ());
      assertEquals (2,argumentCaptor.getValue ().size ());

    }

    @Test
    public void mockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup (bookController).build ();
        mockMvc.perform(get("/books"))
        .andExpect (status().isOk())
        .andExpect (view().name("books"));
    }

}