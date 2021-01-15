package com.nastyabakhshyieva.blog.repositories.custom;

import com.nastyabakhshyieva.blog.entities.Article;
import com.nastyabakhshyieva.blog.entities.status.ArticleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticlesRepositoryCustomImpl implements ArticlesRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ArticlesRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Article> getCriteriaArticles(Long skip, Long limit, String postTitle, Long authorId, String fieldName, String order) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);

        Root<Article> articleRoot = criteriaQuery.from(Article.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!postTitle.equals("")) {
            predicates.add(criteriaBuilder.equal(articleRoot.get("title"), postTitle));
        }

        if (!authorId.equals(0L)) {
            predicates.add(criteriaBuilder.equal(articleRoot.get("authorId"), authorId));
        }

        predicates.add(criteriaBuilder.equal(articleRoot.get("status"), ArticleStatus.PUBLIC));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (!fieldName.equals("none")) {
            if (order.equals("desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(articleRoot.get(fieldName)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(articleRoot.get(fieldName)));
            }
        }

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(Math.toIntExact(skip))
                .setMaxResults(Math.toIntExact(limit))
                .getResultList();
    }
}
