package com.assignment.messageboardapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

  @JsonProperty("messageId")
  private Long messageId;

  @JsonProperty("message")
  private String message;

}
