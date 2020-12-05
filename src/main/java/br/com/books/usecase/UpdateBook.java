package br.com.books.usecase;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import br.com.books.entity.Book;
import br.com.books.exception.BusinessException;
import br.com.books.exception.EntityNotFoundException;
import br.com.books.exception.GatewayException;
import br.com.books.repository.BookRepository;
import br.com.books.resource.entity.UpdateBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class UpdateBook {
  
  private final BookRepository repository;

  public void execute(final UpdateBookRequest updateBookRequest, final String id) {
    final Book book = repository.findByIdOptional(new ObjectId(id))
      .orElseThrow(() -> {
        log.error("Book with id {} not found on database", id);
        throw new EntityNotFoundException("ID: ", id, "Book with id " + id + " not found on database");
      }
    );

    if (!updateBookRequest.getTitle().equals(book.getTitle())) {
      final Optional<Book> bookWithNameOptional = repository.findByTitleOptional(updateBookRequest.getTitle());

      if (bookWithNameOptional.isPresent()) {
        log.error("Book with title {} already exists", updateBookRequest.getTitle());
        throw new BusinessException("Book with title " + updateBookRequest.getTitle() + " already exists");
      }
    }   

    book.setTitle(updateBookRequest.getTitle());
    book.setAuthor(updateBookRequest.getAuthor());
    book.setPages(updateBookRequest.getPages());
    book.setUpdateTime(LocalDateTime.now()); 

    try {
      repository.update(book);
    } catch (Exception e) {
      log.error("Error when trying to update book {} on database", updateBookRequest.getTitle());
      throw new GatewayException("Error when trying to update book on database", e);
    }

    log.info("Book with title {} was updated", book.getTitle());
  }

}
