package com.tanvir.Tanvir.Student;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class StudentService {
    private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {

    return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws IllegalAccessException {
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalAccessException("email taken");

        }
        studentRepository.save(student);


    }

    public void deleteStudent(Long studentId) throws IllegalAccessException {
     boolean exists= studentRepository.existsById(studentId);
     if(!exists){
         throw new IllegalAccessException("student with id "+studentId+ "dosent exists");
     }
     studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
    Student student=studentRepository.findById(studentId).
            orElseThrow(()->new IllegalStateException("student with id"+studentId+ "Dosent exist"));

    if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
        student.setName(name);
        }

    if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
        Optional<Student>studentOptional=studentRepository.findStudentByEmail(email);
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");

        }
        student.setEmail(email);


    }


    }
}
