package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.repositories.UserRepository;
import com.nastyabakhshyieva.blog.security.UserDetailsFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with email " + s + " doesn't exists");
        }

        log.info("IN loadUserByUsername - user with email: {} successfully loaded", s);
        return UserDetailsFactory.create(user.get());
    }
}
