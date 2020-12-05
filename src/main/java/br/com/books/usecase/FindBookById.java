package br.com.books.usecase;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import br.com.books.entity.Book;
import br.com.books.exception.EntityNotFoundException;
import br.com.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindBookById {
  
  private final BookRepository repository;

  public Book execute(final String id) {
    return repository.findByIdOptional(new ObjectId(id))
      .orElseThrow(() -> {
        log.error("Book with id {} not found on database", id);
        throw new EntityNotFoundException("ID: ", id, "Book with id " + id + " not found on database");
      });
  }
}
