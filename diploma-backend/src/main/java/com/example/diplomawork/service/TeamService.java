package com.example.diplomawork.service;

import com.example.diplomawork.repository.TeamRepository;
import com.example.models.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    @Transactional
    public void createTeam(TeamDto teamDto) {

    }
}
