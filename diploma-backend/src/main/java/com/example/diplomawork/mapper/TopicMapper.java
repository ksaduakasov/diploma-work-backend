package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Topic;
import com.example.models.TopicCreateUpdateRequest;
import com.example.models.TopicDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(target = "initial", ignore = true)
    Topic request2entity(TopicCreateUpdateRequest request);

    TopicDto entity2dto(Topic topic);
}
