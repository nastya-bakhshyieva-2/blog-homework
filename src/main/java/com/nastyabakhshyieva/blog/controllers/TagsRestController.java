package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.entities.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/tags-cloud")
public class TagsRestController {

//    @GetMapping("/articles")
//    public List<Article> getArticlesByTags(@RequestParam(name = "tags") String[] tags) {
//        return null;
//    }

    @GetMapping
    public Map<Tag, Long> countTags() {
        return null;
    }
}
