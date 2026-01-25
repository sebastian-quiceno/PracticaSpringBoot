package com.example.ProjectoRepaso.Repositories;

import com.example.ProjectoRepaso.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

//Usa genericos
public interface StudentRepository extends JpaRepository<Student, Long> {

}
