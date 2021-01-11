package com.nastyabakhshyieva.blog.dto;

import com.nastyabakhshyieva.blog.entities.Tag;
import com.nastyabakhshyieva.blog.entities.util.ArticleStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotBlank
    private ArticleStatus status;

    private List<Tag> tags;

}
