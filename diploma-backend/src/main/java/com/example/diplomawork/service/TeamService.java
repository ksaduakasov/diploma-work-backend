package com.example.diplomawork.service;

import com.example.diplomawork.mapper.TeamMapper;
import com.example.diplomawork.model.Team;
import com.example.diplomawork.model.User;
import com.example.diplomawork.repository.TeamRepository;
import com.example.models.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    @Transactional
    public void createTeam(TeamDto teamDto) {
        // TODO CHECK IS IT WORKS!!!
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Team team = teamMapper.dto2entity(teamDto);
        team.setCreator(user);
        teamRepository.save(team);
    }

    @Transactional
    public TeamDto getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        return teamMapper.entity2dto(team);
    }

    @Transactional
    public List<TeamDto> getAdvisorTeams(Long advisorId) {

    }
}