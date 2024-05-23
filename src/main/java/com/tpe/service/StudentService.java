package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFound;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public void createStudent(Student student) {

        if (repository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email exist already");
        }
        repository.save(student);
    }


    public Student findStudentById(Long id) {
        return repository.findById(id).orElseThrow(()->
                new ResourceNotFound("No student with ID "+id));
    }

    public void deleteStuedentById(Long id) {
        Student student = findStudentById(id);
        repository.delete(student);
    }


    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student student = findStudentById(id);
        boolean isEmailExist = repository.existsByEmail(studentDTO.getEmail());
        if (isEmailExist&& !studentDTO.getEmail().equals(student.getEmail())){
            throw new ConflictException("Email exists already");
        }
        student.setName(studentDTO.getName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEmail(studentDTO.getEmail());
        repository.save(student);
    }
}
