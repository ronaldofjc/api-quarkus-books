package br.com.books.usecase.validations;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.bson.types.ObjectId;

import br.com.books.exception.ValidationException;
import br.com.books.exception.entity.ErrorCode;
import br.com.books.exception.entity.ErrorData;

@ApplicationScoped
public class ValidateObjectId {

  public void execute(final String id) {
    if (!ObjectId.isValid(id)) {
      final Set<ErrorData> errors = new HashSet<>();
      errors.add(
          ErrorData.builder().message("Invalid ObjectId: " + id).field("ID").code(ErrorCode.INVALID_PARAMETER).build());
      throw new ValidationException(errors);
    }
  }
}
