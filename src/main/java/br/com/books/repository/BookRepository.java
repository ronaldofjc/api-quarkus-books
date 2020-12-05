package br.com.books.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.entity.Book;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class BookRepository implements PanacheMongoRepository<Book> {

  public Optional<Book> findByTitleOptional(String title) {
    return find("title", title).firstResultOptional();
  }

}
