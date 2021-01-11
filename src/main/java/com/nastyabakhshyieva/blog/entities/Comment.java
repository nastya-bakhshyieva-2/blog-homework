package com.nastyabakhshyieva.blog.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;
}
