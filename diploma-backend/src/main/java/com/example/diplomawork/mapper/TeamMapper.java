package com.example.diplomawork.mapper;

import com.example.diplomawork.model.Team;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamShortInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", source = "teamId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "topic.id", source = "topicId")
    @Mapping(target = "advisor.id", source = "advisorId")
    @Mapping(target = "creator", ignore = true)
    Team request2entity(TeamCreateUpdateRequest request);

    @Mapping(target = "topic", source = "topic.name")
    @Mapping(target = "advisor", expression = "java(team.getAdvisor() != null ? team.getAdvisor().getFirstName() + \" \" + team.getAdvisor().getLastName() : null)")
    TeamShortInfoDto entity2dto(Team team);
}
