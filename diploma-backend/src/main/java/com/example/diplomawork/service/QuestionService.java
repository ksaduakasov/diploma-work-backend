package com.example.diplomawork.service;

import com.example.diplomawork.mapper.QuestionMapper;
import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.Question;
import com.example.diplomawork.repository.DefenceRepository;
import com.example.diplomawork.repository.QuestionRepository;
import com.example.models.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final DefenceRepository defenceRepository;

    private final QuestionMapper questionMapper;

    @Transactional
    public void createQuestion(Long defenceId, QuestionDto questionDto) {
        Question question = questionMapper.dto2entity(questionDto);
        Defence defence = defenceRepository.findById(defenceId).orElse(null);
        if (defence == null) {
            throw new RuntimeException("Defence doesn't exist");
        }
        question.setDefence(defence);
        questionRepository.save(question);
    }

    @Transactional
    public List<QuestionDto> getDefenceQuestions(Long defenceId) {
        List<Question> questions = questionRepository.findAllByDefenceId(defenceId);
        return questions.stream().<QuestionDto>map(question -> questionMapper.entity2dto(question)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public void updateQuestion(QuestionDto questionDto) {
        Question question = questionMapper.dto2entity(questionDto);
        questionRepository.save(question);
    }
}