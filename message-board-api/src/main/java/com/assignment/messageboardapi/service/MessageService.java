package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
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
    MessageModel savedMessage = messageBoardRepository.save(messageModel);
    return buildMessageDTO(savedMessage.getId(), savedMessage.getMessage());
  }

  @Override
  public List<MessageDTO> getAllMessages() {
    List<MessageDTO> messages;
    List<MessageModel> messageModels = messageBoardRepository.findAll();
    messages = messageModels.stream()
        .map(messageModel -> buildMessageDTO(messageModel.getId(), messageModel.getMessage()))
        .collect(Collectors.toList());

    return messages;
  }

  @Override
  public MessageDTO modifyMessage(String id, String message) {
    log.info("Updating message for id {}", id);
    MessageModel messageModel = findMessageById(id);
    messageModel.setMessage(message);
    MessageModel savedMessage = messageBoardRepository.save(messageModel);
    log.info("Updated message for id {}", id);
    return buildMessageDTO(savedMessage.getId(), savedMessage.getMessage());
  }

  public MessageModel findMessageById(String id) {
    Optional<MessageModel> messageModel = messageBoardRepository.findById(Long.parseLong(id));
    if (messageModel.isEmpty()) {
      throw new MessagesDataException("Message does not exist in the db to modify", HttpStatus.NOT_FOUND);
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
