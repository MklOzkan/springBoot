package com.tpe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "enter first name")
    private String firsName;
    @NotBlank(message = "enter last name")
    private String lastName;
    @NotBlank(message = "enter user name")
    @Size(message = "please provide an user name between 5 and 20 characters")
    private String userName;
    @NotBlank(message = "enter password")
    private String password;

}
