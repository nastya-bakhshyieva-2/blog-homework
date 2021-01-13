package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.CommentDto;
import com.nastyabakhshyieva.blog.entities.Comment;
import com.nastyabakhshyieva.blog.service.CommentService;
import com.nastyabakhshyieva.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class CommentsRestController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentsRestController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Object> addComment(@PathVariable(name = "id") Long postId, @RequestBody @Valid CommentDto commentDto, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Message is empty!", HttpStatus.BAD_REQUEST);
        }

        Comment created = commentService.createComment(postId, commentDto, userService.findByEmail(principal.getName()));
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/comments")
    public List<Comment> getAllComments(@PathVariable(name = "id") Long articleId) {
        return commentService.getAllComments(articleId);
    }


    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.getParticularComment(postId, commentId);
    }


    @DeleteMapping("/{postId}/comments/{commentId}")
    public Comment deleteComment(@PathVariable Long postId, @PathVariable Long commentId, Principal principal) {
        return commentService.deleteComment(postId, commentId, userService.findByEmail(principal.getName()));
    }

}
