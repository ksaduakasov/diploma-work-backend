package com.example.diplomawork.repository;

import com.example.diplomawork.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByConfirmedFalse();

    List<Team> findAllByConfirmedTrue();
}
