package com.example.diplomawork.controller;

import com.example.api.SecretaryApi;
import com.example.diplomawork.service.DocumentService;
import com.example.diplomawork.service.SecretaryService;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecretaryController implements SecretaryApi {

    private final SecretaryService secretaryService;

    private final DocumentService documentService;

    @Override
    public ResponseEntity<List<UserDto>> getDefenceCommissions(Long defenceId) {
        return ResponseEntity.ok(secretaryService.getDefenceCommission(defenceId));
    }

    @Override
    public ResponseEntity<Void> createQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        secretaryService.createUpdateQuestion(defenceId, request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        secretaryService.createUpdateQuestion(defenceId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> getDocumentFirstProtocol(Long userId) {
        return ResponseEntity.ok(documentService.getStudentDocument_FirstProtocol(userId));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> getDocumentSecondProtocol(Long userId) {
        return ResponseEntity.ok(documentService.getStudentDocument_SecondProtocol(userId));
    }

    @Override
    public ResponseEntity<DefenceInfoByBlocksDto> getSecretaryDefence(Long defenceId) {
        return ResponseEntity.ok(secretaryService.getDefenceInfo(defenceId));
    }

    @Override
    public ResponseEntity<List<DefenceShortInfoDto>> getSecretaryDefences() {
        return ResponseEntity.ok(secretaryService.getDefencesInfo());
    }

    @Override
    public ResponseEntity<List<StudentWithGradeDto>> getStudentsWithGrades(Long defenceId) {
        return ResponseEntity.ok(secretaryService.getStudentsWithGrades(defenceId));
    }

    @Override
    public ResponseEntity<Void> setFinalGrade(Long defenceId, Long userId, GradeDto gradeDto) {
        secretaryService.setFinalGrade(defenceId, userId, gradeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
