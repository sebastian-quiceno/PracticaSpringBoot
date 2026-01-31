package com.example.ProjectoRepaso.Controllers;

import com.example.ProjectoRepaso.Entities.Student;
import com.example.ProjectoRepaso.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    //Don’t call us, we’ll call you (IoC)
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/estudiantes")
    public List<Student> getStudents(){

        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudentById(@PathVariable("studentId") Long id){

        studentService.deleteStudentById(id);

    }


    //@RequestParam sirve para leer parámetros que vienen en la URL, normalmente después del ?.
    //PUT /students/1?name=Sebas&email=sebas@email.com
    @PutMapping(path = "{studentId}")
    public void updateStudent( @PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }

}
