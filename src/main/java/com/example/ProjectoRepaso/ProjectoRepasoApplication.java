package com.example.ProjectoRepaso;

import com.example.ProjectoRepaso.Entities.Student;
import com.example.ProjectoRepaso.Repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectoRepasoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectoRepasoApplication.class, args);
	}

   /* @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student maria = new Student("Maria", "Jones", "maria.jones@amigoscode.edu",21);
            studentRepository.save(maria);
        };
    }*/

}
