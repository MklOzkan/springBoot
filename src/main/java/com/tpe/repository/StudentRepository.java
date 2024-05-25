package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    //In Spring Data JPA there is a default method existsByEmail(), 
    //but we can update Id with any of field name
    boolean existsByEmail(String email);

    List<Student> findByLastName(String lastName);

    //method to return students by grade using JPQL
    @Query("SELECT s FROM Student s where s.grade>=:pGrade")
    List<Student> findByGradeUsingJPQL(@Param("pGrade") Integer grade);

    @Query(value = "SELECT  * from  tbl_student  s WHERE s.grade=:pGrade", nativeQuery = true)
    List<Student> findByGradeUsingSQL(@Param("pGrade") Integer grade);

    @Query("SELECT new com.tpe.dto.StudentDTO(s) from Student s where s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);
}
