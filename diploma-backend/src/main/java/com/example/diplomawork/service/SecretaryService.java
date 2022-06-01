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
public class SecretaryService {

    private final AuthService authService;

    private final DefenceRepository defenceRepository;

    private final QuestionRepository questionRepository;

    private final UserTeamRepository userTeamRepository;

    private final UserGradeRepository userGradeRepository;

    private final UserCommissionGradeRepository userCommissionGradeRepository;

    private final DefenceCommissionRepository defenceCommissionRepository;
    private final UserRepository userRepository;

    private final TeamMapper teamMapper;

    private final UserMapper userMapper;

    private final QuestionMapper questionMapper;

    private final DefenceMapper defenceMapper;

    public void createUpdateQuestion(Long defenceId, QuestionCreateUpdateRequest request) {
        List<Long> studentIds = request.getStudentIds();
        studentIds.stream().map(studentId -> Question.builder()
                .id(request.getQuestionId() != null ? request.getQuestionId() : null)
                .questioner(authService.getCurrentUser())
                .description(request.getDescription())
                .defence(defenceRepository.findById(defenceId).orElseThrow(() -> new EntityNotFoundException("Defence with id: " + defenceId + " not found")))
                .responder(userRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("User with id: " + studentId + " not found")))
                .questioner(userRepository.findById(request.getQuestionerId()).orElseThrow(() -> new EntityNotFoundException("User with id: " + studentId + " not found")))
                .build()).forEach(questionRepository::save);
    }

    public DefenceInfoByBlocksDto getDefenceInfo(Long defenceId) {
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
            questionDto.setQuestioner(question.getQuestioner().getLastName() + " " + question.getQuestioner().getFirstName().charAt(0) + ".");
            questions.add(questionDto);
        });
        return DefenceInfoByBlocksDto.builder()
                .defence(defenceMapper.entity2dto(defence))
                .team(teamInfo)
                .questions(questions)
                .build();
    }

    public List<DefenceShortInfoDto> getDefencesInfo() {
        List<Defence> defences = defenceRepository.findAll();
        List<DefenceShortInfoDto> dtos = new ArrayList<>();
        defences.forEach(defence -> {
            DefenceShortInfoDto dto = DefenceShortInfoDto.builder()
                    .id(defence.getId())
                    .defenceDate(defence.getDefenceDate())
                    .team(defence.getTeam().getName())
                    .topic(defence.getTeam().getTopic().getName())
                    .stage(defence.getStage().getName())
                    .build();
            dtos.add(dto);
        });
        return dtos;
    }

    public List<StudentWithGradeDto> getStudentsWithGrades(Long defenceId) {
        Defence defence = defenceRepository.findById(defenceId).orElseThrow(() -> new EntityNotFoundException("Defence with id: " + defenceId + " not found"));
        List<UserTeam> userTeams = userTeamRepository.findAllByTeamIdAndAcceptedTrue(defence.getTeam().getId());
        List<StudentWithGradeDto> students = new ArrayList<>();
        userTeams.forEach(userTeam -> {
            List<UserCommissionGrade> grades = userCommissionGradeRepository.findAllByDefenceIdAndStudentId(defenceId, userTeam.getUser().getId());
            students.add(StudentWithGradeDto.builder()
                    .id(userTeam.getUser().getId())
                    .fullName(userTeam.getUser().getFirstName() + " " + userTeam.getUser().getLastName())
                    .grade(userTeam.getUser().getGrade() != null ? userTeam.getUser().getGrade().getFinalGrade() : (!grades.isEmpty() ? grades.stream().mapToInt(UserCommissionGrade::getGrade).sum() / grades.size() : null))
                    .build());
        });
        return students;
    }

    public void setFinalGrade(Long defenceId, Long userId, GradeDto gradeDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " not found"));
        List<UserCommissionGrade> grades = userCommissionGradeRepository.findAllByDefenceIdAndStudentId(defenceId, userId);
        if (user.getGrade() != null) {
            user.getGrade().setFirstGrade(grades.stream().mapToInt(UserCommissionGrade::getGrade).sum() / grades.size());
            user.getGrade().setFinalGrade(gradeDto.getGrade());
            userRepository.save(user);
        } else {
            UserGrade userGrade = UserGrade.builder()
                    .id(null)
                    .firstGrade(!grades.isEmpty() ? grades.stream().mapToInt(UserCommissionGrade::getGrade).sum() / grades.size() : null)
                    .finalGrade(gradeDto.getGrade())
                    .student(user)
                    .build();
            userGradeRepository.save(userGrade);
        }
    }

    public List<UserDto> getDefenceCommission(Long defenceId) {
        List<DefenceCommission> defenceCommissions = defenceCommissionRepository.findDefenceCommissionsByDefenceId(defenceId);
        return defenceCommissions.stream().map(defenceCommission -> userMapper.entity2dto(defenceCommission.getCommission())).collect(Collectors.toList());
    }
}
