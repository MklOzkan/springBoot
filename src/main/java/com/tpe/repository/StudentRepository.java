package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //In Spring Data JPA there is a default method existsByEmail(), 
    //but we can update Id with any of field name
    boolean existsByEmail(String email);
}
