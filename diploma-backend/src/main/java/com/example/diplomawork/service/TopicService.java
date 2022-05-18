package com.example.diplomawork.service;

import com.example.diplomawork.mapper.InitialMapper;
import com.example.diplomawork.mapper.TopicMapper;
import com.example.diplomawork.mapper.UserMapper;
import com.example.diplomawork.model.Topic;
import com.example.diplomawork.model.User;
import com.example.diplomawork.repository.InitialRepository;
import com.example.diplomawork.repository.TopicRepository;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final AuthService authService;

    private final TopicRepository topicRepository;

    private final InitialRepository initialRepository;

    private final TopicMapper topicMapper;

    private final UserMapper userMapper;

    private final InitialMapper initialMapper;

    public void createUpdateTopic(TopicCreateUpdateRequest request) {
        User currentUser = authService.getCurrentUser();
        Topic topic = Topic.builder()
                .id(request.getId() != null ? request.getId() : null)
                .name(request.getName())
                .creator(currentUser)
                .initial(initialRepository.findByInitial(request.getInitial()))
                .selected(false)
                .build();
        topicRepository.save(topic);
    }

    public void deleteTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new EntityNotFoundException("Topic with id: " + topicId + " not found"));
        if (topic.getCreator() != authService.getCurrentUser()) {
            new IllegalAccessException("Not allowed action");
        }
        topicRepository.deleteById(topicId);
    }

    public TopicInfoByBlocksDto getTopic(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new EntityNotFoundException("Topic with id: " + topicId + " not found"));
        UserDto userDto = userMapper.entity2dto(topic.getCreator());
        InitialDto initialDto = initialMapper.entity2dto(topic.getInitial());
        TopicDto topicDto = topicMapper.entity2dto(topic);
        return TopicInfoByBlocksDto.builder()
                .topic(topicDto)
                .creator(userDto)
                .initial(initialDto)
                .build();
    }
}
