package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.entities.User;

import java.util.List;

public interface ArticleService {

    Article createArticle(ArticleDto articleDto, User owner);

    Article updateArticle(Long oldId, ArticleDto newArticle, User owner);

    List<Article> getAllPublicPosts(Long skip, Long limit, String postTitle, Long authorId, String fieldName, String order, String[] tags);

    List<Article> getOwnerPosts(User user);

    Article deletePost(Long id, User user);
}
