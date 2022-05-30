package com.example.diplomawork.service;

import com.example.diplomawork.repository.DefenceRepository;
import com.example.diplomawork.util.DocumentUtil;
import com.example.models.DefenceInfoByBlocksDto;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final DefenceRepository defenceRepository;

    private final CommissionService commissionService;


    public byte[] getDefenceDocuments(Long defenceId) throws DocumentException, IOException {
        DefenceInfoByBlocksDto dto = commissionService.getCommissionDefence(defenceId);
        DocumentUtil.generatePdf(dto, commissionService.getDefenceCommissions(defenceId));
        File path = new File(
                "protocol.pdf");
        FileInputStream fl = new FileInputStream(path);
        byte[] arr = new byte[(int)path.length()];
        fl.read(arr);
        fl.close();
        return Base64.getEncoder().encode(arr);
    }
}
