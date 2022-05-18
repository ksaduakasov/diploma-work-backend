package com.example.diplomawork.service;

import com.example.diplomawork.mapper.*;
import com.example.diplomawork.repository.*;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final InitialRepository initialRepository;

    private final GroupRepository groupRepository;

    private final TeamRepository teamRepository;

    private final DefenceRepository defenceRepository;

    private final TopicRepository topicRepository;

    private final DefenceMapper defenceMapper;

    private final TeamMapper teamMapper;

    private final TopicMapper topicMapper;

    private final RoleMapper roleMapper;

    private final GroupMapper groupMapper;

    private final UserMapper userMapper;

    private final StageMapper stageMapper;

    private final InitialMapper initialMapper;

    public AdminPanelGeneralInfoDto getAdminGeneralInfo() {
        return AdminPanelGeneralInfoDto.builder()
                .defences(getDefences())
                .teams(getConfirmedTeams())
                .topics(getSelectedTopics())
                .users(getUsers())
                .groups(getGroups())
                .initials(getInitials())
                .build();
    }
    private List<DefenceInfoByBlocksDto> getDefences() {
        return defenceRepository.findAll().stream().map(defence -> DefenceInfoByBlocksDto.builder()
                .defence(defenceMapper.entity2dto(defence))
                .stage(stageMapper.entity2dto(defence.getStage()))
                .team(teamMapper.entity2dto(defence.getTeam()))
                .build()).collect(Collectors.toList());
    }

    private List<TeamInfoByBlocksDto> getConfirmedTeams() {
        return teamRepository.findAllByConfirmedTrue().stream().map(team -> TeamInfoByBlocksDto.builder()
                .team(teamMapper.entity2dto(team))
                .advisor(userMapper.entity2dto(team.getAdvisor()))
                .creator(userMapper.entity2dto(team.getCreator()))
                .topic(topicMapper.entity2dto(team.getTopic()))
                .build()).collect(Collectors.toList());
    }

    private List<TopicInfoByBlocksDto> getSelectedTopics() {
        return topicRepository.findAllBySelectedTrue().stream().map(topic -> TopicInfoByBlocksDto.builder()
                .topic(topicMapper.entity2dto(topic))
                .creator(userMapper.entity2dto(topic.getCreator()))
                .initial(initialMapper.entity2dto(topic.getInitial()))
                .build()).collect(Collectors.toList());
    }

    private List<UserInfoByBlocksDto> getUsers() {
        return userRepository.findAll().stream().map(user -> UserInfoByBlocksDto.builder()
                .user(userMapper.entity2dto(user))
                .group(groupMapper.entity2dto(user.getGroup()))
                .role(roleMapper.entity2dto(user.getRole()))
                .build()).collect(Collectors.toList());
    }

    private List<GroupDto> getGroups() {
        return groupRepository.findAll().stream().map(groupMapper::entity2dto).collect(Collectors.toList());
    }

    private List<InitialDto> getInitials() {
        return initialRepository.findAll().stream().map(initialMapper::entity2dto).collect(Collectors.toList());
    }
}
