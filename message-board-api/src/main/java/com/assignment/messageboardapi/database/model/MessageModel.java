package com.assignment.messageboardapi.database.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "MESSAGE_BOARD")
public class MessageModel implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "MESSAGE")
  private String message;
}
