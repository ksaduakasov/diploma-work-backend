package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Question;
import com.example.models.QuestionCreateUpdateRequest;
import com.example.models.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "questionId", source = "id")
    QuestionDto entity2dto(Question question);
}
