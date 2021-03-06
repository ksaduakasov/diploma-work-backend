package com.example.diplomawork;

import com.example.diplomawork.model.*;
import com.example.diplomawork.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class DiplomaWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaWorkApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

    @Bean
    @Transactional
    CommandLineRunner run(ReviewerRepository reviewerRepository, RoleRepository roleRepository, TopicRepository topicRepository, UserRepository userRepository, GroupRepository groupRepository, InitialRepository initialRepository, TeamRepository teamRepository, TeamTopicRepository teamTopicRepository, UserTeamRepository userTeamRepository, StageRepository stageRepository) {
        return args -> {

            reviewerRepository.save(Reviewer.builder().id(null).fullName("???????????? ?????????????????? ????????????????????").careerGrade("PhD candidate").workPlace("IITU").profession("Senior-lecturer, Information Systems Department").build());
            reviewerRepository.save(Reviewer.builder().id(null).fullName("???????????????? ?????????????? ??????????????????").careerGrade("PHD").workPlace("?????????? ???? ??????-????????????, ?????????????????? ???????????????????????????? ????????????????????").profession("???????????????????? ?????????????? ??????????????????????").build());
            reviewerRepository.save(Reviewer.builder().id(null).fullName("???????????????????? ???????????? ????????????????????????").careerGrade("???????????? phd").workPlace("?????? ?????????? ??.??.????????????????").profession("??.??. ?????????????? ?????????????? ?????????????????????????? ?? ?????????????????????? ????????????????????").build());
            reviewerRepository.save(Reviewer.builder().id(null).fullName("?????????????? ???????????? ??????????????????????").careerGrade("?????????????? ???????????????????????? ????????").workPlace("?????? ???? ??.??.????????????????").profession("??????????????????????????").build());
            reviewerRepository.save(Reviewer.builder().id(null).fullName("?????????? ???????????? ????????????????????").careerGrade("MSc").workPlace("IITU").profession("????????????-???????????? ?????????????? ??????????????????????????????????").build());

            roleRepository.save(new Role(null, "ROLE_ADMIN"));
            roleRepository.save(new Role(null, "ROLE_STUDENT"));
            roleRepository.save(new Role(null, "ROLE_COMMISSION"));
            roleRepository.save(new Role(null, "ROLE_SECRETARY"));
            roleRepository.save(new Role(null, "ROLE_ADVISOR"));

            // --------------------//
            initialRepository.save(Initial.builder().initial("IT").build());
            initialRepository.save(Initial.builder().initial("SE").build());
            groupRepository.save(new Group(null, "1902", initialRepository.findByInitial("IT")));
            groupRepository.save(new Group(null, "IT-1903", initialRepository.findByInitial("IT")));
            groupRepository.save(new Group(null, "1901", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1903", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1905", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1907", initialRepository.findByInitial("SE")));

            stageRepository.save(Stage.builder().name("DEFENCE").build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("admin")
                    .lastName("admin")
                    .middleName(null)
                    .username("admin")
                    .role(roleRepository.findByName("ROLE_ADMIN"))
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????")
                    .middleName(null)
                    .username("edilkhan")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????")
                    .middleName(null)
                    .username("adamova")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????????")
                    .middleName(null)
                    .username("aubakirov")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????????")
                    .middleName(null)
                    .username("ayabekova")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????")
                    .middleName(null)
                    .username("smaiyl")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????????")
                    .middleName(null)
                    .username("kumalakov")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????????????")
                    .middleName(null)
                    .username("rakhimzhanov")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("Askar")
//                    .lastName("Askar")
//                    .middleName("Askar")
//                    .username("askar")
//                    .role(roleRepository.findByName("ROLE_COMMISSION"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Secretar")
                    .lastName("Secretar")
                    .middleName("Secretar")
                    .username("sec")
                    .role(roleRepository.findByName("ROLE_SECRETARY"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????????")
                    .username("tleu13")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Implementation of Single Sign-On microservice for AUTH")
                    .creator(userRepository.findByUsername("tleu13").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????")
                    .lastName("??????????")
                    .middleName("??????????????????")
                    .username("alayevd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????????")
                    .lastName("??????????????")
                    .middleName("????????????????????")
                    .username("ibragimi")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????????")
                    .lastName("??????????")
                    .middleName("??????????????????")
                    .username("iliasa")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("?????????? + ?????????????? + ??????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("alayevd").get())
                    .advisor(userRepository.findByUsername("tleu13").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alayevd").get(), teamRepository.findTeamByName("?????????? + ?????????????? + ??????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("ibragimi").get(),  teamRepository.findTeamByName("?????????? + ?????????????? + ??????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("iliasa").get(),  teamRepository.findTeamByName("?????????? + ?????????????? + ??????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Implementation of Single Sign-On microservice for AUTH"))
                    .team(teamRepository.findTeamByName("?????????? + ?????????????? + ??????????"))
                    .approved(true)
                    .build());

            Team team1 = teamRepository.findTeamByName("?????????? + ?????????????? + ??????????");
            team1.setReviewer(reviewerRepository.findByFullName("???????????? ?????????????????? ????????????????????"));
            team1.setTopic(topicRepository.findByName("Implementation of Single Sign-On microservice for AUTH"));
            teamRepository.save(team1);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????")
                    .username("adamovaa")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of a mobile application to analyze data from open sources")
                    .creator(userRepository.findByUsername("adamovaa").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????")
                    .lastName("??????????????")
                    .middleName("??????????????????????")
                    .username("alshanova")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1902"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????")
                    .lastName("??????????????")
                    .middleName("??????????????????")
                    .username("zhakanovd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1902"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("?????????????? + ??????????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("alshanova").get())
                    .advisor(userRepository.findByUsername("adamovaa").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alshanova").get(), teamRepository.findTeamByName("?????????????? + ??????????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhakanovd").get(),  teamRepository.findTeamByName("?????????????? + ??????????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of a mobile application to analyze data from open sources"))
                    .team(teamRepository.findTeamByName("?????????????? + ??????????????"))
                    .approved(true)
                    .build());

            Team team2 = teamRepository.findTeamByName("?????????????? + ??????????????");
            team2.setReviewer(reviewerRepository.findById(2L).get());
            team2.setTopic(topicRepository.findByName("Development of a mobile application to analyze data from open sources"));
            teamRepository.save(team2);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????????")
                    .username("aubakirovs")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Distributed load testing for http traffic simulation in production")
                    .creator(userRepository.findByUsername("aubakirovs").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????")
                    .lastName("????????????????")
                    .middleName("????????????????????")
                    .username("kumarovau")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1903"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("????????????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("kumarovau").get())
                    .advisor(userRepository.findByUsername("aubakirovs").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kumarovau").get(), teamRepository.findTeamByName("????????????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Distributed load testing for http traffic simulation in production"))
                    .team(teamRepository.findTeamByName("????????????????"))
                    .approved(true)
                    .build());

            Team team3 = teamRepository.findTeamByName("????????????????");
            team3.setReviewer(reviewerRepository.findById(3L).get());
            team3.setTopic(topicRepository.findByName("Distributed load testing for http traffic simulation in production"));
            teamRepository.save(team3);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????????????????")
                    .username("tursynkulovaa")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Web-application for automating business processes of computer service centers")
                    .creator(userRepository.findByUsername("tursynkulovaa").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????")
                    .lastName("????????????????????")
                    .middleName("????????????????????")
                    .username("bigabulovad")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????????")
                    .lastName("??????????????????")
                    .middleName("????????????????????")
                    .username("sairambaye")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("???????????????????? + ??????????????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("bigabulovad").get())
                    .advisor(userRepository.findByUsername("tursynkulovaa").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("bigabulovad").get(), teamRepository.findTeamByName("???????????????????? + ??????????????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sairambaye").get(),  teamRepository.findTeamByName("???????????????????? + ??????????????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Web-application for automating business processes of computer service centers"))
                    .team(teamRepository.findTeamByName("???????????????????? + ??????????????????"))
                    .approved(true)
                    .build());

            Team team4 = teamRepository.findTeamByName("???????????????????? + ??????????????????");
            team4.setReviewer(reviewerRepository.findById(4L).get());
            team4.setTopic(topicRepository.findByName("Web-application for automating business processes of computer service centers"));
            teamRepository.save(team4);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("??????????????")
                    .username("assanovan")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of a volunteer platform")
                    .creator(userRepository.findByUsername("assanovan").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????????")
                    .lastName("??????????????????")
                    .middleName("??????????????????")
                    .username("imangazina")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????????")
                    .lastName("??????????")
                    .middleName("????????????????")
                    .username("mazhitm")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????")
                    .lastName("??????????")
                    .middleName("????????????????????")
                    .username("toleur")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("?????????????????? + ?????????? + ??????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("imangazina").get())
                    .advisor(userRepository.findByUsername("assanovan").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("imangazina").get(), teamRepository.findTeamByName("?????????????????? + ?????????? + ??????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("mazhitm").get(),  teamRepository.findTeamByName("?????????????????? + ?????????? + ??????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("toleur").get(),  teamRepository.findTeamByName("?????????????????? + ?????????? + ??????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of a volunteer platform"))
                    .team(teamRepository.findTeamByName("?????????????????? + ?????????? + ??????????"))
                    .approved(true)
                    .build());

            Team team5 = teamRepository.findTeamByName("?????????????????? + ?????????? + ??????????");
            team5.setReviewer(reviewerRepository.findById(1L).get());
            team5.setTopic(topicRepository.findByName("Development of a volunteer platform"));
            teamRepository.save(team5);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??.")
                    .lastName("????????????????????")
                    .username("khaimuldinn")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of mobile application 'Service for subscription and delivery of ZOO-goods'")
                    .creator(userRepository.findByUsername("khaimuldinn").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????????")
                    .lastName("??????????????????")
                    .middleName("??????????????????")
                    .username("zhailanovaz")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("??????????????")
                    .lastName("????????")
                    .middleName("")
                    .username("sayatt")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("????????????")
                    .lastName("????????????????")
                    .middleName("??????????????????")
                    .username("temirkhant")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("?????????????????? + ???????? + ????????????????")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("temirkhant").get())
                    .advisor(userRepository.findByUsername("khaimuldinn").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhailanovaz").get(), teamRepository.findTeamByName("?????????????????? + ???????? + ????????????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sayatt").get(),  teamRepository.findTeamByName("?????????????????? + ???????? + ????????????????"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("temirkhant").get(),  teamRepository.findTeamByName("?????????????????? + ???????? + ????????????????"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of mobile application 'Service for subscription and delivery of ZOO-goods'"))
                    .team(teamRepository.findTeamByName("?????????????????? + ???????? + ????????????????"))
                    .approved(true)
                    .build());

            Team team6 = teamRepository.findTeamByName("?????????????????? + ???????? + ????????????????");
            team6.setReviewer(reviewerRepository.findById(5L).get());
            team6.setTopic(topicRepository.findByName("Development of mobile application 'Service for subscription and delivery of ZOO-goods'"));
            teamRepository.save(team6);

            // --------------------//

            //defence day 2 -> 02.06.2022
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??.")
//                    .lastName("??????????????")
//                    .username("lebedevd")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University")
//                    .creator(userRepository.findByUsername("lebedevd").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("??????????")
//                    .middleName("??????????????????????")
//                    .username("bolate")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1902"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????")
//                    .lastName("????????????")
//                    .middleName("????????????????????????")
//                    .username("kaliyeva")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????")
//                    .lastName("????????????????")
//                    .middleName("????????????????")
//                    .username("sagyndyka")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????")
//                    .lastName("????????????????")
//                    .middleName("??????????????????")
//                    .username("tuyakbayeva")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1902"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("?????????? + ???????????? + ???????????????? + ????????????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("tuyakbayeva").get())
//                    .advisor(userRepository.findByUsername("lebedevd").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("bolate").get(), teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kaliyeva").get(),  teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sagyndyka").get(),  teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("tuyakbayeva").get(),  teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University"))
//                    .team(teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????"))
//                    .approved(true)
//                    .build());
//
//            Team team7 = teamRepository.findTeamByName("?????????? + ???????????? + ???????????????? + ????????????????");
//            team7.setTopic(topicRepository.findByName("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University"));
//            teamRepository.save(team7);
//
//            // --------------------//
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("A.")
//                    .lastName("????????????????????")
//                    .username("khaimuldina")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Development of data analyzer system to find available job positions from open source")
//                    .creator(userRepository.findByUsername("khaimuldina").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????????")
//                    .lastName("??????????????????????")
//                    .middleName("????????????????????????")
//                    .username("nurumgaliyeva")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????")
//                    .lastName("????????????????")
//                    .middleName("????????????????")
//                    .username("siyrbayevn")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("????????????????")
//                    .middleName("????????????????????")
//                    .username("ussabekovm")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("?????????????????????? + ???????????????? + ????????????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("ussabekovm").get())
//                    .advisor(userRepository.findByUsername("khaimuldina").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("nurumgaliyeva").get(), teamRepository.findTeamByName("?????????????????????? + ???????????????? + ????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("siyrbayevn").get(),  teamRepository.findTeamByName("?????????????????????? + ???????????????? + ????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("ussabekovm").get(),  teamRepository.findTeamByName("?????????????????????? + ???????????????? + ????????????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Development of data analyzer system to find available job positions from open source"))
//                    .team(teamRepository.findTeamByName("?????????????????????? + ???????????????? + ????????????????"))
//                    .approved(true)
//                    .build());
//
//            Team team8 = teamRepository.findTeamByName("?????????????????????? + ???????????????? + ????????????????");
//            team8.setTopic(topicRepository.findByName("Development of data analyzer system to find available job positions from open source"));
//            teamRepository.save(team8);
//
//            // --------------------//
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??.")
//                    .lastName("??????????????????")
//                    .username("sarsenovaz")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Development of system for translating manga and comics with automatic identification and replacement of text")
//                    .creator(userRepository.findByUsername("sarsenovaz").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????????????????")
//                    .lastName("??????????????????")
//                    .middleName("????????????????")
//                    .username("erimbetovd")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("????????????")
//                    .middleName("????????????????")
//                    .username("zhakudam")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????")
//                    .lastName("??????????????????")
//                    .middleName("????????????????")
//                    .username("shayahmetovr")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1906"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("?????????????????? + ???????????? + ??????????????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("shayahmetovr").get())
//                    .advisor(userRepository.findByUsername("sarsenovaz").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("erimbetovd").get(), teamRepository.findTeamByName("?????????????????? + ???????????? + ??????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhakudam").get(),  teamRepository.findTeamByName("?????????????????? + ???????????? + ??????????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("shayahmetovr").get(),  teamRepository.findTeamByName("?????????????????? + ???????????? + ??????????????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Development of system for translating manga and comics with automatic identification and replacement of text"))
//                    .team(teamRepository.findTeamByName("?????????????????? + ???????????? + ??????????????????"))
//                    .approved(true)
//                    .build());
//
//            Team team9 = teamRepository.findTeamByName("?????????????????? + ???????????? + ??????????????????");
//            team9.setTopic(topicRepository.findByName("Development of system for translating manga and comics with automatic identification and replacement of text"));
//            teamRepository.save(team9);
//
//            //------------------------//
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??.")
//                    .lastName("??????????????????")
//                    .username("aubakirovs")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Development of MDM system for restaurants")
//                    .creator(userRepository.findByUsername("aubakirovs").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????????")
//                    .lastName("??????????????????")
//                    .middleName("????????????????????????")
//                    .username("kenzhetayeva")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1906"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????????")
//                    .lastName("??????????????????????")
//                    .middleName("????????????????")
//                    .username("konyskaiyrova")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????????????")
//                    .lastName("??????????")
//                    .middleName("??????????????????")
//                    .username("marata")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("?????????????????? + ?????????????????????? + ??????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("marata").get())
//                    .advisor(userRepository.findByUsername("aubakirovs").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kenzhetayeva").get(), teamRepository.findTeamByName("?????????????????? + ?????????????????????? + ??????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("konyskaiyrova").get(),  teamRepository.findTeamByName("?????????????????? + ?????????????????????? + ??????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("marata").get(),  teamRepository.findTeamByName("?????????????????? + ?????????????????????? + ??????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Development of MDM system for restaurants"))
//                    .team(teamRepository.findTeamByName("?????????????????? + ?????????????????????? + ??????????"))
//                    .approved(true)
//                    .build());
//
//            Team team10 = teamRepository.findTeamByName("?????????????????? + ?????????????????????? + ??????????");
//            team10.setTopic(topicRepository.findByName("Development of MDM system for restaurants"));
//            teamRepository.save(team10);
//
//            // --------------------//
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("T.")
//                    .lastName("????????????????")
//                    .username("tleu13")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Implementation of blokchain in Go")
//                    .creator(userRepository.findByUsername("tleu13").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??????????")
//                    .lastName("????????????")
//                    .middleName("??????????????????")
//                    .username("alimovm")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("????????????")
//                    .middleName("????????????????")
//                    .username("kurmana")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("????????????")
//                    .middleName("??????????????????????")
//                    .username("raissovb")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("???????????? + ???????????? + ????????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("raissovb").get())
//                    .advisor(userRepository.findByUsername("tleu13").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alimovm").get(), teamRepository.findTeamByName("???????????? + ???????????? + ????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kurmana").get(),  teamRepository.findTeamByName("???????????? + ???????????? + ????????????"), true));
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("raissovb").get(),  teamRepository.findTeamByName("???????????? + ???????????? + ????????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Implementation of blokchain in Go"))
//                    .team(teamRepository.findTeamByName("???????????? + ???????????? + ????????????"))
//                    .approved(true)
//                    .build());
//
//            Team team11 = teamRepository.findTeamByName("???????????? + ???????????? + ????????????");
//            team11.setTopic(topicRepository.findByName("Implementation of blokchain in Go"));
//            teamRepository.save(team11);
//
//            // --------------------//
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("??.")
//                    .lastName("??????????????")
//                    .username("levedevd")
//                    .role(roleRepository.findByName("ROLE_ADVISOR"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .build());
//
//            topicRepository.save(Topic.builder()
//                    .name("Development of the ???Digital University??? information system using microservice architecture")
//                    .creator(userRepository.findByUsername("levedevd").get())
//                    .selected(true)
//                    .build());
//
//            userRepository.save(User.builder()
//                    .id(null)
//                    .firstName("????????????")
//                    .lastName("????????????????")
//                    .middleName("??????????????")
//                    .username("zhomartova")
//                    .role(roleRepository.findByName("ROLE_STUDENT"))
//                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
//                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
//                    .build());
//
//            teamRepository.save(Team.builder()
//                    .name("????????????????")
//                    .confirmed(true)
//                    .creator(userRepository.findByUsername("zhomartova").get())
//                    .advisor(userRepository.findByUsername("levedevd").get())
//                    .build());
//
//            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhomartova").get(), teamRepository.findTeamByName("????????????????"), true));
//
//            teamTopicRepository.save(TeamTopic.builder()
//                    .topic(topicRepository.findByName("Development of the ???Digital University??? information system using microservice architecture"))
//                    .team(teamRepository.findTeamByName("????????????????"))
//                    .approved(true)
//                    .build());
//
//            Team team12 = teamRepository.findTeamByName("????????????????");
//            team12.setTopic(topicRepository.findByName("Development of the ???Digital University??? information system using microservice architecture"));
//            teamRepository.save(team12);

            // --------------------//
        };
    }
}
