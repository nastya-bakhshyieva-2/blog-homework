package com.nastyabakhshyieva.blog.entities;

import com.nastyabakhshyieva.blog.entities.util.ArticleStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String text;

    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tags_articles",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private List<Tag> tags;
}
