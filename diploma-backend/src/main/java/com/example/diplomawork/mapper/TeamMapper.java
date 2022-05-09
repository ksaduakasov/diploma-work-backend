package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Team;
import com.example.models.TeamDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TeamMapper {
    Team dto2entity(TeamDto teamDto);

    TeamDto entity2dto(Team team);
}
