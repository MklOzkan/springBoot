package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    Logger logger = LoggerFactory.getLogger("StudentController.class");

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

    //method to get student by pagination
    @GetMapping ("/page")  //http://localhost:8080/students/page?page=0&size=3&sort=name&direction=ASC + GET
    public ResponseEntity<Page<Student>> getStudentsByPage(
            @RequestParam("page") int page, //number of page
            @RequestParam("size") int size, //number of students in each
            @RequestParam("sort") String prop, //optional, property which will used for sort
            @RequestParam("direction") Sort.Direction direction // sorting direction : ascending or descending
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<Student> studentPage = service.getAllStudentByPage(pageable);
        return ResponseEntity.ok(studentPage);
    }

    //method to get student by lastName
    @GetMapping("/lastname")  //http://localhost:8080/students/lastname?lastname=Kelesh + GET
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastname") String lName){
        List <Student> students = service.findStudentByField(lName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("query/{grade}") //http://localhost:8080/students/query/90+ GET
    public ResponseEntity<List<Student>> getStudentByTheirGrade(@PathVariable("grade") Integer grade){
        List<Student> students = service.findStudentByGrade(grade);
        return ResponseEntity.ok(students);
    }

    //method / end point to fetch DTO from Repo
    @GetMapping("/query/dto")   //http://localhost:8080/students/query/dto?id=2 + GET
    public ResponseEntity<StudentDTO> getStudentDTOById(@RequestParam("id") Long id){
        StudentDTO studentDTO = service.findStudentDTOById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @GetMapping("/welcome")  //http://localhost:8080/students/welcome
    public String welcome(HttpServletRequest request){
        logger.warn("====================== Welcome {}", request.getServletPath());
        return "Welcome to Student Controller";
    }

}
