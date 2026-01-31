package com.example.ProjectoRepaso;

import com.example.ProjectoRepaso.Entities.Student;
import com.example.ProjectoRepaso.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    "Mariam",
                    "james",
                    LocalDate.of(2000, 6, 24),
                    "mariam.jamal@gmail.com"
            );

            Student alex = new Student(
                    "Alex",
                    "Anter",
                    LocalDate.of(2004, 6, 24),
                    "alex@gmail.com"
            );

            repository.saveAll(
                    List.of(mariam,alex)
            );
        };
    }
}
