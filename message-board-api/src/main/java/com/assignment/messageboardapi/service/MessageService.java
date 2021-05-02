package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
import com.assignment.messageboardapi.exception.ErrorMessage;
import com.assignment.messageboardapi.exception.MessagesDataException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService implements MessageBoardService {

  private final MessageBoardRepository messageBoardRepository;

  public MessageService(MessageBoardRepository messageBoardRepository) {
    this.messageBoardRepository = messageBoardRepository;
  }

  @Override
  public MessageDTO createNewMessage(MessageDetails messageDetails) {
    MessageModel messageModel = new MessageModel();
    messageModel.setMessage(messageDetails.getMessage());
    messageModel.setUsername(messageDetails.getUsername());
    MessageModel savedMessage = messageBoardRepository.save(messageModel);
    return buildMessageDTO(savedMessage.getId(), savedMessage.getMessage());
  }

  @Override
  public List<MessageDTO> getAllMessages() {
    return messageBoardRepository.findAll()
        .stream()
        .map(messageModel -> buildMessageDTO(messageModel.getId(), messageModel.getMessage()))
        .collect(Collectors.toList());

  }

  @Override
  public MessageDTO modifyMessage(String username, String id, String message) {
    log.info("Updating message for id {}", id);
    MessageModel messageModel = findMessageById(id);
    if (!username.equals(messageModel.getUsername())) {
      throw new MessagesDataException(String.valueOf(ErrorMessage.MODIFICATION_NOT_ALLOWED), HttpStatus.UNAUTHORIZED);
    }
    messageModel.setMessage(message);
    MessageModel savedMessage = messageBoardRepository.save(messageModel);
    log.info("Updated message for id {}", id);
    return buildMessageDTO(savedMessage.getId(), savedMessage.getMessage());

  }

  @Override
  public void deleteMessage(String username, String id) {
    MessageModel messageModel = findMessageById(id);
    if (!username.equals(messageModel.getUsername())) {
      throw new MessagesDataException(String.valueOf(ErrorMessage.DELETION_NOT_ALLOWED), HttpStatus.UNAUTHORIZED);
    }
    messageBoardRepository.deleteById(Long.parseLong(id));

  }

  public MessageModel findMessageById(String id) {
    Optional<MessageModel> messageModel = messageBoardRepository.findById(Long.parseLong(id));
    if (messageModel.isEmpty()) {
      throw new MessagesDataException(String.valueOf(ErrorMessage.MESSAGE_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    return messageModel.get();
  }

  private MessageDTO buildMessageDTO(Long id, String message) {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setMessageId(id);
    messageDTO.setMessage(message);
    return messageDTO;
  }

}
