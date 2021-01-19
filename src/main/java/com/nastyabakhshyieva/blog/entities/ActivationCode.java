package com.nastyabakhshyieva.blog.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@RedisHash
public class ActivationCode {

    @Id
    private String code;
    private Long userId;
    private LocalDateTime expiration = LocalDateTime.now().plusDays(1L);

}
