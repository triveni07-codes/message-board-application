package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.model.dto.MessageDTO;
import com.assignment.messageboardapi.model.message.MessageDetails;

public interface MessageBoardService {

  MessageDTO writeNewMessage(MessageDetails messageDetails);
}
