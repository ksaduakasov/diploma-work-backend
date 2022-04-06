package com.example.diplomawork.controller;

import com.example.api.TeamsApi;
import com.example.diplomawork.service.TeamService;
import com.example.models.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController implements TeamsApi {

    private final TeamService teamService;

    @Override
    public ResponseEntity<List<Team>> getAvailableTeams() {
        return null;
    }

    @Override
    public ResponseEntity<Team> getTeam(Long teamId) {
        return null;
    }
}
