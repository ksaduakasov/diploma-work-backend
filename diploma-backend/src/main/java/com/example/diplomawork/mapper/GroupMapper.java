package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Group;
import com.example.models.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "initial", source = "initial.initial")
    GroupDto entity2dto(Group group);
}
