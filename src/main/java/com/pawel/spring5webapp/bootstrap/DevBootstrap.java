package com.pawel.spring5webapp.bootstrap;

import com.pawel.spring5webapp.model.Author;
import com.pawel.spring5webapp.model.Book;
import com.pawel.spring5webapp.model.Publisher;
import com.pawel.spring5webapp.repositories.AuthorRepository;
import com.pawel.spring5webapp.repositories.BookRepository;
import com.pawel.spring5webapp.repositories.PublisherRepository;
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
		Author eric = new Author("Eric", "Evans");
		Publisher harperCollins = new Publisher("Harper Collins", "New Yourk 3228");
		Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		publisherRepository.save(harperCollins);
		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "Ottwa 3243");
		Book noEjb = new Book("J2EE Development without EJB", "1523", worx);
		rod.getBooks().add(noEjb);
		noEjb.getAuthors().add(rod);

		publisherRepository.save(worx);
		authorRepository.save(rod);
		bookRepository.save(noEjb);
	}
}
