package com.assignment.messageboardapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.messageboardapi.api.dto.MessageDTO;
import com.assignment.messageboardapi.api.dto.MessageDetails;
import com.assignment.messageboardapi.constant.CustomHttpConstants;
import com.assignment.messageboardapi.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MessageBoardController.class)
class MessageBoardControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private MessageBoardController messageBoardController;

  @MockBean
  private MessageService messageService;

  private HttpHeaders httpHeaders;

  @BeforeEach
  public void setConfig() {
    httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.set(CustomHttpConstants.X_TRANSACTION_ID, "12345");
  }

  @Test
  public void testWriteMessage_givenMessageDetails_savesMessage() throws Exception {
    MessageDetails messageDetails = new MessageDetails();
    messageDetails.setMessage("someMessage");
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setMessage("someMessage");
    when(messageService.writeNewMessage(any())).thenReturn(messageDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/messages")
        .headers(httpHeaders)
        .content(new ObjectMapper().writeValueAsString(messageDetails)))
        .andExpect(status().isCreated());

    verify(messageService, times(1)).writeNewMessage(any());
  }

}