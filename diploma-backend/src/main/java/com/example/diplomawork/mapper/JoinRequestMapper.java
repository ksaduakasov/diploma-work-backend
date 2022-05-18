package com.example.diplomawork.mapper;

import com.example.diplomawork.model.UserTeam;
import com.example.models.RequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JoinRequestMapper {

    RequestDto entity2dto(UserTeam userTeam);
}
