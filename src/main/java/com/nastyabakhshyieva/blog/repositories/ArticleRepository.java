package com.nastyabakhshyieva.blog.repositories;

import com.nastyabakhshyieva.blog.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuthorId(Long authorId);

    @Query(value = "select * from articles limit :from, :to", nativeQuery = true)
    List<Article> findRange(@Param("from") Long from, @Param("to") Long to);
}
