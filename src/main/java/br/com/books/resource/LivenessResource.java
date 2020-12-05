package br.com.books.resource;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessResource implements HealthCheck {

  @Override
  public HealthCheckResponse call() {
    return HealthCheckResponse.up("I'm live");
  }
}
