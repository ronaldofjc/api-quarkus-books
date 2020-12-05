package br.com.books.exception;

import java.util.Set;

import br.com.books.exception.entity.ErrorCode;
import br.com.books.exception.entity.ErrorData;
import lombok.ToString;

@ToString
public class ValidationException extends RuntimeException {

  static final long serialVersionUID = -9034816190742716963L;

  private final Set<ErrorData> errors;

  public ValidationException(final Set<ErrorData> errors) {
    this((String) null, (Throwable) null, errors);
  }

  public ValidationException(final String message, final Throwable cause, final Set<ErrorData> errors) {
    super(message, cause);
    errors.forEach(error -> error.setCode(ErrorCode.INVALID_PARAMETER));
    this.errors = errors;
  }

  public Set<ErrorData> getErrors() {
    return this.errors;
  }
}
