package com.nastyabakhshyieva.blog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {

    @NotBlank
    private String message;

}
