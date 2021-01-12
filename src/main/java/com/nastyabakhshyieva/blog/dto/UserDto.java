package com.nastyabakhshyieva.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String repeatPassword;

    @NotBlank
    @Email
    private String email;

}
