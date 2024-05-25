package com.tpe.controller;


import com.tpe.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService service;

    @GetMapping("/fetchBooks")
    public ResponseEntity<String> fetchBook(@RequestParam String query){
        service.fetchAndSaveBooks(query);
        return ResponseEntity.ok("Books are save successfully in db...");
    }
}
