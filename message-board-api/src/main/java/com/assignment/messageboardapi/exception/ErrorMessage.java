package com.assignment.messageboardapi.exception;

public enum ErrorMessage {

  MODIFICATION_NOT_ALLOWED("Permission not allowed to modify message."),
  DELETION_NOT_ALLOWED("Permission not allowed to delete message."),
  MESSAGE_NOT_FOUND("Message does not exist in the db to modify");

  private String errorMessage;

  ErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}
