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

    @Bean
    @Transactional
    CommandLineRunner run(RoleRepository roleRepository, TopicRepository topicRepository, UserRepository userRepository, GroupRepository groupRepository, InitialRepository initialRepository, TeamRepository teamRepository, TeamTopicRepository teamTopicRepository, UserTeamRepository userTeamRepository, StageRepository stageRepository) {
        return args -> {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
            roleRepository.save(new Role(null, "ROLE_STUDENT"));
            roleRepository.save(new Role(null, "ROLE_COMMISSION"));
            roleRepository.save(new Role(null, "ROLE_SECRETARY"));
            roleRepository.save(new Role(null, "ROLE_ADVISOR"));

            // --------------------//
            initialRepository.save(Initial.builder().initial("IT").build());
            initialRepository.save(Initial.builder().initial("SE").build());
            groupRepository.save(new Group(null, "1902", initialRepository.findByInitial("IT")));
            groupRepository.save(new Group(null, "1903", initialRepository.findByInitial("IT")));
            groupRepository.save(new Group(null, "1901", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1903", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1905", initialRepository.findByInitial("SE")));
            groupRepository.save(new Group(null, "1907", initialRepository.findByInitial("SE")));

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Askar")
                    .lastName("Askar")
                    .middleName("Askar")
                    .username("askar")
                    .role(roleRepository.findByName("ROLE_COMMISSION"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

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
                    .firstName("Т.")
                    .lastName("Мукатаев")
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
                    .firstName("Даир")
                    .lastName("Алаев")
                    .middleName("Дамирович")
                    .username("alayevd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Исмаил")
                    .lastName("Ибрагим")
                    .middleName("Ибрагимұлы")
                    .username("ibragimi")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Әбдуали")
                    .lastName("Ілияс")
                    .middleName("Бірлікұлы")
                    .username("iliasa")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Алаев + Ибрагим + Ілияс")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("alayevd").get())
                    .advisor(userRepository.findByUsername("tleu13").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alayevd").get(), teamRepository.findTeamByName("Алаев + Ибрагим + Ілияс"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("ibragimi").get(),  teamRepository.findTeamByName("Алаев + Ибрагим + Ілияс"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("iliasa").get(),  teamRepository.findTeamByName("Алаев + Ибрагим + Ілияс"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Implementation of Single Sign-On microservice for AUTH"))
                    .team(teamRepository.findTeamByName("Алаев + Ибрагим + Ілияс"))
                    .approved(true)
                    .build());

            Team team1 = teamRepository.findTeamByName("Алаев + Ибрагим + Ілияс");
            team1.setTopic(topicRepository.findByName("Implementation of Single Sign-On microservice for AUTH"));
            teamRepository.save(team1);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("А.")
                    .lastName("Адамова")
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
                    .firstName("Аслан")
                    .lastName("Алшанов")
                    .middleName("Бауыржанулы")
                    .username("alshanova")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1902"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Диас")
                    .lastName("Жаканов")
                    .middleName("Максатұлы")
                    .username("zhakanovd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1902"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Алшанов + Жаканов")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("alshanova").get())
                    .advisor(userRepository.findByUsername("adamovaa").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alshanova").get(), teamRepository.findTeamByName("Алшанов + Жаканов"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhakanovd").get(),  teamRepository.findTeamByName("Алшанов + Жаканов"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of a mobile application to analyze data from open sources"))
                    .team(teamRepository.findTeamByName("Алшанов + Жаканов"))
                    .approved(true)
                    .build());

            Team team2 = teamRepository.findTeamByName("Алшанов + Жаканов");
            team2.setTopic(topicRepository.findByName("Development of a mobile application to analyze data from open sources"));
            teamRepository.save(team2);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("С.")
                    .lastName("Аубакиров")
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
                    .firstName("Үміт")
                    .lastName("Құмарова")
                    .middleName("Қайратқызы")
                    .username("kumarovau")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("IT", "1903"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Құмарова")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("kumarovau").get())
                    .advisor(userRepository.findByUsername("aubakirovs").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kumarovau").get(), teamRepository.findTeamByName("Құмарова"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Distributed load testing for http traffic simulation in production"))
                    .team(teamRepository.findTeamByName("Құмарова"))
                    .approved(true)
                    .build());

            Team team3 = teamRepository.findTeamByName("Құмарова");
            team3.setTopic(topicRepository.findByName("Distributed load testing for http traffic simulation in production"));
            teamRepository.save(team3);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("А.")
                    .lastName("Турсынкулова")
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
                    .firstName("Дәрия")
                    .lastName("Бигабулова")
                    .middleName("Нұрланқызы")
                    .username("bigabulovad")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Эльдар")
                    .lastName("Сайрамбай")
                    .middleName("Бауржанұлы")
                    .username("sairambaye")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1907"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Бигабулова + Сайрамбай")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("bigabulovad").get())
                    .advisor(userRepository.findByUsername("tursynkulovaa").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("bigabulovad").get(), teamRepository.findTeamByName("Бигабулова + Сайрамбай"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sairambaye").get(),  teamRepository.findTeamByName("Бигабулова + Сайрамбай"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Web-application for automating business processes of computer service centersH"))
                    .team(teamRepository.findTeamByName("Бигабулова + Сайрамбай"))
                    .approved(true)
                    .build());

            Team team4 = teamRepository.findTeamByName("Бигабулова + Сайрамбай");
            team4.setTopic(topicRepository.findByName("Web-application for automating business processes of computer service centers"));
            teamRepository.save(team4);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Н.")
                    .lastName("Асанова")
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
                    .firstName("Алимжан")
                    .lastName("Имангазин")
                    .middleName("Ерболович")
                    .username("imangazina")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Мағауия")
                    .lastName("Мәжит")
                    .middleName("Ғабитұлы")
                    .username("mazhitm")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Рахат")
                    .lastName("Төлеу")
                    .middleName("Ризабекұлы")
                    .username("toleur")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Имангазин + Мәжит + Төлеу")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("imangazina").get())
                    .advisor(userRepository.findByUsername("assanovan").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("imangazina").get(), teamRepository.findTeamByName("Имангазин + Мәжит + Төлеу"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("mazhitm").get(),  teamRepository.findTeamByName("Имангазин + Мәжит + Төлеу"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("toleur").get(),  teamRepository.findTeamByName("Имангазин + Мәжит + Төлеу"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of a volunteer platform"))
                    .team(teamRepository.findTeamByName("Имангазин + Мәжит + Төлеу"))
                    .approved(true)
                    .build());

            Team team5 = teamRepository.findTeamByName("Имангазин + Мәжит + Төлеу");
            team5.setTopic(topicRepository.findByName("Development of a volunteer platform"));
            teamRepository.save(team5);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Н.")
                    .lastName("Хаймульдин")
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
                    .firstName("Жансая")
                    .lastName("Жайланова")
                    .middleName("Айдынкызы")
                    .username("zhailanovaz")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Томирис")
                    .lastName("Саят")
                    .middleName("")
                    .username("sayatt")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Тамина")
                    .lastName("Темірхан")
                    .middleName("Ержанқызы")
                    .username("temirkhant")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Жайланова + Саят + Темірхан")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("temirkhant").get())
                    .advisor(userRepository.findByUsername("khaimuldinn").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhailanovaz").get(), teamRepository.findTeamByName("Жайланова + Саят + Темірхан"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sayatt").get(),  teamRepository.findTeamByName("Жайланова + Саят + Темірхан"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("temirkhant").get(),  teamRepository.findTeamByName("Жайланова + Саят + Темірхан"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of mobile application 'Service for subscription and delivery of ZOO-goods'"))
                    .team(teamRepository.findTeamByName("Жайланова + Саят + Темірхан"))
                    .approved(true)
                    .build());

            Team team6 = teamRepository.findTeamByName("Жайланова + Саят + Темірхан");
            team6.setTopic(topicRepository.findByName("Development of mobile application 'Service for subscription and delivery of ZOO-goods'"));
            teamRepository.save(team6);

            // --------------------//

            //defence day 2 -> 02.06.2022

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Д.")
                    .lastName("Лебедев")
                    .username("lebedevd")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University")
                    .creator(userRepository.findByUsername("lebedevd").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Ерасыл")
                    .lastName("Болат")
                    .middleName("Жұмағазыұлы")
                    .username("bolate")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1902"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Анияр")
                    .lastName("Калиев")
                    .middleName("Ануарбекович")
                    .username("kaliyeva")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Айбын")
                    .lastName("Сағындық")
                    .middleName("Бекенұлы")
                    .username("sagyndyka")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Ален")
                    .lastName("Туякбаев")
                    .middleName("Сакенович")
                    .username("tuyakbayeva")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1902"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Болат + Калиев + Сағындық + Туякбаев")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("tuyakbayeva").get())
                    .advisor(userRepository.findByUsername("lebedevd").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("bolate").get(), teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kaliyeva").get(),  teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("sagyndyka").get(),  teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("tuyakbayeva").get(),  teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University"))
                    .team(teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев"))
                    .approved(true)
                    .build());

            Team team7 = teamRepository.findTeamByName("Болат + Калиев + Сағындық + Туякбаев");
            team7.setTopic(topicRepository.findByName("Development of a module for the Competitive Contract Commission for domestic and foreign teaching staff for Astana IT University"));
            teamRepository.save(team7);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("A.")
                    .lastName("Хаймульдин")
                    .username("khaimuldina")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of data analyzer system to find available job positions from open source")
                    .creator(userRepository.findByUsername("khaimuldina").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Азатқали")
                    .lastName("Нұрұмғалиев")
                    .middleName("Берикқалиұлы")
                    .username("nurumgaliyeva")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Нұрым")
                    .lastName("Сиырбаев")
                    .middleName("Айзатұлы")
                    .username("siyrbayevn")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Мадияр")
                    .lastName("Усабеков")
                    .middleName("Нурланович")
                    .username("ussabekovm")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Нұрұмғалиев + Сиырбаев + Усабеков")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("ussabekovm").get())
                    .advisor(userRepository.findByUsername("khaimuldina").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("nurumgaliyeva").get(), teamRepository.findTeamByName("Нұрұмғалиев + Сиырбаев + Усабеков"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("siyrbayevn").get(),  teamRepository.findTeamByName("Нұрұмғалиев + Сиырбаев + Усабеков"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("ussabekovm").get(),  teamRepository.findTeamByName("Нұрұмғалиев + Сиырбаев + Усабеков"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of data analyzer system to find available job positions from open source"))
                    .team(teamRepository.findTeamByName("Нұрұмғалиев + Сиырбаев + Усабеков"))
                    .approved(true)
                    .build());

            Team team8 = teamRepository.findTeamByName("Нұрұмғалиев + Сиырбаев + Усабеков");
            team8.setTopic(topicRepository.findByName("Development of data analyzer system to find available job positions from open source"));
            teamRepository.save(team8);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Ж.")
                    .lastName("Сарсенова")
                    .username("sarsenovaz")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of system for translating manga and comics with automatic identification and replacement of text")
                    .creator(userRepository.findByUsername("sarsenovaz").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Динмухаммед")
                    .lastName("Еримбетов")
                    .middleName("Ермекулы")
                    .username("erimbetovd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Мағжан")
                    .lastName("Жақуда")
                    .middleName("Ардақұлы")
                    .username("zhakudam")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Рахат")
                    .lastName("Шаяхметов")
                    .middleName("Серикулы")
                    .username("shayahmetovr")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1906"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Еримбетов + Жақуда + Шаяхметов")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("shayahmetovr").get())
                    .advisor(userRepository.findByUsername("sarsenovaz").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("erimbetovd").get(), teamRepository.findTeamByName("Еримбетов + Жақуда + Шаяхметов"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhakudam").get(),  teamRepository.findTeamByName("Еримбетов + Жақуда + Шаяхметов"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("shayahmetovr").get(),  teamRepository.findTeamByName("Еримбетов + Жақуда + Шаяхметов"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of system for translating manga and comics with automatic identification and replacement of text"))
                    .team(teamRepository.findTeamByName("Еримбетов + Жақуда + Шаяхметов"))
                    .approved(true)
                    .build());

            Team team9 = teamRepository.findTeamByName("Еримбетов + Жақуда + Шаяхметов");
            team9.setTopic(topicRepository.findByName("Development of system for translating manga and comics with automatic identification and replacement of text"));
            teamRepository.save(team9);

            //------------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("С.")
                    .lastName("Аубакиров")
                    .username("aubakirovs")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of MDM system for restaurants")
                    .creator(userRepository.findByUsername("aubakirovs").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Адильхан")
                    .lastName("Кенжетаев")
                    .middleName("Сырымбетович")
                    .username("kenzhetayeva")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1906"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Ақылбек")
                    .lastName("Қонысқайров")
                    .middleName("Ерланұлы")
                    .username("konyskaiyrova")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1905"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Асфандияр")
                    .lastName("Марат")
                    .middleName("Русланулы")
                    .username("marata")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1904"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Кенжетаев + Қонысқайров + Марат")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("marata").get())
                    .advisor(userRepository.findByUsername("aubakirovs").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kenzhetayeva").get(), teamRepository.findTeamByName("Кенжетаев + Қонысқайров + Марат"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("konyskaiyrova").get(),  teamRepository.findTeamByName("Кенжетаев + Қонысқайров + Марат"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("marata").get(),  teamRepository.findTeamByName("Кенжетаев + Қонысқайров + Марат"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of MDM system for restaurants"))
                    .team(teamRepository.findTeamByName("Кенжетаев + Қонысқайров + Марат"))
                    .approved(true)
                    .build());

            Team team10 = teamRepository.findTeamByName("Кенжетаев + Қонысқайров + Марат");
            team10.setTopic(topicRepository.findByName("Development of MDM system for restaurants"));
            teamRepository.save(team10);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("T.")
                    .lastName("Мукатаев")
                    .username("tleu13")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Implementation of blokchain in Go")
                    .creator(userRepository.findByUsername("tleu13").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Мирас")
                    .lastName("Алимов")
                    .middleName("Сакенович")
                    .username("alimovm")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Амиран")
                    .lastName("Құрман")
                    .middleName("Мәлікұлы")
                    .username("kurmana")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Бекнұр")
                    .lastName("Раисов")
                    .middleName("Кенжебекұлы")
                    .username("raissovb")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1901"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Алимов + Құрман + Раисов")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("raissovb").get())
                    .advisor(userRepository.findByUsername("tleu13").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("alimovm").get(), teamRepository.findTeamByName("Алимов + Құрман + Раисов"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kurmana").get(),  teamRepository.findTeamByName("Алимов + Құрман + Раисов"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("raissovb").get(),  teamRepository.findTeamByName("Алимов + Құрман + Раисов"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Implementation of blokchain in Go"))
                    .team(teamRepository.findTeamByName("Алимов + Құрман + Раисов"))
                    .approved(true)
                    .build());

            Team team11 = teamRepository.findTeamByName("Алимов + Құрман + Раисов");
            team11.setTopic(topicRepository.findByName("Implementation of blokchain in Go"));
            teamRepository.save(team11);

            // --------------------//

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Д.")
                    .lastName("Лебедев")
                    .username("levedevd")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Development of the “Digital University” information system using microservice architecture")
                    .creator(userRepository.findByUsername("levedevd").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Алихан")
                    .lastName("Жомартов")
                    .middleName("Мадиұлы")
                    .username("zhomartova")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1903"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Жомартов")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("zhomartova").get())
                    .advisor(userRepository.findByUsername("levedevd").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhomartova").get(), teamRepository.findTeamByName("Жомартов"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Development of the “Digital University” information system using microservice architecture"))
                    .team(teamRepository.findTeamByName("Жомартов"))
                    .approved(true)
                    .build());

            Team team12 = teamRepository.findTeamByName("Жомартов");
            team12.setTopic(topicRepository.findByName("Development of the “Digital University” information system using microservice architecture"));
            teamRepository.save(team12);

            // --------------------//
        };
    }
}
