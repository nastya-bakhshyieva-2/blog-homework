package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.entities.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TagsRestController {

    @GetMapping("/articles")
    public List<Article> getArticlesByTags(@RequestParam(name = "tags") String[] tags) {
        return null;
    }

    @GetMapping("/tags-cloud")
    public Map<Tag, Long> countTags() {
        return null;
    }
}
