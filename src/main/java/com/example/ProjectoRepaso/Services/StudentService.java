package com.example.ProjectoRepaso.Services;

import com.example.ProjectoRepaso.Entities.Student;
import com.example.ProjectoRepaso.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


//Hay que especificarle a spring boot cuales son los cocmponentes, para que el pueda hacer la inyeccoin de dependencias
//Esto se puede hacer con la etiqueta @Component, pero se puede usar otras que cumplen la misma funcion, pero con nombres mas caracteristicos
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }


    public void addNewStudent(Student student){

        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()){
            throw new IllegalStateException("El Email ya esta en uso");
        }
        studentRepository.save(student);

        System.out.println(student);
    }

    public void deleteStudentById(long id){
        boolean existe = studentRepository.existsById(id);

        if(!existe){
            throw new IllegalStateException("El estudiante con el id: "+id+" no existe");
        }

        studentRepository.deleteById(id);
    }

    //Esta etiqueta sirve para asegurar que:
    //si todo sale bien → COMMIT
    //si algo falla → ROLLBACK
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("El estudiante con el id: "+studentId+" no existe"));

        if(name != null && !name.isEmpty() && !Objects.equals(student.getFirstName(), name)){
            student.setFirstName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            //Comprobacion de correo
            if(studentOptional.isPresent()){
                throw new IllegalStateException("El Email ya esta en uso");
            }
            student.setEmail(email);

        }

    }

}
