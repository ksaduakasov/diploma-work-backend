package com.example.diplomawork.service;

import com.example.diplomawork.mapper.DefenceMapper;
import com.example.diplomawork.mapper.QuestionMapper;
import com.example.diplomawork.mapper.TeamMapper;
import com.example.diplomawork.mapper.UserMapper;
import com.example.diplomawork.model.*;
import com.example.diplomawork.repository.*;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommissionService {

    private final UserTeamRepository userTeamRepository;

    private final DefenceRepository defenceRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    private final DefenceCommissionRepository defenceCommissionRepository;

    private final AuthService authService;

    private final TeamMapper teamMapper;

    private final UserMapper userMapper;

    private final DefenceMapper defenceMapper;

    private final QuestionMapper questionMapper;

    public DefenceInfoByBlocksDto getCommissionDefence(Long defenceId) {
        Defence defence = defenceRepository.findById(defenceId).orElseThrow(() -> new EntityNotFoundException("Defence with id: " + defenceId + " not found"));
        Team team = defence.getTeam();
        List<UserTeam> userTeams = userTeamRepository.findAllByTeamIdAndAcceptedTrue(team.getId());
        TeamInfoWithMembersDto teamInfo = TeamInfoWithMembersDto.builder()
                .team(teamMapper.entity2dto(team).advisor(team.getAdvisor() != null ? team.getAdvisor().getFirstName() + " " + team.getAdvisor().getLastName() : null))
                .members(userTeams.stream().map(userTeam -> userMapper.entity2dto(userTeam.getUser())).collect(Collectors.toList()))
                .build();
        List<QuestionDto> questions = new ArrayList<>();
        questionRepository.findAllByDefenceId(defenceId).forEach(question -> {
            QuestionDto questionDto = questionMapper.entity2dto(question);
            questionDto.setResponderName(question.getResponder().getLastName() + " " + question.getResponder().getFirstName().charAt(0) + ".");
            questions.add(questionDto);
        });
        return DefenceInfoByBlocksDto.builder()
                .defence(defenceMapper.entity2dto(defence))
                .team(teamInfo)
                .questions(questions)
                .build();
    }

    public void createUpdateQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        List<Long> studentIds = request.getStudentIds();
        studentIds.stream().map(studentId -> Question.builder()
                .id(request.getQuestionId() != null ? request.getQuestionId() : null)
                .questioner(authService.getCurrentUser())
                .description(request.getDescription())
                .defence(defenceRepository.findById(defenceId).orElseThrow(() -> new EntityNotFoundException("Defence with id: " + defenceId + " not found")))
                .responder(userRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("User with id: " + studentId + " not found")))
                .grade(request.getGrade())
                .build()).forEach(questionRepository::save);
    }

    public List<DefenceShortInfoDto> getCommissionDefences() {
        User currentUser = authService.getCurrentUser();
        List<DefenceCommission> defenceCommissions = defenceCommissionRepository.findDefenceCommissionsByCommissionId(currentUser.getId());
        List<DefenceShortInfoDto> list = new ArrayList<>();
        defenceCommissions.forEach(defenceCommission -> {
            Defence defence = defenceCommission.getDefence();
            List<Question> questions = questionRepository.findAllByDefenceId(defence.getId());
            DefenceShortInfoDto build = DefenceShortInfoDto.builder()
                    .id(defence.getId())
                    .defenceDate(defenceCommission.getDefence().getDefenceDate())
                    .team(defenceCommission.getDefence().getTeam().getName())
                    .topic(defenceCommission.getDefence().getTeam().getTopic().getName())
                    .grade(!questions.isEmpty() ? questions.stream().mapToInt(Question::getGrade).sum() / questions.size() : null)
                    .stage(defence.getStage().getName())
                    .build();
            list.add(build);
        });
        return list;
    }

    public List<UserDto> getDefenceCommissions(Long defenceId) {
        List<DefenceCommission> defenceCommissions = defenceCommissionRepository.findDefenceCommissionsByDefenceId(defenceId);
        return defenceCommissions.stream().map(defence -> userMapper.entity2dto(defence.getCommission())).collect(Collectors.toList());
    }
}
