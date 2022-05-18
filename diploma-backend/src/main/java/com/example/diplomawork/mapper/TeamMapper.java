package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Team;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    Team dto2entity(TeamDto teamDto);

    @Mapping(target = "id", source = "teamId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "topic.id", source = "topicId")
    @Mapping(target = "advisor.id", source = "advisorId")
    Team request2entity(TeamCreateUpdateRequest request);

    TeamDto entity2dto(Team team);
}
