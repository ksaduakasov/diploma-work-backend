package com.example.diplomawork.service;

import com.example.diplomawork.mapper.TeamMapper;
import com.example.diplomawork.model.Team;
import com.example.diplomawork.repository.TeamRepository;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final AuthService authService;
    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public void createTeam(TeamCreateUpdateRequest request) {
        // TODO CHECK IS IT WORKS!!!
        Team team = teamMapper.request2entity(request);
        team.setCreator(authService.getCurrentUser());
        teamRepository.save(team);
    }

    public TeamDto getTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        return teamMapper.entity2dto(team);
    }

    public List<TeamDto> getAdvisorTeams(Long advisorId) {
        return null;
    }
}