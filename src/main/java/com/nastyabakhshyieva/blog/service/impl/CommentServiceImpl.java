package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.dto.CommentDto;
import com.nastyabakhshyieva.blog.entities.Comment;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.repositories.CommentRepository;
import com.nastyabakhshyieva.blog.service.CommentService;
import com.nastyabakhshyieva.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment createComment(Long postId, CommentDto commentDto, User user) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setAuthorId(user.getId());
        comment.setCreatedAt(new Date());
        comment.setMessage(commentDto.getMessage());

        Comment id = commentRepository.save(comment);

        log.info("IN createComment - comment with id: {} successfully saved", id.getId());

        return comment;
    }

    @Override
    public List<Comment> getAllComments(Long postId) {

        List<Comment> res = commentRepository.findAllByPostId(postId);

        log.info("IN getAllComments - total size of comments referred to post with id {} is: {}", postId, res.size());

        return res;
    }

    @Override
    public Comment getParticularComment(Long postId, Long commentId) {
        Comment res = commentRepository.findCommentByPostIdAndId(postId, commentId);

        log.info("IN getParticularComment - comment with id: {} successfully founded", res.getId());

        return res;
    }

    @Override
    public Comment deleteComment(Long postId, Long commentId, User user) {

        Comment dbComment = commentRepository.findCommentByPostIdAndId(postId, commentId);
        User postOwner = userService.findById(dbComment.getAuthorId());

        if (postOwner == null) {
            log.info("IN deleteComment - comment hasn't an author!");
            return null;
        }

        if (dbComment.getAuthorId().equals(user.getId()) || postOwner.getId().equals(user.getId())) {
            commentRepository.delete(dbComment);
            log.info("IN deleteComment - comment with id: {} successfully deleted", dbComment.getId());
            return dbComment;
        }

        log.info("IN deleteComment - user hasn't got permissions");
        return null;
    }
}
