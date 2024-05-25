package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookTitle") //in json data, name of field will be "bookName"
    private String title;

    private String author;
    private String publishedDate;
    private String isbn;

    @ManyToOne
    @JoinColumn (name = "student_id")
    @JsonIgnore //it will not create field for student in json
    private Student student;



}