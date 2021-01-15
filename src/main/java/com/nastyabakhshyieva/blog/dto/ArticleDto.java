package com.nastyabakhshyieva.blog.dto;

import com.nastyabakhshyieva.blog.entities.Tag;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class ArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private String status;
    private Set<Tag> tags;

}
