package com.assignment.messageboardapi.api;

import com.assignment.messageboardapi.model.dto.MessageDTO;
import com.assignment.messageboardapi.model.message.MessageDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "messages", tags = "Messages API")
@RequestMapping(value = "/api")
public interface MessageBoardApi {

  @ApiOperation(value = "Writes messages to the board", nickname = "writeMessage",
      notes = "To write new message to board:")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "A message written to the board"),
      @ApiResponse(code = 400, message = "Invalid input supplied")})
  @PostMapping(value = "/messages",
      produces = {"application/json"})
  ResponseEntity<MessageDTO> writeMessage(@ApiParam(value = "Message details to be written") @Valid @NotNull
  @RequestBody MessageDetails messageDetails);

}
