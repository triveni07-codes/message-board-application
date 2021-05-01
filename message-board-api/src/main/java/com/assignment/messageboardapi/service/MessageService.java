package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.database.model.MessageModel;
import com.assignment.messageboardapi.database.repository.MessageBoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService implements MessageBoardService {

  private final MessageBoardRepository messageBoardRepository;

  public MessageService(MessageBoardRepository messageBoardRepository) {
    this.messageBoardRepository = messageBoardRepository;
  }

  public MessageDTO createNewMessage(MessageDetails messageDetails) {
    MessageModel messageModel = new MessageModel();
    messageModel.setMessage(messageDetails.getMessage());
    MessageModel savedMessageEntity = messageBoardRepository.save(messageModel);
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setMessageId(messageModel.getId());
    messageDTO.setMessage(savedMessageEntity.getMessage());
    return messageDTO;
  }

  @Override
  public List<MessageDTO> getAllMessages() {
    List<MessageDTO> messages = new ArrayList<>();
    List<MessageModel> messageModels = messageBoardRepository.findAll();
    messageModels.forEach(messageModel -> {
      MessageDTO message = new MessageDTO();
      message.setMessageId(messageModel.getId());
      message.setMessage(messageModel.getMessage());
      messages.add(message);
    });

    return messages;
  }

}
