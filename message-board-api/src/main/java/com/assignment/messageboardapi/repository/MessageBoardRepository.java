package com.assignment.messageboardapi.repository;

import com.assignment.messageboardapi.model.database.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBoardRepository extends JpaRepository<MessageModel, Long> {

}
