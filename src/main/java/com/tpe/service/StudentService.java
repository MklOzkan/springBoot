package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFound;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        //first check student with id exist
        Student student = findStudentById(id);
        //check if provided email exists in BD
        //and if email is going to be changed, email should not be one which is in DB
        boolean isEmailExist = repository.existsByEmail(studentDTO.getEmail());

        if(isEmailExist && !studentDTO.getEmail().equals(student.getEmail())){
            throw new ConflictException("Email exists already");
        }
        //map studentDTO to student
        student.setEmail(studentDTO.getEmail());
        student.setName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        repository.save(student);
    }

    public Page<Student> getAllStudentByPage(Pageable pageable) {

        return repository.findAll(pageable);
    }

    public List<Student> findStudentByField(String lastName) {

        return repository.findByLastName(lastName);
    }

    public List<Student> findStudentByGrade(Integer grade) {
        return repository.findByGradeUsingSQL(grade);
    }

    public StudentDTO findStudentDTOById(Long id) {
        return repository.findStudentDTOById(id).orElseThrow(()->
                new ResourceNotFound("No Student found with " + id));
    }
}
