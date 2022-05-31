package com.example.diplomawork.service;

import com.example.diplomawork.mapper.UserMapper;
import com.example.diplomawork.model.Defence;
import com.example.diplomawork.model.Question;
import com.example.diplomawork.model.User;
import com.example.diplomawork.model.UserTeam;
import com.example.diplomawork.repository.DefenceRepository;
import com.example.diplomawork.repository.QuestionRepository;
import com.example.diplomawork.repository.UserRepository;
import com.example.diplomawork.repository.UserTeamRepository;
import com.example.diplomawork.util.DocumentUtil;

import com.example.models.*;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final DefenceRepository defenceRepository;

    private final CommissionService commissionService;

    private final UserRepository userRepository;

    private final UserTeamRepository userTeamRepository;

    private final QuestionRepository questionRepository;

    private final UserMapper userMapper;

    public byte[] getStudentDocument_FirstProtocol(Long userId) throws DocumentException, IOException {
        User student = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserTeam userTeam = userTeamRepository.findByUserIdAndAcceptedTrue(student.getId()).orElseThrow(() -> new EntityNotFoundException("UserTeam not found"));
        Defence defence = defenceRepository.findByTeamIdAndStageName(userTeam.getTeam().getId(), "DEFENCE");
        List<Question> questions = questionRepository.findAllByDefenceIdAndResponderId(defence.getId(), userId);
        List<UserDto> commissions = commissionService.getDefenceCommissions(defence.getId());

        List<QuestionInfoDto> questionInfoDtos = questions.stream().map(question -> QuestionInfoDto.builder()
                .description(question.getDescription())
                .questioner(userMapper.entity2dto(question.getQuestioner()))
                .grade(question.getGrade())
                .build()).collect(Collectors.toList());

        DefenceShortInfoDto defenceInfo = DefenceShortInfoDto.builder()
                .id(defence.getId())
                .defenceDate(defence.getDefenceDate())
                .team(userTeam.getTeam().getName())
                .topic(userTeam.getTeam().getTopic().getName())
                .grade(!questions.isEmpty() ? questions.stream().mapToInt(Question::getGrade).sum() / questions.size() : null)
                .stage(defence.getStage().getName())
                .build();

        UserDto advisor = userMapper.entity2dto(userTeam.getTeam().getAdvisor());

        InfoForDocumentTemplateDto dto = InfoForDocumentTemplateDto.builder()
                .student(StudentShortInfoDto.builder().firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .group(student.getGroup().getName())
                        .build())
                .defence(defenceInfo)
                .advisor(advisor)
                .commissions(commissions)
                .questions(questionInfoDtos)
                .grade(student.getGrade().getFinalGrade())
                .build();
        DocumentUtil.generateFirstProtocolPdf(dto);
        File path = new File(
                "protocol1.pdf");
        FileInputStream fl = new FileInputStream(path);
        byte[] arr = new byte[(int) path.length()];
        fl.read(arr);
        fl.close();
        return Base64.getEncoder().encode(arr);
    }


    public byte[] getStudentDocument_SecondProtocol(Long userId) throws DocumentException, IOException {
        User student = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserTeam userTeam = userTeamRepository.findByUserIdAndAcceptedTrue(student.getId()).orElseThrow(() -> new EntityNotFoundException("UserTeam not found"));
        Defence defence = defenceRepository.findByTeamIdAndStageName(userTeam.getTeam().getId(), "DEFENCE");
        List<Question> questions = questionRepository.findAllByDefenceIdAndResponderId(defence.getId(), userId);
        List<UserDto> commissions = commissionService.getDefenceCommissions(defence.getId());

        List<QuestionInfoDto> questionInfoDtos = questions.stream().map(question -> QuestionInfoDto.builder()
                .description(question.getDescription())
                .questioner(userMapper.entity2dto(question.getQuestioner()))
                .grade(question.getGrade())
                .build()).collect(Collectors.toList());

        DefenceShortInfoDto defenceInfo = DefenceShortInfoDto.builder()
                .id(defence.getId())
                .defenceDate(defence.getDefenceDate())
                .team(userTeam.getTeam().getName())
                .topic(userTeam.getTeam().getTopic().getName())
                .grade(!questions.isEmpty() ? questions.stream().mapToInt(Question::getGrade).sum() / questions.size() : null)
                .stage(defence.getStage().getName())
                .build();

        UserDto advisor = userMapper.entity2dto(userTeam.getTeam().getAdvisor());

        InfoForDocumentTemplateDto dto = InfoForDocumentTemplateDto.builder()
                .student(StudentShortInfoDto.builder().firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .group("SE-1901")
                        .build())
                .defence(defenceInfo)
                .advisor(advisor)
                .commissions(commissions)
                .questions(questionInfoDtos)
                .grade(student.getGrade().getFinalGrade())
                .build();
        DocumentUtil.generateSecondProtocolPdf(dto);
        File path = new File(
                "protocol2.pdf");
        FileInputStream fl = new FileInputStream(path);
        byte[] arr = new byte[(int) path.length()];
        fl.read(arr);
        fl.close();
        return Base64.getEncoder().encode(arr);
    }
}
