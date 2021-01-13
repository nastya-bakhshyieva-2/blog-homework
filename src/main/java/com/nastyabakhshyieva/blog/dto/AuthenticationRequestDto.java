package com.nastyabakhshyieva.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthenticationRequestDto {

    private String email;
    private String password;
}
