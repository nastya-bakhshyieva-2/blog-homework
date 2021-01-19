package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.dto.UserDto;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.entities.status.UserStatus;
import com.nastyabakhshyieva.blog.repositories.UserRepository;
import com.nastyabakhshyieva.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User registerUser(UserDto userDto) {

        Optional<User> userFromDb = userRepository.findByEmail(userDto.getEmail());

        if (userFromDb.isPresent()) {
            return null;
        }

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setStatus(UserStatus.NON_ACTIVATED);
        user.setCreatedAt(new Date());

        user = userRepository.save(user);
        log.info("IN registerUser - User with email: {} successfully registered", user.getEmail());

        return user;
    }

    @Override
    public boolean activateUser(Long id) {

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return false;
        }

        User u = user.get();
        u.setStatus(UserStatus.ACTIVATED);
        userRepository.save(u);

        return true;
    }
}
