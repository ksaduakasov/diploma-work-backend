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

@SpringBootApplication
public class DiplomaWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiplomaWorkApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner run(RoleRepository roleRepository, TopicRepository topicRepository, UserRepository userRepository, GroupRepository groupRepository, InitialRepository initialRepository, TeamRepository teamRepository) {
        return args -> {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
            roleRepository.save(new Role(null, "ROLE_USER"));
            roleRepository.save(new Role(null, "ROLE_COMMISSION"));
            userRepository.save(new User(null, "Test", "Test", "Test", "test@gmail.com", "test", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_USER")));
            userRepository.save(new User(null, "Assel", "Smayil", "Maralbaevna", "assel.smayil@gmail.com", "asmayil", new BCryptPasswordEncoder().encode("12131213"), roleRepository.findByName("ROLE_COMMISSION")));
            initialRepository.save(new Initial(null, "SE"));
            groupRepository.save(new Group(null, "1903", initialRepository.findByInitial("SE")));
            topicRepository.save(new Topic(null, "Diploma Project Test", userRepository.findByUsername("asmayil").get(), initialRepository.findByInitial("SE"), false));
        };
    }
}
