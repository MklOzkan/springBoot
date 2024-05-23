package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/students") //http://localhost:8080/students
@RestController //@Controller+ @ResponseBody (to be able to use rest api)
public class StudentController {

    @Autowired
    private StudentService service;

    //method to get all students
    //http://localhost:8080/students + GET
    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> studentList = service.getAllStudents();
        return ResponseEntity.ok(studentList); //it should return status code 200, and list of students
        //return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    //method to add student
    //http://localhost:8080/students + POST + body (json)
    @PostMapping
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student){

        service.createStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED); //201
    }

    @GetMapping("query")
    public ResponseEntity<Student> getStudentById(@RequestParam("id") Long id){
        Student student = service.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentByIdUsingPathVariable(@PathVariable("id") Long id){
        Student student = service.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    //method to delete student by ID //http://localhost:8080/students/1 +DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id){
        service.deleteStuedentById(id);
        Map<String, String>  map = new HashMap<>();
        map.put("message", "Student has been deleted successfully");
        map.put("status", "true");
        return  new ResponseEntity<>(map, HttpStatus.OK); //return ResponseEntity.ok(map) //200
    }

    //method to update student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id,
                                                @RequestBody StudentDTO studentDTO){
        service.updateStudent(id, studentDTO);
        String message = "Student is updated successfully";
        return ResponseEntity.ok(message);
    }

}
