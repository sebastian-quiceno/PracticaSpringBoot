package com.example.ProjectoRepaso.Repositories;

import com.example.ProjectoRepaso.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Usa genericos, el primero es el tipo con el que se quiere trabajar, y el otro es el id del tipo que se usa
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //Esto va a transformar el codigo en un query a la base de datos:
    //SELECT * FROM student WHERE email = ?

//    @Query("SELECT s FROM student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
