package com.example.diplomawork.service;

import com.example.diplomawork.model.Stage;
import com.example.diplomawork.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;

    public void createStage(Stage stage) {
        stageRepository.save(stage);
    }

    public void deleteStage(Long stageId) {
        stageRepository.deleteById(stageId);
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }
}
