package com.mehmetpekdemir.jwt;

import com.mehmetpekdemir.jwt.entity.RoleEntity;
import com.mehmetpekdemir.jwt.entity.UserEntity;
import com.mehmetpekdemir.jwt.repository.RoleRepository;
import com.mehmetpekdemir.jwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    CommandLineRunner createInitialUsers(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            //Role User
            RoleEntity roleUser = new RoleEntity();
            roleUser.setName("USER");
            roleRepository.save(roleUser);

            //Role Admin
            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setName("ADMIN");
            roleRepository.save(roleAdmin);

            //User 1
            UserEntity user = new UserEntity();
            user.setUsername("Mehmet1");
            user.setPassword(passwordEncoder.encode("Mehmet1"));
            user.setRole(roleAdmin);
            userRepository.save(user);

            //User 2
            UserEntity user2 = new UserEntity();
            user2.setUsername("Mehmet2");
            user2.setPassword(passwordEncoder.encode("Mehmet2"));
            user2.setRole(roleUser);

            userRepository.save(user2);

        };
    }

}
