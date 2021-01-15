package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.service.ArticleService;
import com.nastyabakhshyieva.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class ArticlesRestController {

    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    public ArticlesRestController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping("/articles")
    public Article addArticle(@RequestBody @Valid ArticleDto articleDto, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        return articleService.createArticle(articleDto, user);

    }


    @PutMapping("/articles/{id}")
    public Article editArticle(@PathVariable Long id, @RequestBody @Valid ArticleDto articleDto, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        return articleService.updateArticle(id, articleDto, user);

    }


    @GetMapping("/articles")
    public List<Article> getAllPublicPosts(@RequestParam(name = "skip", required = false, defaultValue = "0") Long skip,
                                           @RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
                                           @RequestParam(name = "q", required = false, defaultValue = "") String postTitle,
                                           @RequestParam(name = "author", required = false, defaultValue = "0") Long authorId,
                                           @RequestParam(name = "sort", required = false, defaultValue = "none") String fieldName,
                                           @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                           @RequestParam(name = "tags", required = false, defaultValue = "") String[] tags) {

        return articleService.getAllPublicPosts(skip, limit, postTitle, authorId, fieldName, order, tags);

    }


    @GetMapping("/my")
    public List<Article> getMyPosts(Principal principal) {

        User user = userService.findByEmail(principal.getName());
        return articleService.getOwnerPosts(user);

    }


    @DeleteMapping("/articles/{id}")
    public Article deleteArticle(@PathVariable Long id, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        return articleService.deletePost(id, user);

    }

}
