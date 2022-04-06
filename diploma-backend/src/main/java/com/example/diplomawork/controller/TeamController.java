package com.example.diplomawork.controller;

import com.example.api.TeamsApi;
import com.example.models.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController implements TeamsApi {

    @Override
    public ResponseEntity<List<Team>> getAvailableTeams() {
        return null;
    }

    @Override
    public ResponseEntity<Team> getTeam(Long teamId) {
        return null;
    }
}
