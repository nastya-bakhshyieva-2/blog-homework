package com.nastyabakhshyieva.blog.repositories;

import com.nastyabakhshyieva.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long id);

    Comment findCommentByPostIdAndId(Long postId, Long id);

}
