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
            initialRepository.save(Initial.builder().initial("SE").build());
            groupRepository.save(new Group(null, "1908", initialRepository.findByInitial("SE")));

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Т.")
                    .lastName("Чинибаева")
                    .username("chinibaevat")
                    .role(roleRepository.findByName("ROLE_ADVISOR"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .build());

            topicRepository.save(Topic.builder()
                    .name("Разработка системы для анализа открытых данных с использованием технологии Big Data")
                    .creator(userRepository.findByUsername("chinibaevat").get())
                    .selected(true)
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Даурен")
                    .lastName("Жапаров")
                    .middleName("Маратұлы")
                    .username("zhaparovd")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1908"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Алимжан")
                    .lastName("Исмагамбетов")
                    .middleName("Талгатович")
                    .username("ismagambetova")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1908"))
                    .build());

            userRepository.save(User.builder()
                    .id(null)
                    .firstName("Бақдаулет")
                    .lastName("Қанатбек")
                    .middleName("Бірлікұлы")
                    .username("qanatbekb")
                    .role(roleRepository.findByName("ROLE_STUDENT"))
                    .password(new BCryptPasswordEncoder().encode("verySecret3$"))
                    .group(groupRepository.findByInitialInitialAndName("SE", "1908"))
                    .build());

            teamRepository.save(Team.builder()
                    .name("Жапаров + Исмагамбетов + Қанатбек")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("zhaparovd").get())
                    .advisor(userRepository.findByUsername("chinibaevat").get())
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("zhaparovd").get(), teamRepository.findTeamByName("Жапаров + Исмагамбетов + Қанатбек"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("ismagambetova").get(),  teamRepository.findTeamByName("Жапаров + Исмагамбетов + Қанатбек"), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("qanatbekb").get(),  teamRepository.findTeamByName("Жапаров + Исмагамбетов + Қанатбек"), true));

            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findByName("Разработка системы для анализа открытых данных с использованием технологии Big Data"))
                    .team(teamRepository.findTeamByName("Жапаров + Исмагамбетов + Қанатбек"))
                    .approved(true)
                    .build());

            Team team1 = teamRepository.findTeamByName("Жапаров + Исмагамбетов + Қанатбек");
            team1.setTopic(topicRepository.findByName("Разработка системы для анализа открытых данных с использованием технологии Big Data"));
            teamRepository.save(team1);

            // --------------------//
        };
    }
}
