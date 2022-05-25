package com.example.diplomawork.controller;

import com.example.api.CommissionApi;
import com.example.diplomawork.service.CommissionService;
import com.example.diplomawork.service.DocumentService;
import com.example.models.DefenceInfoByBlocksDto;
import com.example.models.DefenceShortInfoDto;
import com.example.models.QuestionCreateUpdateRequest;
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

    private final DocumentService documentService;

    @SneakyThrows
    @Override
    public ResponseEntity<byte[]> getDefenceDocuments(Long defenceId) {
        return ResponseEntity.ok(documentService.getDefenceDocuments(defenceId));
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
