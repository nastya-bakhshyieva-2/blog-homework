package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.UserDto;
import com.nastyabakhshyieva.blog.entities.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User findByEmail(String email);

    boolean registerUser(UserDto userDto);

    boolean activateUser(String code);
}
