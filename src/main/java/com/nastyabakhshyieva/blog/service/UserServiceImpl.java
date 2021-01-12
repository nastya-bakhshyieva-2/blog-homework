package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.UserDto;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.entities.util.UserStatus;
import com.nastyabakhshyieva.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

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
    public boolean registerUser(UserDto userDto) {

        Optional<User> userFromDb = userRepository.findByEmail(userDto.getEmail());

        if (userFromDb.isPresent()) {
            return false;
        }

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setStatus(UserStatus.NON_ACTIVATED);

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean activateUser(String code) {
        return false;
    }
}
