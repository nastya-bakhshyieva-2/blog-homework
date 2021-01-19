package com.nastyabakhshyieva.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ForgotPasswordDto {

    @NotBlank
    @Email
    private String email;
}
