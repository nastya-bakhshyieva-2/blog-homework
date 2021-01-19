package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.entities.ActivationCode;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.repositories.ActivationCodeRepo;
import com.nastyabakhshyieva.blog.service.ActivationCodeService;
import com.nastyabakhshyieva.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private final ActivationCodeRepo activationCodeRepo;
    private final UserService userService;

    @Autowired
    public ActivationCodeServiceImpl(ActivationCodeRepo activationCodeRepo, UserService userService) {
        this.activationCodeRepo = activationCodeRepo;
        this.userService = userService;
    }

    @Override
    public void setActivationCode(User user) {

        ActivationCode activationCode = new ActivationCode();
        String uuid = UUID.randomUUID().toString();

        activationCode.setCode(uuid);
        activationCode.setUserId(user.getId());

    }

    @Override
    public boolean activateUser(String code) {

        Optional<ActivationCode> codeFromDb = activationCodeRepo.findById(code);

        if (!codeFromDb.isPresent()) {
            return false;
        } else if (codeFromDb.get().getExpiration().isAfter(LocalDateTime.now())) {
            activationCodeRepo.delete(codeFromDb.get());
            return false;
        }

        boolean b = userService.activateUser(codeFromDb.get().getUserId());
        activationCodeRepo.delete(codeFromDb.get());

        return b;
    }

    @Override
    public boolean forgotPassword(String email) {
        return false;
    }

    @Override
    public boolean resetPassword(String code, String newPassword) {
        return false;
    }

    @Override
    public boolean checkCodeValidity(String code) {
        return false;
    }
}
