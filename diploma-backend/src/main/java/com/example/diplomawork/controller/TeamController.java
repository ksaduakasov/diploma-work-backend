package com.example.diplomawork.controller;

import com.example.api.TeamsApi;
import com.example.diplomawork.service.TeamService;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController implements TeamsApi {

    private final TeamService teamService;

    @Override
    public ResponseEntity<List<TeamDto>> getAvailableTeams() {
        return null;
    }

    @Override
    public ResponseEntity<TeamDto> getTeam(Long teamId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createTeam(TeamCreateUpdateRequest teamCreateUpdateRequest) {
        return TeamsApi.super.createTeam(teamCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> updateTeam(TeamCreateUpdateRequest teamCreateUpdateRequest) {
        return TeamsApi.super.updateTeam(teamCreateUpdateRequest);
    }
}
