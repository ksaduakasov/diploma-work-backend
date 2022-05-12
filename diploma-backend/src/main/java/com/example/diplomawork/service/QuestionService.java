package com.example.diplomawork.service;

import com.example.diplomawork.mapper.QuestionMapper;
import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.Question;
import com.example.diplomawork.repository.DefenceRepository;
import com.example.diplomawork.repository.QuestionRepository;
import com.example.models.QuestionCreateUpdateRequest;
import com.example.models.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {

    private final AuthService authService;

    private final QuestionRepository questionRepository;

    private final DefenceRepository defenceRepository;

    private final QuestionMapper questionMapper;

    public void createQuestion(QuestionCreateUpdateRequest request) {
        Defence defence = defenceRepository.findById(request.getDefenceId())
                .orElseThrow(() -> new EntityNotFoundException("Defence with id" + request.getDefenceId().toString() + " not found"));
        Question question = questionMapper.request2entity(request);
        question.setQuestioner(authService.getCurrentUser());
        questionRepository.save(question);
    }

    public List<QuestionDto> getDefenceQuestions(Long defenceId) {
        List<Question> questions = questionRepository.findAllByDefenceId(defenceId);
        return questions.stream().map(question -> questionMapper.entity2dto(question)).collect(Collectors.toList());
    }

    public QuestionDto getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question with id" + questionId + " not found"));
        return questionMapper.entity2dto(question);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public void updateQuestion(QuestionCreateUpdateRequest request) {
        Defence defence = defenceRepository.findById(request.getDefenceId())
                .orElseThrow(() -> new EntityNotFoundException("Defence with id" + request.getDefenceId().toString() + " not found"));
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new EntityNotFoundException("Question with id" + request.getQuestionId().toString() + " not found"));
        questionRepository.save(question);
    }
}