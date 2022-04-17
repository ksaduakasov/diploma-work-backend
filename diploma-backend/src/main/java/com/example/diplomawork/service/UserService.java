package com.example.diplomawork.service;

import com.example.diplomawork.mapper.UserMapper;
import com.example.diplomawork.model.User;
import com.example.diplomawork.repository.UserRepository;
import com.example.models.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public void register(UserDto userDto) {
        User user = userMapper.dto2entity(userDto);
        log.info("register() - > new user created");
        userRepository.save(user);
    }
}
