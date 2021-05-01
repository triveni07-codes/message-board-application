package com.assignment.messageboardapi.controller;

import com.assignment.messageboardapi.api.MessageBoardApi;
import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.service.MessageBoardService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageBoardController implements MessageBoardApi {

  private final MessageBoardService messageBoardService;

  public MessageBoardController(MessageBoardService messageBoardService) {
    this.messageBoardService = messageBoardService;
  }

  @Override
  public ResponseEntity<MessageDTO> writeMessage(@Valid @NotNull MessageDetails messageDetails, String xTransactionId) {
    log.info("Request received to write new message with transactionId {}", xTransactionId);
    return new ResponseEntity<MessageDTO>(messageBoardService.writeNewMessage(messageDetails), HttpStatus.CREATED);
  }

}
