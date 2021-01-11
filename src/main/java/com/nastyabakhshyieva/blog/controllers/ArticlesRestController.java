package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.entities.Article;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class ArticlesRestController {


    @PostMapping("/articles")
    public Article addArticle(@RequestBody @Valid ArticleDto articleDto) {
        return null;
    }


    @PutMapping("/articles/{id}")
    public Article editArticle(@PathVariable Long id, @RequestBody @Valid ArticleDto articleDto) {
        return null;
    }


    @GetMapping("/articles")
    public List<Article> getAllPublicPosts(@RequestParam(name = "skip", required = false, defaultValue = "0") Long skip,
                                           @RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
                                           @RequestParam(name = "q", required = false, defaultValue = "") String postTitle,
                                           @RequestParam(name = "author", required = false, defaultValue = "0") Long authorId,
                                           @RequestParam(name = "sort", required = false, defaultValue = "id") String fieldName,
                                           @RequestParam(name = "order", required = false, defaultValue = "asc") String order) {
        return null;
    }


    @GetMapping("/my")
    public List<Article> getMyPosts(Principal principal) {
        return null;
    }


    @DeleteMapping("/articles/{id}")
    public Article deleteArticle(@PathVariable Long id) {
        return null;
    }

}
