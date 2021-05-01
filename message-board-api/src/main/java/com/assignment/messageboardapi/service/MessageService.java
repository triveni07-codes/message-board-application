package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.model.database.MessageModel;
import com.assignment.messageboardapi.model.dto.MessageDTO;
import com.assignment.messageboardapi.model.message.MessageDetails;
import com.assignment.messageboardapi.repository.MessageBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService implements MessageBoardService{

  private final MessageBoardRepository messageBoardRepository;

  public MessageService(MessageBoardRepository messageBoardRepository) {
    this.messageBoardRepository = messageBoardRepository;
  }

  public MessageDTO writeNewMessage(MessageDetails messageDetails) {
    MessageModel messageModel = new MessageModel();
    messageModel.setMessage(messageDetails.getMessage());
    MessageModel savedMessageEntity = messageBoardRepository.save(messageModel);
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setMessage(savedMessageEntity.getMessage());
    return messageDTO;
  }

}
