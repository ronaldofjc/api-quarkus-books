package br.com.books.usecase.validations;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import br.com.books.exception.ValidationException;
import br.com.books.exception.entity.ErrorCode;
import br.com.books.exception.entity.ErrorData;

@ApplicationScoped
public class ValidatePageAndSizeParameters {

  public void execute(final int page, final int size) {
    final Set<ErrorData> errors = new HashSet<>();

    if (page < 1) {
      generateValidationError("Campo 'page' deve ser maior ou igual a um.", "page", errors);
    }

    if (size <= 0) {
      generateValidationError("Campo 'size' deve ser maior que zero.", "size", errors);
    }

    if (size > 1000) {
      generateValidationError("Campo 'size' não deve ser maior que 1000.", "size", errors);
    }

    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  private void generateValidationError(final String errorMessage, final String field, Set<ErrorData> errors) {
    errors.add(ErrorData.builder().message(errorMessage).field(field).code(ErrorCode.BAD_REQUEST).build());
  }
}
