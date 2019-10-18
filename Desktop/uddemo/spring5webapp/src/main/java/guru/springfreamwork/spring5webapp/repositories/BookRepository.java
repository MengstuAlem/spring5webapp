package guru.springfreamwork.spring5webapp.repositories;

import guru.springfreamwork.spring5webapp.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {

}
