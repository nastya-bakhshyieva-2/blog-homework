package com.nastyabakhshyieva.blog.service;

import com.nastyabakhshyieva.blog.entities.User;

import java.time.LocalDateTime;

public interface ActivationCodeService {

    void setActivationCode(User user);

    boolean activateUser(String code);

    boolean forgotPassword(String email);

    boolean resetPassword(String code, String newPassword);

    LocalDateTime checkCodeValidity(String code);

}
