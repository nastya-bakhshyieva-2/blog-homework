package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.dto.UserDto;
import com.nastyabakhshyieva.blog.entities.User;


public interface UserService {

    User findByEmail(String email);

    User findById(Long id);

    User registerUser(UserDto userDto);

    boolean activateUser(Long id);
}
