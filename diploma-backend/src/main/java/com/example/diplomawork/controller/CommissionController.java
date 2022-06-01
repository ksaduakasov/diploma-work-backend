package com.example.diplomawork.controller;

import com.example.api.CommissionApi;
import com.example.diplomawork.service.CommissionService;
import com.example.diplomawork.service.DocumentService;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommissionController implements CommissionApi {

    private final CommissionService commissionService;


    @Override
    public ResponseEntity<List<StudentWithGradeDto>> getStudentsWithCommissionGrades(Long defenceId) {
        return ResponseEntity.ok(commissionService.getStudentsWithCommissionGrades(defenceId));
    }

    @Override
    public ResponseEntity<Void> setGrade(Long defenceId, Long studentId, GradeDto gradeDto) {
        commissionService.setGrade(defenceId, studentId, gradeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DefenceInfoByBlocksDto> getCommissionDefence(Long defenceId) {
        return ResponseEntity.ok(commissionService.getCommissionDefence(defenceId));
    }

    @Override
    public ResponseEntity<Void> createDefenceQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        commissionService.createUpdateQuestion(defenceId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<DefenceShortInfoDto>> getCommissionDefences() {
        return ResponseEntity.ok(commissionService.getCommissionDefences());
    }

    @Override
    public ResponseEntity<Void> updateDefenceQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        commissionService.createUpdateQuestion(defenceId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
