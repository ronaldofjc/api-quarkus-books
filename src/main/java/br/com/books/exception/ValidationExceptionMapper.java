package br.com.books.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

  @Override
  public Response toResponse(final ValidationException exception) {
    return Response.status(Status.BAD_REQUEST).entity(exception.getErrors()).type(MediaType.APPLICATION_JSON).build();
  }
}
