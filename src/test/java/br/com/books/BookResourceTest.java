package br.com.books;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BookResourceTest {

  @Test
  public void validateApiFindAllBooks() {
    given()
      .when().get("/books")
      .then()
        .statusCode(200);
  }
}
