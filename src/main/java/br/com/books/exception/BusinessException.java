package br.com.books.exception;

public class BusinessException extends RuntimeException {

  static final long serialVersionUID = -9034816190742716963L;

  public BusinessException(final String message) {
    super(message);
  }

}
