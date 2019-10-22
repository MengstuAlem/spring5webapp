package guru.springfreamwork.spring5webapp.repositories;

import guru.springfreamwork.spring5webapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
