package com.example.diplomawork.service;

import com.example.diplomawork.mapper.DefenceMapper;
import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.DefenceCommission;
import com.example.diplomawork.repository.*;
import com.example.models.DefenceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DefenceService {

    private final DefenceRepository defenceRepository;

    private final TeamRepository teamRepository;

    private final QuestionRepository questionRepository;

    private final StageRepository stageRepository;

    private final DefenceMapper defenceMapper;

    private final DefenceCommissionRepository defenceCommissionRepository;

    public List<DefenceDto> getAllDefencesShortInfo() {
        return defenceRepository.findAll().stream().map(defenceMapper::entity2dto).collect(Collectors.toList());
    }

    public List<DefenceDto> getDefences(Long commissionId) {
        List<DefenceCommission> defenceCommissions = defenceCommissionRepository.findDefenceCommissionsByCommissionId(commissionId);
        return defenceCommissions.stream().map(defenceCommission -> defenceMapper.entity2dto(defenceCommission.getDefence())).collect(Collectors.toList());
    }

    public void createDefence(DefenceDto dto) {
        Defence defence = defenceMapper.dto2entity(dto);
        defenceRepository.save(defence);
    }
}