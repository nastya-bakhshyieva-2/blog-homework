package com.nastyabakhshyieva.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordDto {

    @NotBlank
    private String code;

    @NotBlank
    @Size(min = 8)
    private String newPassword;

    @NotBlank
    private String repeatPassword;
}
