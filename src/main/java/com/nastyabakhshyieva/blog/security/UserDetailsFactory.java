package com.nastyabakhshyieva.blog.security;

import com.nastyabakhshyieva.blog.entities.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

@NoArgsConstructor
public final class UserDetailsFactory {

    public static UserDetails create(User user) {

        return new UserDetailsImpl(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getStatus(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

}
