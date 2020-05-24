package com.vianney.spring5webapp.bootstrap;

import com.vianney.spring5webapp.model.Author;
import com.vianney.spring5webapp.model.Book;
import com.vianney.spring5webapp.model.Publisher;
import com.vianney.spring5webapp.repositories.AuthorRepository;
import com.vianney.spring5webapp.repositories.BookRepository;
import com.vianney.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public void initData(){
        Author eric = new Author("Eric","Evans");
        Publisher pub = new Publisher("Harper Collins", "1234 rue storez");
        Book ddd = new Book("Domain Driven Design", "1234",pub);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        Author rod = new Author("Rod","Johnson");
        Publisher pub2 = new Publisher("Worx", "4567 rue de Masny");
        Book noEJB = new Book("JEE without EJB", "23444",pub2);
        rod.getBooks().add(noEJB);

        publisherRepository.save(pub);
        publisherRepository.save(pub2);
        authorRepository.save(eric);
        authorRepository.save(rod);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
