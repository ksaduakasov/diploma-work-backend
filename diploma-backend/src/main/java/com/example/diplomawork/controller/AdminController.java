package com.example.diplomawork.controller;

import com.example.api.AdminApi;
import com.example.diplomawork.service.AdminService;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<AdminPanelGeneralInfoDto> getAdminPageInfo() {
        return ResponseEntity.ok(adminService.getAdminGeneralInfo());
    }

    @Override
    public ResponseEntity<Void> createNewTeam(TeamCreateUpdateRequest teamCreateUpdateRequest) {
        return AdminApi.super.createNewTeam(teamCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> createNewTopic(TopicCreateUpdateRequest topicCreateUpdateRequest) {
        return AdminApi.super.createNewTopic(topicCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> createUser(RegisterRequest registerRequest) {
        return AdminApi.super.createUser(registerRequest);
    }

    @Override
    public ResponseEntity<Void> deleteTeam(Long teamId) {
        return AdminApi.super.deleteTeam(teamId);
    }

    @Override
    public ResponseEntity<Void> deleteTopicInfo(Long topicId) {
        return AdminApi.super.deleteTopicInfo(topicId);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        return AdminApi.super.deleteUser(userId);
    }

    @Override
    public ResponseEntity<TeamInfoByBlocksDto> getTeamInfo(Long teamId) {
        return AdminApi.super.getTeamInfo(teamId);
    }

    @Override
    public ResponseEntity<TopicInfoByBlocksDto> getTopicInfo(Long topicId) {
        return AdminApi.super.getTopicInfo(topicId);
    }

    @Override
    public ResponseEntity<UserInfoByBlocksDto> getUser(Long userId) {
        return AdminApi.super.getUser(userId);
    }

    @Override
    public ResponseEntity<Void> updateTeamInfo(TeamCreateUpdateRequest teamCreateUpdateRequest) {
        return AdminApi.super.updateTeamInfo(teamCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> updateTopicInfo(TopicCreateUpdateRequest topicCreateUpdateRequest) {
        return AdminApi.super.updateTopicInfo(topicCreateUpdateRequest);
    }

    @Override
    public ResponseEntity<Void> updateUser(RegisterRequest registerRequest) {
        return AdminApi.super.updateUser(registerRequest);
    }
}
