package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Defence;
import com.example.models.DefenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefenceMapper {

    @Mapping(target = "defenceId", source = "id")
    @Mapping(target = "teamId", source = "team.id")
    @Mapping(target = "stageId", source = "stage.id")
    DefenceDto entity2dto(Defence defence);
}
