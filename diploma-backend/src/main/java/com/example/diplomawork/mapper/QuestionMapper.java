package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Question;
import com.example.models.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "questionId", source = "id")
    @Mapping(target = "responderName", ignore = true)
    QuestionDto entity2dto(Question question);
}
