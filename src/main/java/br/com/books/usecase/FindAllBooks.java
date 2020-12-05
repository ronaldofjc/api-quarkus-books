package br.com.books.usecase;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.entity.Book;
import br.com.books.exception.GatewayException;
import br.com.books.repository.BookRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class FindAllBooks {
  
  private final BookRepository repository;

  public List<Book> execute(int page, int size) {
    try {
      PanacheQuery<Book> books = repository.findAll();
      return books.page(Page.of(page -1, size)).list();
    } catch (Exception e) {
      log.error("Error when trying to get all books on database. StackTrace: {}", e);
      throw new GatewayException("Error when trying to get all books on database", e);
    }
  }

}
