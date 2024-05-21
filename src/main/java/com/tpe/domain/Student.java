package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor//create parameterized constructor
@NoArgsConstructor//create non_parameterized constructor
@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Name muss not be empty")
    @Size(min = 3, max = 30, message = "first name '${validatedValue}' must be between {min} and {max} characters")
    private String name;
    private String lastName;
    private Integer grade;
    private String phoneNumber;
    @Column(nullable = false, length = 50, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;
    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "US")
    private LocalDateTime createdDate = LocalDateTime.now();

}
