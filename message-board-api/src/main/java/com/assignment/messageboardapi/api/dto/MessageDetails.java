package com.assignment.messageboardapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDetails {

  @JsonProperty("message")
  @Size(min = 2, max = 2000)
  @NotBlank
  private String message;

}
