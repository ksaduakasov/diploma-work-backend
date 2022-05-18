package com.example.diplomawork.controller;

import com.example.api.AdminApi;
import com.example.diplomawork.service.AdminService;
import com.example.models.AdminPanelGeneralInfoDto;
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
}
