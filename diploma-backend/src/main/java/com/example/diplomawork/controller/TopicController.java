package com.example.diplomawork.controller;

import com.example.api.TopicsApi;
import com.example.diplomawork.repository.TopicRepository;

import com.example.models.TopicCreateUpdateRequest;
import com.example.models.TopicDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopicController implements TopicsApi {

    private final TopicRepository topicRepository;

    private ObjectMapper objectMapper;

    @Override
    public ResponseEntity<List<TopicDto>> getAvailableTopics() {
        return null;
    }

    @Override
    public ResponseEntity<TopicDto> getTopic(Long topicId) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> createTopic(TopicCreateUpdateRequest topicCreateUpdateRequest) {
        return TopicsApi.super.createTopic(topicCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> updateTopic(TopicCreateUpdateRequest topicCreateUpdateRequest) {
        return TopicsApi.super.updateTopic(topicCreateUpdateRequest);
    }
}
