package com.nastyabakhshyieva.blog.repositories;

import com.nastyabakhshyieva.blog.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);

    void deleteByName(String name);

}
