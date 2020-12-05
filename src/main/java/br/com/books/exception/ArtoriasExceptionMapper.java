package br.com.books.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.books.exception.entity.ErrorCode;
import br.com.books.exception.entity.ErrorData;

@Provider
public class ArtoriasExceptionMapper implements ExceptionMapper<RuntimeException> {

  @Override
  public Response toResponse(final RuntimeException exception) {
    Status httpStatus = Status.INTERNAL_SERVER_ERROR;
    ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;

    switch (exception.getClass().getSimpleName()) {
      case "BusinessException":
        httpStatus = Status.NOT_ACCEPTABLE;
        errorCode = ErrorCode.BUSINESS_ERROR;
        break;
      case "GatewayException":
        httpStatus = Status.SERVICE_UNAVAILABLE;
        errorCode = ErrorCode.GATEWAY_ERROR;
        break;
      case "EntityNotFoundException":
        httpStatus = Status.NOT_FOUND;
        errorCode = ErrorCode.NOT_FOUND;
        break;
    }

    return Response.status(httpStatus)
        .entity(ErrorData.builder().message(exception.getMessage()).code(errorCode).build())
        .type(MediaType.APPLICATION_JSON).build();
  }
}
