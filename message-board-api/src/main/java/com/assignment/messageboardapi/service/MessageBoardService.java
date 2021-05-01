package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import java.util.List;

public interface MessageBoardService {

  MessageDTO createNewMessage(MessageDetails messageDetails);

  List<MessageDTO> getAllMessages();

  MessageDTO modifyMessage(String id, String message);

  void deleteMessage(String id);
}
