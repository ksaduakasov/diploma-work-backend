package com.example.diplomawork.service;

import com.example.diplomawork.repository.TeamRepository;
import com.example.diplomawork.repository.TeamTopicRepository;
import com.example.diplomawork.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdvisorService {

    private final TeamRepository teamRepository;

    private final TopicRepository topicRepository;

    private final TeamTopicRepository teamTopicRepository;

}
