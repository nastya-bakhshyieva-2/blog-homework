package com.nastyabakhshyieva.blog.repositories;

import com.nastyabakhshyieva.blog.entities.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationCodeRepo extends JpaRepository<ActivationCode, String> {
}
