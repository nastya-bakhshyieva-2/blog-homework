package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.dto.TagResponse;
import com.nastyabakhshyieva.blog.entities.Article;

import java.util.List;

public interface TagService {

    List<TagResponse> getAllTags();

    void saveTagsIfNotExist(ArticleDto articleDto, Article article);

}
