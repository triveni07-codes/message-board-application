package com.assignment.messageboardapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
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
  public void testCreateNewMessage_givenMessageDetails_savesMessageInDb() {
    MessageDetails messageDetails = new MessageDetails();
    messageDetails.setMessage("someMessage");
    MessageModel mockEntity = new MessageModel();
    mockEntity.setId(1L);
    mockEntity.setMessage(messageDetails.getMessage());

    when(messageBoardRepository.save(any())).thenReturn(mockEntity);

    MessageDTO expectedMessage = messageService.createNewMessage(messageDetails);
    assertEquals(expectedMessage.getMessage(), messageDetails.getMessage());

  }

  @Test
  public void testGetAllMessages_returnsListOfMessages() throws JsonProcessingException {
    List<MessageModel> mockMessagesList = new ArrayList<>();
    MessageModel messageModel = new MessageModel();
    messageModel.setId(1L);
    messageModel.setMessage("someMessage");
    mockMessagesList.add(messageModel);
    when(messageBoardRepository.findAll()).thenReturn(mockMessagesList);

    List<MessageDTO> expectedMessages = messageService.getAllMessages();
    assertEquals(expectedMessages.size(), mockMessagesList.size());
  }

}