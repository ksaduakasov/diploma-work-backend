package com.example.diplomawork.repository;

import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByDefenceId(Long defenceId);

    List<Question> findAllByDefenceIdAndResponderId(Long defenceId, Long responderId);

    List<Question> findAllByDefenceIdAndQuestionerId(Long defenceId, Long questionerId);
}
