package com.example.diplomawork.controller;

import com.example.api.TeamsApi;
import com.example.diplomawork.service.TeamService;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController implements TeamsApi {

    private final TeamService teamService;

    @Override
    public ResponseEntity<TeamDto> getTeam(Long teamId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> createTeam(TeamCreateUpdateRequest request) {
        teamService.createTeam(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateTeam(TeamCreateUpdateRequest teamCreateUpdateRequest) {
        return TeamsApi.super.updateTeam(teamCreateUpdateRequest);
    }
}
