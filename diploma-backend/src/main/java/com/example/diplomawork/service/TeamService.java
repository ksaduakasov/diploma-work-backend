package com.example.diplomawork.service;

import com.example.diplomawork.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
}
