package com.tpe.controller;

import com.tpe.domain.User;
import com.tpe.dto.UserRequestDTO;
import com.tpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/register") //http://localhost:8080/register

    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDTO requestDTO){
        userService.saveUser(requestDTO);
        String message = "User has been created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
