package br.com.books.usecase;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.entity.Book;
import br.com.books.exception.EntityNotFoundException;
import br.com.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindBookByTitle {
  
  private final BookRepository repository;

  public Book execute(final String title) {
    return repository.findByTitleOptional(title)
      .orElseThrow(() -> {
        log.error("Book with title {} not found on database", title);
        throw new EntityNotFoundException("Title: ", title, "Book with title " + title + " not found on database");
      }
    );
  }

}
