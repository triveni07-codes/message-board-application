package com.assignment.messageboardapi.controller;

import com.assignment.messageboardapi.exception.ErrorResponse;
import com.assignment.messageboardapi.exception.MessagesDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerAdviser {

  private static final String EXCEPTION_OCCURRED = "Exception occurred {}";

  @ExceptionHandler({MessagesDataException.class, Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    log.error(EXCEPTION_OCCURRED, e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

}
