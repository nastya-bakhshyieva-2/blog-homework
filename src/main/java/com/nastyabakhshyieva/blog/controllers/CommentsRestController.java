package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.CommentDto;
import com.nastyabakhshyieva.blog.entities.Comment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class CommentsRestController {


    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable(name = "id") Long postId, @RequestBody @Valid CommentDto commentDto, Principal principal) {
        return null;
    }


    @GetMapping("/{id}/comments")
    public List<Comment> getAllComments(@PathVariable(name = "id") Long postId,
                                        @RequestParam(name = "skip", required = false, defaultValue = "0") Long skip,
                                        @RequestParam(name = "limit", required = false, defaultValue = "10") Long limit,
                                        @RequestParam(name = "q", required = false, defaultValue = "") String postTitle,
                                        @RequestParam(name = "author", required = false, defaultValue = "0") Long authorId,
                                        @RequestParam(name = "sort", required = false, defaultValue = "id") String fieldName,
                                        @RequestParam(name = "order", required = false, defaultValue = "asc") String order) {
        return null;
    }


    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return null;
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    public Comment deleteComment(@PathVariable Long postId, @PathVariable Long commentId, Principal principal) {
        return null;
    }

}
