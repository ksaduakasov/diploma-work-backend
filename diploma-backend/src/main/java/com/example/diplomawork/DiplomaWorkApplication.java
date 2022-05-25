package com.example.diplomawork;

import com.example.diplomawork.model.*;
import com.example.diplomawork.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            roleRepository.save(new Role(null, "ROLE_USER"));
            roleRepository.save(new Role(null, "ROLE_COMMISSION"));
            userRepository.save(new User(null, "Nurahmet", "Azhibek", "Serikzhanovich", "nurahmet@gmail.com", "nurahmet", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));
            userRepository.save(new User(null, "Beibarys", "Zholmyrza", null, "beibarys@gmail.com", "beibarys", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));
            userRepository.save(new User(null, "Kalbek", "Saduakasov", null, "kalbek@gmail.com", "kalbek", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));

            userRepository.save(new User(null, "Bekezhan", "Shamshikenov", null, "bekezhan@gmail.com", "bekezhan", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));
            userRepository.save(new User(null, "Damir", "Nurgaziyev", null, "damir@gmail.com", "damir", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));

            userRepository.save(new User(null, "Admin", "Admin", "Admin", "Admin@gmail.com", "admin", new BCryptPasswordEncoder().encode("admin"), roleRepository.findByName("ROLE_ADMIN")));
            userRepository.save(new User(null, "Assel", "Smayil", "Maralbaevna", "assel.smayil@gmail.com", "asmayil", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_COMMISSION")));
            userRepository.save(new User(null, "Askar", "Khaimuldin", null, "askar@gmail.com", "askar", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_COMMISSION")));
            userRepository.save(new User(null, "Erkebulan", "Kolpakov", null, "erkebulan@gmail.com", "erkebulan", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_COMMISSION")));
            userRepository.save(new User(null, "Tleuzhan", "Mukatayev", null, "tleu13@gmail.com", "tleu13", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_COMMISSION")));

            initialRepository.save(Initial.builder().id(null).initial("SE").build());
            initialRepository.save(Initial.builder().id(null).initial("CS").build());
            initialRepository.save(Initial.builder().id(null).initial("MT").build());
            groupRepository.save(new Group(null, "1903", initialRepository.findByInitial("SE")));

            topicRepository.save(Topic.builder()
                    .name("DIPLOMA PROJECT #1")
                    .creator(userRepository.findByUsername("asmayil").get())
                    .initial(initialRepository.findById(1L).get())
                    .selected(true)
                    .build());
            teamRepository.save(Team.builder()
                    .name("REMMA")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("nurahmet").get())
                    .advisor(userRepository.findByUsername("asmayil").get())
                    .choices(3)
                    .build());

            topicRepository.save(Topic.builder()
                    .name("DIPLOMA PROJECT #2")
                    .creator(userRepository.findByUsername("askar").get())
                    .initial(initialRepository.findById(2L).get())
                    .selected(true)
                    .build());
            teamRepository.save(Team.builder()
                    .name("LIMIT")
                    .confirmed(true)
                    .creator(userRepository.findByUsername("bekezhan").get())
                    .advisor(userRepository.findByUsername("damir").get())
                    .choices(3)
                    .build());

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("nurahmet").get(), teamRepository.findById(1L).get(), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("kalbek").get(), teamRepository.findById(1L).get(), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("beibarys").get(), teamRepository.findById(1L).get(), true));

            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("bekezhan").get(), teamRepository.findById(2L).get(), true));
            userTeamRepository.save(new UserTeam(null, userRepository.findByUsername("damir").get(), teamRepository.findById(2L).get(), true));


            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findById(1L).get())
                    .team(teamRepository.findById(1L).get())
                    .approved(true)
                    .build());
            teamTopicRepository.save(TeamTopic.builder()
                    .topic(topicRepository.findById(2L).get())
                    .team(teamRepository.findById(2L).get())
                    .approved(true)
                    .build());

            stageRepository.save(Stage.builder().name("PRE-DEFENCE#1").build());
            stageRepository.save(Stage.builder().name("PRE-DEFENCE#2").build());
            stageRepository.save(Stage.builder().name("DEFENCE").build());
            Topic topic1 = topicRepository.findById(1L).get();
            Team team1 = teamRepository.findById(1L).get();
            team1.setTopic(topic1);
            teamRepository.save(team1);

            Topic topic2 = topicRepository.findById(2L).get();
            Team team2 = teamRepository.findById(2L).get();
            team2.setTopic(topic2);
            teamRepository.save(team2);
        };
    }
}
