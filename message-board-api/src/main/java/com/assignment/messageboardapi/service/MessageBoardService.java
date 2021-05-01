package com.assignment.messageboardapi.service;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;

public interface MessageBoardService {

  MessageDTO writeNewMessage(MessageDetails messageDetails);
}
