package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.dto.TagResponse;
import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.repositories.TagRepository;
import com.nastyabakhshyieva.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagResponse> getAllTags() {

        return tagRepository.findAll().stream()
                .map(t -> new TagResponse(t.getName(), t.getArticles().size()))
                .collect(Collectors.toList());

    }

    @Override
    public void saveTagsIfNotExist(ArticleDto articleDto, Article article) {

        if (articleDto.getTags() != null) {

            articleDto.getTags().forEach(t -> t.getArticles().add(article));

            articleDto.getTags().removeAll(articleDto.getTags().stream()
                    .map(t -> tagRepository.findByName(t.getName()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet()));

            tagRepository.saveAll(articleDto.getTags());

        }
    }

}
