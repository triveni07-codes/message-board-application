package com.assignment.messageboardapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
import com.assignment.messageboardapi.exception.ErrorMessage;
import com.assignment.messageboardapi.exception.MessagesDataException;
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
    MessageDetails expectedMessage = new MessageDetails();
    expectedMessage.setMessage("someMessage");
    MessageModel mockEntity = buildMessageEntity();
    when(messageBoardRepository.save(any())).thenReturn(mockEntity);

    MessageDTO actualMessage = messageService.createNewMessage(expectedMessage);
    assertEquals(actualMessage.getMessage(), expectedMessage.getMessage());
    verify(messageBoardRepository, times(1)).save(any());

  }

  @Test
  public void testGetAllMessages_returnsListOfMessages() throws JsonProcessingException {
    List<MessageModel> mockMessagesList = new ArrayList<>();
    MessageModel messageModel = buildMessageEntity();
    mockMessagesList.add(messageModel);
    when(messageBoardRepository.findAll()).thenReturn(mockMessagesList);

    List<MessageDTO> actualMessageList = messageService.getAllMessages();
    assertEquals(mockMessagesList.size(), actualMessageList.size());
    verify(messageBoardRepository, times(1)).findAll();

  }

  @Test
  public void testUpdateMessage_givenMessageIdAndModificationContent_updatesMessage() throws JsonProcessingException {
    MessageModel messageModel = buildMessageEntity();
    when(messageBoardRepository.findById(anyLong())).thenReturn(java.util.Optional.of(messageModel));
    when(messageBoardRepository.save(any())).thenReturn(messageModel);

    MessageDTO updatedMessageDto = messageService.modifyMessage("admin", "1", "updatedMessage");
    assertEquals("updatedMessage", updatedMessageDto.getMessage());
    verify(messageBoardRepository, times(1)).save(any());

  }

  @Test
  public void testUpdateMessage_givenMessageAndNotMatchingUsername_throwsException() throws JsonProcessingException {
    MessageModel messageModel = buildMessageEntity();
    when(messageBoardRepository.findById(anyLong())).thenReturn(java.util.Optional.of(messageModel));

    Exception exception = assertThrows(MessagesDataException.class, () -> {
      messageService.modifyMessage("", "1", "updatedMessage");
    });

    String expectedMessage = String.valueOf(ErrorMessage.MODIFICATION_NOT_ALLOWED);
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
    verify(messageBoardRepository, never()).save(any());

  }

  @Test
  public void testDeleteMessage_givenMessageId_deletesMessage() throws JsonProcessingException {
    MessageModel messageModel = buildMessageEntity();
    when(messageBoardRepository.findById(anyLong())).thenReturn(java.util.Optional.of(messageModel));
    doNothing().when(messageBoardRepository).deleteById(anyLong());

    messageService.deleteMessage("admin", "1");
    verify(messageBoardRepository, times(1)).deleteById(anyLong());
  }

  @Test
  public void testDeleteMessage_givenMessageIdWithNotMatchingUsername_throwsException() throws JsonProcessingException {
    MessageModel messageModel = buildMessageEntity();
    when(messageBoardRepository.findById(anyLong())).thenReturn(java.util.Optional.of(messageModel));

    Exception exception = assertThrows(MessagesDataException.class, () -> {
      messageService.deleteMessage("", "1");
    });

    String expectedMessage = String.valueOf(ErrorMessage.DELETION_NOT_ALLOWED);
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
    verify(messageBoardRepository, never()).save(any());

  }

  private MessageModel buildMessageEntity() {
    MessageModel messageModel = new MessageModel();
    messageModel.setId(1L);
    messageModel.setMessage("someMessage");
    messageModel.setUsername("admin");
    return messageModel;
  }

}