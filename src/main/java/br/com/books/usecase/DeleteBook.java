package br.com.books.usecase;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import br.com.books.exception.EntityNotFoundException;
import br.com.books.exception.GatewayException;
import br.com.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class DeleteBook {

  private final BookRepository repository;

  public void execute(final String id) {
    repository.findByIdOptional(new ObjectId(id))
      .orElseThrow(() -> {
        log.error("Book with id {} not found on database", id);
        throw new EntityNotFoundException("ID: ", id, "book with id " + id + " not found on database");
      }
    );

    try {
      repository.deleteById(new ObjectId(id));
      log.info("Book with id {} was removed", id);
    } catch (Exception e) {
      log.error("Error when trying to delete book with id {} on database", id);
      throw new GatewayException("Error when trying to delete book on database", e);
    }
  }
  
}
