package com.example.ProjectoRepaso.Entities;


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Period;

//Es una buena práctica ponerle nombre a las tablas
@Entity(name = "student")
@Table(
        name= "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)

//Se puede evitar tener que poner getters y setters gracias a lombok
public class Student {
    //Para mantener una generacion secuelcial de identificadores, podemos usar la etiqueta @SequenceGenerator
    @Id
    @SequenceGenerator(
            //Nombre del generador dentro de java
            name = "student_sequence",
            //Nombre del generador dentro de Postgre
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")

    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "date_of_birt", nullable = false)
    private LocalDate dob;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

//    Para indicar a Hibernate que no debe persistir un atributo en la base de datos se usa la anotacion @Transient
    @Transient
    private int age;

    public Student(String firstName, String lastName, LocalDate dob, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
