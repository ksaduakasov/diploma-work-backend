package com.example.diplomawork.controller;

import com.example.api.TeamsApi;
import com.example.diplomawork.service.TeamService;
import com.example.models.*;
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
    public ResponseEntity<TeamInfoWithMembersDto> getTeam() {
        return ResponseEntity.ok(teamService.getTeam());
    }

    @Override
    public ResponseEntity<Void> createTeam(TeamCreateUpdateRequest request) {
        teamService.createUpdateTeam(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateTeam(TeamCreateUpdateRequest request) {
        teamService.createUpdateTeam(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createRequestToTeam(Long teamId) {
        teamService.createRequestToTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserTeamInfoByBlockDto>> getTeamRequests() {
        return ResponseEntity.ok(teamService.getTeamJoinRequests());
    }

    @Override
    public ResponseEntity<Void> acceptTeamJoinRequest(Long requestId) {
        teamService.acceptTeamJoinRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeamJoinRequestInfoByBlocksDto>> getUserRequests() {
        return ResponseEntity.ok(teamService.getUserRequests());
    }
}
