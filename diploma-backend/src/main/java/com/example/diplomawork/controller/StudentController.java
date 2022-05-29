package com.example.diplomawork.controller;

import com.example.api.StudentApi;
import com.example.diplomawork.service.StudentService;
import com.example.models.TeamCreateUpdateRequest;
import com.example.models.TeamInfoWithMembersDto;
import com.example.models.TeamJoinRequestInfoByBlocksDto;
import com.example.models.UserTeamInfoByBlockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StudentController implements StudentApi {

    private final StudentService studentService;

    @Override
    public ResponseEntity<Void> acceptTeamJoinRequest(Long requestId) {
        studentService.acceptTeamJoinRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createRequestToTeam(Long teamId) {
        studentService.createRequestToTeam(teamId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createTeam(TeamCreateUpdateRequest request) {
        studentService.createUpdateTeam(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TeamInfoWithMembersDto> getTeam() {
        return ResponseEntity.ok(studentService.getTeam());
    }

    @Override
    public ResponseEntity<List<UserTeamInfoByBlockDto>> getTeamRequests() {
        return ResponseEntity.ok(studentService.getTeamJoinRequests());
    }

    @Override
    public ResponseEntity<List<TeamJoinRequestInfoByBlocksDto>> getUserRequests() {
        return ResponseEntity.ok(studentService.getUserRequests());
    }
}
