package com.assignment.messageboardapi.exception;

import org.springframework.http.HttpStatus;

public class MessagesDataException extends RuntimeException {

  private static final long serialVersionUID = 5373868865249521216L;

  private HttpStatus status;

  public MessagesDataException(Exception e) {
    super(e);
  }

  public MessagesDataException(String errorMessage) {
    super(errorMessage);
  }

  public MessagesDataException(String errorMessage, HttpStatus status) {
    super(errorMessage);
    this.status = status;
  }

  public MessagesDataException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
