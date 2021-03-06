package com.assignment.messageboardapi.controller;

import com.assignment.messageboardapi.api.MessageBoardApi;
import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.api.dto.MessageModificationRequest;
import com.assignment.messageboardapi.service.MessageBoardService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Slf4j
public class MessageBoardController implements MessageBoardApi {

  private final MessageBoardService messageBoardService;

  public MessageBoardController(MessageBoardService messageBoardService) {
    this.messageBoardService = messageBoardService;
  }

  @Override
  public ResponseEntity<MessageDTO> createMessage(@Valid @NotNull MessageDetails messageDetails,
      String xTransactionId) {
    log.info("Request received to write new message with transactionId {}", xTransactionId);
    return new ResponseEntity<MessageDTO>(messageBoardService.createNewMessage(messageDetails), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<MessageDTO>> viewAllMessages(@NotBlank String xTransactionId) {
    log.info("Request received to fetch all messages with transactionId {}", xTransactionId);
    return new ResponseEntity<List<MessageDTO>>(messageBoardService.getAllMessages(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<MessageDTO> modifyMessage(@NotBlank String username, @NotBlank String messageId,
      MessageModificationRequest messageModificationRequest, @NotBlank String xTransactionId) {
    log.info("Request received to modify message with transactionId {}", xTransactionId);
    MessageDTO updatedMessage = messageBoardService
        .modifyMessage(username, messageId, messageModificationRequest.getMessageToBeUpdated());
    log.info("Message modified successfully transactionId {}", xTransactionId);
    return new ResponseEntity<MessageDTO>(updatedMessage, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> deleteMessage(@NotBlank String username, @NotNull String id,
      @NotBlank String xTransactionId) {
    log.info("Request received to delete message with transactionId {}", xTransactionId);
    messageBoardService.deleteMessage(username, id);
    log.info("Message deleted successfully with transactionId {}", xTransactionId);
    return new ResponseEntity<>("Message deleted successfully", HttpStatus.NO_CONTENT);
  }

}
