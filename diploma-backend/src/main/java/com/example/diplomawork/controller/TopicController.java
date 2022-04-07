package com.example.diplomawork.controller;

import com.example.api.TopicsApi;
import com.example.diplomawork.repository.TopicRepository;

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
}
