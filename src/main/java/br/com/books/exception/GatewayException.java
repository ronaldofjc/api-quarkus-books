package br.com.books.exception;

public class GatewayException extends RuntimeException {

  static final long serialVersionUID = -9034816190742716963L;

  public GatewayException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
