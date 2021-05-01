package com.assignment.messageboardapi.api;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.constant.CustomHttpConstants;
import com.assignment.messageboardapi.exception.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "messages", tags = "Messages API")
@RequestMapping(value = "/api")
public interface MessageBoardApi {

  @ApiOperation(value = "Writes messages to the board", nickname = "createMessage",
      notes = "To write new message to board:")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "A message was written to the board successfully", response = MessageDTO.class),
      @ApiResponse(code = 400, message = "Invalid input supplied", response = ErrorResponse.class)})
  @PostMapping(value = "/messages",
      produces = {"application/json"})
  ResponseEntity<MessageDTO> createMessage(
      @ApiParam(value = "Message details to be written")
      @Valid @NotNull @RequestBody MessageDetails messageDetails,
      @NotBlank @RequestHeader(value = CustomHttpConstants.X_TRANSACTION_ID) String xTransactionId);

  @ApiOperation(value = "Returns all messages on the board", nickname = "viewAllMessages",
      notes = "To read all the messages on the board:")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "List of messages retrieved"),
      @ApiResponse(code = 404, message = "Messages not found", response = ErrorResponse.class)})
  @GetMapping(value = "/messages",
      produces = {"application/json"})
  ResponseEntity<List<MessageDTO>> viewAllMessages(
      @ApiParam @NotBlank @RequestHeader(value = CustomHttpConstants.X_TRANSACTION_ID) String xTransactionId);

  @ApiOperation(value = "Update message details", nickname = "updateMessage", notes = "To update message")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "A message details has been updated", response = MessageDTO.class),
      @ApiResponse(code = 400, message = "Invalid input supplied", response = ErrorResponse.class)})
  @PutMapping(value = "/messages/{id}",
      produces = {"application/json"})
  ResponseEntity<MessageDTO> modifyMessage(
      @ApiParam(value = "Id of the message that needs to be modified", required = true)
      @NotBlank @PathVariable("id") String messageId,
      @ApiParam(value = "Modifications to be done", required = true)
      @RequestBody MessageDetails messageModificationRequest,
      @ApiParam @NotBlank @RequestHeader(value = CustomHttpConstants.X_TRANSACTION_ID) String xTransactionId);

}
