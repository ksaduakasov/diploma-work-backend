package com.example.diplomawork.repository;

import com.example.diplomawork.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllBySelectedFalse();

    Topic findByName(String topicName);

    List<Topic> findAllBySelectedTrue();
}
