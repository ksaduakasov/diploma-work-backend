package com.example.diplomawork.mapper;

import com.example.diplomawork.model.User;
import com.example.models.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    User dto2entity(UserDto userDto);
}
