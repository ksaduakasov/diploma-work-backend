package com.example.diplomawork.controller;

import com.example.api.TopicsApi;
import com.example.diplomawork.repository.TopicRepository;

import com.example.diplomawork.service.TeamService;
import com.example.diplomawork.service.TopicService;
import com.example.models.TopicCreateUpdateRequest;
import com.example.models.TopicDto;
import com.example.models.TopicInfoByBlocksDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopicController implements TopicsApi {

    private final TopicService topicService;

    @Override
    public ResponseEntity<List<TopicInfoByBlocksDto>> getAvailableTopics() {
        return null;
    }

    @Override
    public ResponseEntity<TopicInfoByBlocksDto> getTopic(Long topicId) {
        return ResponseEntity.ok(topicService.getTopic(topicId));
    }

    @Override
    public ResponseEntity<Void> createTopic(TopicCreateUpdateRequest request) {
        topicService.createUpdateTopic(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTopic(Long topicId) {
        topicService.deleteTopic(topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateTopic(TopicCreateUpdateRequest request) {
        topicService.createUpdateTopic(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
