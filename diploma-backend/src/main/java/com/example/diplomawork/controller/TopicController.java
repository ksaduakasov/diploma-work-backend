package com.example.diplomawork.controller;

import com.example.api.TopicsApi;
import com.example.models.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController implements TopicsApi {

    @Override
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return null;
    }

    @Override
    public ResponseEntity<Topic> getTopic(Long topicId) {
        return null;
    }
}
