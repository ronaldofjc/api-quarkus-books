package br.com.books.resource;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import br.com.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Readiness
@RequiredArgsConstructor
public class ReadinessResource implements HealthCheck {
  
  private final BookRepository repository;

  @Override
  public HealthCheckResponse call() {
    if (!isHealthy()) {
      return HealthCheckResponse.down("I'm not ready yet...");
    }

    return HealthCheckResponse.up("I'm ready!");
  }

  public boolean isHealthy() {
    try {
      repository.count();  
    } catch (Exception e) {
      return false;
    } 
    
    return true; 
  }  
}
