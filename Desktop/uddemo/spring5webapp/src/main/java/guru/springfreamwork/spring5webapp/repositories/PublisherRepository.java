package guru.springfreamwork.spring5webapp.repositories;

import guru.springfreamwork.spring5webapp.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
