package com.assignment.messageboardapi.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  @JsonProperty("errorCode")
  private int errorCode;
  @JsonProperty("errorMessage")
  private String errorMessage;
}
