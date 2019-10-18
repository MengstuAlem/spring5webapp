package guru.springfreamwork.spring5webapp.repositories;

import guru.springfreamwork.spring5webapp.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
