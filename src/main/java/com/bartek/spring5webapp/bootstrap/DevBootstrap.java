package com.bartek.spring5webapp.bootstrap;

import com.bartek.spring5webapp.model.Author;
import com.bartek.spring5webapp.model.Book;
import com.bartek.spring5webapp.model.Publisher;
import com.bartek.spring5webapp.repositories.AuthorRepository;
import com.bartek.spring5webapp.repositories.BookRepository;
import com.bartek.spring5webapp.repositories.PublisherRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


    private void initData() {

        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        ///adam
        Author adam = new Author("Adam", "Mickiewicz");
        Book panTadeusz = new Book("Pan Tadeusz", "1234", publisher);
        adam.getBooks().add(panTadeusz);
        panTadeusz.getAuthors().add(adam);

        authorRepository.save(adam);
        bookRepository.save(panTadeusz);

        ///januszNOEJB
        Author bartek = new Author("Bartek", "Sawicki");
        Book noEJB = new Book("zycie", "4321", publisher);
        bartek.getBooks().add(noEJB);

        authorRepository.save(bartek);
        bookRepository.save(noEJB);
    }


}
