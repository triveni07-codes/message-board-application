package com.assignment.messageboardapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
class MessageServiceTest {

  @MockBean
  private MessageBoardRepository messageBoardRepository;

  private MessageService messageService;

  @BeforeEach
  public void setup() {
    messageService = new MessageService(messageBoardRepository);
  }

  @Test
  public void writeNewMessage() {
    MessageDetails messageDetails = new MessageDetails();
    messageDetails.setMessage("someMessage");
    MessageModel entitySaved = new MessageModel();
    entitySaved.setId(1L);
    entitySaved.setMessage(messageDetails.getMessage());

    when(messageBoardRepository.save(any())).thenReturn(entitySaved);

    MessageDTO message = messageService.writeNewMessage(messageDetails);
    assertEquals(messageDetails.getMessage(), message.getMessage());

  }

}