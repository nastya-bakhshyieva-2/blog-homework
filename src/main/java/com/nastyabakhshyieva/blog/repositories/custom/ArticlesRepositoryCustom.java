package com.nastyabakhshyieva.blog.repositories.custom;

import com.nastyabakhshyieva.blog.entities.Article;

import java.util.List;

public interface ArticlesRepositoryCustom {

    List<Article> getCriteriaArticles(Long skip, Long limit, String postTitle, Long authorId, String fieldName, String order);

}
