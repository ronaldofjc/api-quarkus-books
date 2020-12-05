package br.com.books.usecase;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.entity.Book;
import br.com.books.exception.BusinessException;
import br.com.books.exception.GatewayException;
import br.com.books.repository.BookRepository;
import br.com.books.resource.entity.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CreateBook {

  private final BookRepository repository;

  public void execute(CreateBookRequest createBookRequest) {

    Optional<Book> bookOptional = repository.findByTitleOptional(createBookRequest.getTitle());

    if (bookOptional.isPresent()) {
      log.error("Book already exists. Title: " + bookOptional.get().getTitle());
      throw new BusinessException("Book with name " + bookOptional.get().getTitle() + " already exists!");
    }

    Book book = Book.builder()
      .title(createBookRequest.getTitle())
      .author(createBookRequest.getAuthor())
      .pages(createBookRequest.getPages())
      .active(true)
      .creationTime(LocalDateTime.now())
      .updateTime(LocalDateTime.now())
      .build();

    try {
      repository.persist(book);
    } catch (Exception e) {
      log.error("Error when trying to save new book {} on database. Title: {}", createBookRequest.getTitle(), e);
      throw new GatewayException("Error when trying to save new book on database", e);
    }

    log.info("Book with title {} was created", book.getTitle());
  }
}
