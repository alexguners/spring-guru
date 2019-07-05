package guru.springfamework.bootstrap;

import guru.springfamework.model.Publisher;
import guru.springfamework.repositories.AuthorRepository;
import guru.springfamework.repositories.BookRepository;
import guru.springfamework.model.Author;
import guru.springfamework.model.Book;
import guru.springfamework.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 5/16/17.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent > {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent  contextRefreshedEvent) {
        System.out.println("entrou");
    	initData();
    }

    private void initData(){

        Publisher p = new Publisher();
        p.setName("Foo");
        publisherRepository.save(p);

        //Eric
        Author eric = new Author("Jose", "Evans");
        Book  ddd = new Book("Domain Driven Design", "1234", p);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", p);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
