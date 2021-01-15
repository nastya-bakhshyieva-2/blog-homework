package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.CommentDto;
import com.nastyabakhshyieva.blog.entities.Comment;
import com.nastyabakhshyieva.blog.entities.User;

import java.util.List;

public interface CommentService {

    Comment createComment(Long postId, CommentDto commentDto, User user);

    List<Comment> getAllComments(Long postId);

    Comment getParticularComment(Long postId, Long commentId);

    Comment deleteComment(Long postId, Long commentId, User user);
}
