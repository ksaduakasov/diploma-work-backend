package com.example.diplomawork.service;

import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.Document;
import com.example.diplomawork.repository.DefenceRepository;
import com.example.diplomawork.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final DefenceRepository defenceRepository;

    public List<Document> getDefenceDocuments(Long defenceId) {
        Defence defence = defenceRepository.findById(defenceId)
                .orElseThrow(() -> new EntityNotFoundException("Defence with id" + defenceId + " not found"));
        return documentRepository.findDocumentsByDefenceId(defenceId);
    }
}
