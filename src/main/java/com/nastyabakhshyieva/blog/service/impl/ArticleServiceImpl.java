package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.dto.ArticleDto;
import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.entities.status.ArticleStatus;
import com.nastyabakhshyieva.blog.repositories.ArticleRepository;
import com.nastyabakhshyieva.blog.repositories.custom.ArticlesRepositoryCustom;
import com.nastyabakhshyieva.blog.service.ArticleService;
import com.nastyabakhshyieva.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticlesRepositoryCustom articlesRepositoryCustom;
    private final TagService tagService;


    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticlesRepositoryCustom articlesRepositoryCustom, TagService tagService) {
        this.articleRepository = articleRepository;
        this.articlesRepositoryCustom = articlesRepositoryCustom;
        this.tagService = tagService;
    }

    @Override
    public Article createArticle(ArticleDto articleDto, User owner) {

        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());

        if (articleDto.getStatus() != null) {
            article.setStatus(articleDto.getStatus().equalsIgnoreCase("public") ? ArticleStatus.PUBLIC : ArticleStatus.DRAFT);
        }

        article.setAuthorId(owner.getId());
        article.setTags(articleDto.getTags());
        article.setCreatedAt(new Date());
        article.setUpdatedAt(new Date());

        tagService.saveTagsIfNotExist(articleDto, article);

        articleRepository.save(article);

        log.info("IN createArticle - article with title: {} successfully saved", article.getTitle());

        return article;
    }

    @Override
    public Article updateArticle(Long oldId, ArticleDto newArticle, User owner) {

        Article article = articleRepository.findById(oldId).orElse(null);

        if (article == null || !article.getAuthorId().equals(owner.getId())) {
            log.info("IN updateArticle - article is null or user hasn't permissions");
            return null;
        }

        article.setTitle(newArticle.getTitle());
        article.setText(newArticle.getText());
        article.setUpdatedAt(new Date());
        article.setTags(newArticle.getTags());

        if (newArticle.getStatus() != null) {
            article.setStatus(newArticle.getStatus().equalsIgnoreCase("public") ? ArticleStatus.PUBLIC : ArticleStatus.DRAFT);
        }

        tagService.saveTagsIfNotExist(newArticle, article);

        articleRepository.save(article);

        log.info("IN updateArticle - article with title: {} successfully updated", article.getTitle());

        return article;

    }

    @Override
    public List<Article> getAllPublicPosts(Long skip, Long limit, String postTitle, Long authorId, String fieldName, String order, String[] tags) {

        Stream<Article> articleStream = articlesRepositoryCustom.getCriteriaArticles(skip, limit, postTitle, authorId, fieldName, order).stream();

        articleStream = filterByTags(articleStream, tags);

        List<Article> res = articleStream.collect(Collectors.toList());

        log.info("IN getAllPublicPosts - general size of requested posts: {}", res.size());

        return res;
    }

    @Override
    public List<Article> getOwnerPosts(User user) {

        List<Article> res = articleRepository.findByAuthorId(user.getId());

        log.info("IN getOwnerPosts - general size of user's posts: {}", res.size());

        return res;
    }

    @Override
    public Article deletePost(Long id, User user) {

        Article article = articleRepository.findById(id).orElse(null);

        if (article == null || !article.getAuthorId().equals(user.getId())) {
            return null;
        }

        article.getTags().forEach(t -> t.getArticles().remove(article));

        articleRepository.deleteById(id);

        log.info("IN deletePost - post with id: {} successfully deleted", id);

        return article;
    }

    private Stream<Article> filterByTags(Stream<Article> articleStream, String[] tags) {

        if (tags.length == 0) {
            return articleStream;
        }

        return articleStream.filter(a -> a.getTags().stream()
                        .anyMatch(tag -> Stream.of(tags)
                                .anyMatch(searchingTag -> searchingTag.equalsIgnoreCase(tag.getName()))));
    }

}
