package com.example.diplomawork.service;

import com.example.diplomawork.mapper.*;
import com.example.diplomawork.model.*;
import com.example.diplomawork.repository.*;
import com.example.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

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

    private final PasswordEncoder passwordEncoder;

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

    public void createUpdateTeam(TeamCreateUpdateRequest request) {
        Team team = Team.builder()
                .id(request.getTeamId() != null ? request.getTeamId() : null)
                .name(request.getName())
                .advisor(request.getAdvisorId() != null ? userRepository.findById(request.getAdvisorId()).orElseThrow(() -> new EntityNotFoundException("Not found")) : null)
                .confirmed(true)
                .topic(request.getTopicId() != null ? topicRepository.findById(request.getTopicId()).orElseThrow(() -> new EntityNotFoundException("Not found")) : null)
                .choices(3)
                .build();
        teamRepository.save(team);
    }

    public void createUpdateTopic(TopicCreateUpdateRequest request) {
        Topic topic = Topic.builder()
                .id(request.getId() != null ? request.getId() : null)
                .name(request.getName())
                .initial(initialRepository.findByInitial(request.getInitial()))
                .selected(true)
                .build();
        topicRepository.save(topic);
    }

    public void createUpdateUser(RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByName("ROLE_USER"))
                .build();
        userRepository.save(user);
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public TeamInfoByBlocksDto getTeamInfo(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team with id: " + teamId + " not found"));
        return TeamInfoByBlocksDto.builder()
                .team(teamMapper.entity2dto(team))
                .advisor(userMapper.entity2dto(team.getAdvisor()))
                .creator(userMapper.entity2dto(team.getCreator()))
                .topic(topicMapper.entity2dto(team.getTopic()))
                .build();
    }

    public TopicInfoByBlocksDto getTopicInfo(Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new EntityNotFoundException("Topic with id: " + topicId + " not found"));
        return TopicInfoByBlocksDto.builder()
                .topic(topicMapper.entity2dto(topic))
                .creator(userMapper.entity2dto(topic.getCreator()))
                .initial(initialMapper.entity2dto(topic.getInitial()))
                .build();
    }

    public UserInfoByBlocksDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User with id: " + userId + " not found"));
        return UserInfoByBlocksDto.builder()
                .user(userMapper.entity2dto(user))
                .group(groupMapper.entity2dto(user.getGroup()))
                .role(roleMapper.entity2dto(user.getRole()))
                .build();
    }

    public void createUpdateGroup(GroupDto groupDto) {
        Group group = Group.builder()
                .id(groupDto.getId() != null ? groupDto.getId() : null)
                .name(groupDto.getName())
                .initial(initialRepository.findByInitial(groupDto.getInitial()))
                .build();
        groupRepository.save(group);
    }

    public void createUpdateInitial(InitialDto initialDto) {
        Initial initial = Initial.builder()
                .id(initialDto.getId() != null ? initialDto.getId() : null)
                .initial(initialDto.getInitial())
                .build();
        initialRepository.save(initial);
    }

    public GroupInfoByBlocksDto getGroupInfo(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Group with id: " + groupId + " not found"));
        List<UserDto> users = group.getGroupUsers().stream().map(userMapper::entity2dto).collect(Collectors.toList());
        return GroupInfoByBlocksDto.builder()
                .group(groupMapper.entity2dto(group))
                .users(users)
                .initial(initialMapper.entity2dto(group.getInitial()))
                .build();
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public void deleteInitial(Long initialId) {
        initialRepository.deleteById(initialId);
    }

    public InitialInfoByBlocksDto getInitialInfo(Long initialId) {
        Initial initial = initialRepository.findById(initialId).orElseThrow(() -> new EntityNotFoundException("Initial with id: " + initialId + " not found"));
        List<GroupDto> groups = initial.getGroups().stream().map(groupMapper::entity2dto).collect(Collectors.toList());
        return InitialInfoByBlocksDto.builder()
                .initial(initialMapper.entity2dto(initial))
                .groups(groups)
                .build();
    }
}
