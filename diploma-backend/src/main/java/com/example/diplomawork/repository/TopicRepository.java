package com.example.diplomawork.repository;

import com.example.diplomawork.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllBySelectedFalse();
}
