package com.example.diplomawork.service;

import com.example.diplomawork.repository.GroupRepository;
import com.example.diplomawork.repository.InitialRepository;
import com.example.diplomawork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final InitialRepository initialRepository;

    private final GroupRepository groupRepository;

    private final TopicService topicService;
}
