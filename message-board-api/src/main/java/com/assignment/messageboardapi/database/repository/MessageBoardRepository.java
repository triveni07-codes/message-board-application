package com.assignment.messageboardapi.database.repository;

import com.assignment.messageboardapi.database.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBoardRepository extends JpaRepository<MessageModel, Long> {

}
