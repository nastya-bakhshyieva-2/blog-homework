package com.nastyabakhshyieva.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nastyabakhshyieva.blog.entities.status.ArticleStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    @JsonIgnoreProperties("articles")
    private Set<Tag> tags;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return title.equals(article.title) &&
                text.equals(article.text) &&
                authorId.equals(article.authorId) &&
                createdAt.equals(article.createdAt);
    }


    @Override
    public int hashCode() {
        return Objects.hash(title, text, authorId, createdAt);
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        tags.stream()
                .map(Tag::getName)
                .forEach(n -> sb.append(n).append(" "));

        return String.format("Article {ID=%d, title=%s, text=%s, status=%s, author_id=%d, created_at=%s, " +
                "updated_at=%s, tags=%s}", id, title, text, status.name(), authorId, createdAt.toString(),
                updatedAt.toString(), sb.toString());
    }
}
