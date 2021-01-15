package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.TagResponse;
import com.nastyabakhshyieva.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags-cloud")
public class TagsRestController {

    private final TagService tagService;

    @Autowired
    public TagsRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagResponse> countTags() {

        return tagService.getAllTags();

    }
}
