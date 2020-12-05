package br.com.books.usecase;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.exception.GatewayException;
import br.com.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CountBooks {
  
  private final BookRepository repository;

  public Long execute() {
    try {
      return repository.count();
    } catch (Exception e) {
      log.error("Error when trying to count books on database: " + e);
      throw new GatewayException("Error when trying to count books on database", e);
    }
  }
}
