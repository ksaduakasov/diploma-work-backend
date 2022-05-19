package com.example.diplomawork.controller;

import com.example.api.AdminApi;
import com.example.diplomawork.service.AdminService;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<Void> createDefence(Long teamId, CreateDefenceRequest request) {
        adminService.createDefence(teamId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TeamInfoByBlocksDto>> getTeams() {
        return ResponseEntity.ok(adminService.getTeams());
    }

    @Override
    public ResponseEntity<Void> createNewTeam(TeamCreateUpdateRequest request) {
        adminService.createUpdateTeam(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createNewTopic(TopicCreateUpdateRequest request) {
        adminService.createUpdateTopic(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createUser(RegisterRequest request) {
        adminService.createUpdateUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteTeam(Long teamId) {
        adminService.deleteTeam(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTopicInfo(Long topicId) {
        adminService.deleteTopic(topicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        adminService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TeamInfoByBlocksDto> getTeamInfo(Long teamId) {
        return ResponseEntity.ok(adminService.getTeamInfo(teamId));
    }

    @Override
    public ResponseEntity<TopicInfoByBlocksDto> getTopicInfo(Long topicId) {
        return ResponseEntity.ok(adminService.getTopicInfo(topicId));
    }

    @Override
    public ResponseEntity<UserInfoByBlocksDto> getUser(Long userId) {
        return ResponseEntity.ok(adminService.getUser(userId));
    }

    @Override
    public ResponseEntity<Void> updateTeamInfo(TeamCreateUpdateRequest request) {
        adminService.createUpdateTeam(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateTopicInfo(TopicCreateUpdateRequest request) {
        adminService.createUpdateTopic(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateUser(RegisterRequest request) {
        adminService.createUpdateUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createGroup(GroupDto groupDto) {
        adminService.createUpdateGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> createInitial(InitialDto initialDto) {
        adminService.createUpdateInitial(initialDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteGroupInfo(Long groupId) {
        adminService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteInitialInfo(Long initialId) {
        adminService.deleteInitial(initialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GroupInfoByBlocksDto> getGroupInfo(Long groupId) {
        return ResponseEntity.ok(adminService.getGroupInfo(groupId));
    }

    @Override
    public ResponseEntity<InitialInfoByBlocksDto> getInitialInfo(Long initialId) {
        return ResponseEntity.ok(adminService.getInitialInfo(initialId));
    }

    @Override
    public ResponseEntity<Void> updateGroupInfo(GroupDto groupDto) {
        adminService.createUpdateGroup(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateInitialInfo(InitialDto initialDto) {
        adminService.createUpdateInitial(initialDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createStage(StageDto stageDto) {
        adminService.createUpdateStage(stageDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteStage(Long stageId) {
        adminService.deleteStage(stageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StageInfoByBlocksDto> getStage(Long stageId) {
        return ResponseEntity.ok(adminService.getStageInfo(stageId));
    }

    @Override
    public ResponseEntity<Void> updateStage(StageDto stageDto) {
        adminService.createUpdateStage(stageDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
