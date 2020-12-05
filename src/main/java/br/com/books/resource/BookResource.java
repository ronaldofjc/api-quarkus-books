package br.com.books.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.books.entity.Book;
import br.com.books.resource.entity.CreateBookRequest;
import br.com.books.resource.entity.UpdateBookRequest;
import br.com.books.usecase.CountBooks;
import br.com.books.usecase.CreateBook;
import br.com.books.usecase.DeleteBook;
import br.com.books.usecase.FindAllBooks;
import br.com.books.usecase.FindBookById;
import br.com.books.usecase.FindBookByTitle;
import br.com.books.usecase.UpdateBook;
import br.com.books.usecase.validations.ValidateObjectId;
import br.com.books.usecase.validations.ValidatePageAndSizeParameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/books")
@Tag(name = "Books")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
  
  private final ValidatePageAndSizeParameters validatePageAndSizeParameters;
  private final ValidateObjectId validateObjectId;
  private final CreateBook createBook;
  private final FindAllBooks findAllBooks;
  private final DeleteBook deleteBook;
  private final UpdateBook updateBook;
  private final FindBookById findBookById;
  private final FindBookByTitle findBookByTitle;
  private final CountBooks countBooks;

  @POST
  @Operation(summary = "Realiza a criação de um novo livro")
  public Response create(@Valid CreateBookRequest createBookRequest) {
    log.info("PAYLOAD - Create new book: {}", createBookRequest.toString());
    createBook.execute(createBookRequest);
    return Response.status(Status.CREATED).build();
  }

  @GET
  @Operation(summary = "Retorna a lista de livros")
  public List<Book> findAll(@DefaultValue("1") @QueryParam("page") int page,
                            @DefaultValue("20") @QueryParam("size") int size) {
    log.info("Find all books - pagination: page {}, size {}", page, size);
    validatePageAndSizeParameters.execute(page, size);
    return findAllBooks.execute(page, size);
  }

  @DELETE
  @Path("/{id}")
  @Operation(summary = "Realiza a remoção de um livro pelo id")
  public void remove(@PathParam("id") String id) {
    log.info("PAYLOAD - Remove book with Id: {}", id);
    validateObjectId.execute(id);
    deleteBook.execute(id);
  }

  @PUT
  @Path("/{id}")
  @Operation(summary = "Realiza a atualização de um livro pelo id")
  public void update(@PathParam("id") String id, @Valid UpdateBookRequest updateBookRequest) {
    log.info("PAYLOAD - Update book: {}", updateBookRequest.toString());
    validateObjectId.execute(id);
    updateBook.execute(updateBookRequest, id);
  }

  @GET
  @Path("/{id}")
  @Operation(summary = "Realiza a busca de um livro pelo id")
  public Book findById(@PathParam("id") String id) {
    log.info("PAYLOAD - Find book by Id: {}", id);
    validateObjectId.execute(id);
    return findBookById.execute(id);
  }

  @GET
  @Path("/title")
  @Operation(summary = "Realiza a busca de um livro pelo título")
  public Book findByTitle(@QueryParam("queryParam") String title) {
    log.info("PAYLOAD - Find book by title: {}", title);
    return findBookByTitle.execute(title);
  }

  @GET
  @Path("/count")
  @Operation(summary = "Retorna a quantidade de livros cadastrados")
  public Long count() {
    return countBooks.execute();
  }

}
