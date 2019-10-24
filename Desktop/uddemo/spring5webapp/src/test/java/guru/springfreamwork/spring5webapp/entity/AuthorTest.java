package guru.springfreamwork.spring5webapp.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorTest {
    private  Author author;

    @BeforeEach
    public void setUp(){
        author = new Author ();
    }

    @Test

    public void testGetId(){
        author.setId (21L);
       assertEquals(21L,author.getId ());
    }

}