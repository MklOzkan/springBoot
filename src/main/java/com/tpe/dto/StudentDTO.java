package com.tpe.dto;

import com.tpe.domain.Student;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull(message = "Name muss not be empty")
    @Size(min = 3, max = 30, message = "first name '${validatedValue}' must be between {min} and {max} characters")
    private String firstName;
    private String lastName;
    private Integer grade;
    private String phoneNumber;
    @Email(message = "Provide valid email")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;

    private LocalDateTime createdDate = LocalDateTime.now();

    public StudentDTO(Student student){
        this.id = student.getId();
        this.firstName = student.getName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
        this.phoneNumber = student.getPhoneNumber();
        this.email = student.getEmail();
        this.createdDate = student.getCreatedDate();

    }
}
