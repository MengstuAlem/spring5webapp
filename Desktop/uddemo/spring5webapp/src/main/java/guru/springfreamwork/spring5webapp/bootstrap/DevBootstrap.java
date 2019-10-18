package guru.springfreamwork.spring5webapp.bootstrap;

import guru.springfreamwork.spring5webapp.entity.Author;
import guru.springfreamwork.spring5webapp.entity.Book;
import guru.springfreamwork.spring5webapp.entity.Publisher;
import guru.springfreamwork.spring5webapp.repositories.AuthorRepository;
import guru.springfreamwork.spring5webapp.repositories.BookRepository;
import guru.springfreamwork.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData ();

    }

    public void  initData(){
        Publisher men= new Publisher ("mengst","ethiopoia");
        publisherRepository.save (men);

        Author eric = new Author ("eric","evans");
        Book ddd= new Book ("data deiven development","1234",men);

        eric.getBooks ().add (ddd);
        ddd.setPublisher (men);


        ddd.getAuthors ().add (eric);
        authorRepository.save (eric);
        bookRepository.save (ddd);
        publisherRepository.save (men);

        Publisher ale= new Publisher ("ale","ethiopoia");
        publisherRepository.save (ale);
        Author rod = new Author ("rod","johnson");
        Book noejb = new Book ("j2ee development without jdb","1234566",ale);

        rod.getBooks ().add (noejb);
        noejb.getAuthors ().add (rod);
        noejb.setPublisher (ale);
        authorRepository.save (rod);
        bookRepository.save (noejb);




    }
}
