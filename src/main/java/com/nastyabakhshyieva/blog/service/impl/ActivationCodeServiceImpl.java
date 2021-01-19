package com.nastyabakhshyieva.blog.service.impl;

import com.nastyabakhshyieva.blog.entities.ActivationCode;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.repositories.ActivationCodeRepo;
import com.nastyabakhshyieva.blog.service.ActivationCodeService;
import com.nastyabakhshyieva.blog.service.MailSender;
import com.nastyabakhshyieva.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private final ActivationCodeRepo activationCodeRepo;
    private final UserService userService;
    private final MailSender mailSender;

    @Value("${activation.address}")
    private String address;

    @Autowired
    public ActivationCodeServiceImpl(ActivationCodeRepo activationCodeRepo, UserService userService, MailSender mailSender) {
        this.activationCodeRepo = activationCodeRepo;
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Override
    public void setActivationCode(User user) {

        ActivationCode activationCode = new ActivationCode();
        String uuid = UUID.randomUUID().toString();

        activationCode.setCode(uuid);
        activationCode.setUserId(user.getId());

        activationCodeRepo.save(activationCode);

        String msg = String.format("Hello, %s %s!\n" +
                "Please, follow next link to verify your account - %s",
                user.getFirstName(), user.getLastName(),
                address + uuid);

        mailSender.send(user.getEmail(), "Activation link", msg);

    }

    @Override
    public boolean activateUser(String code) {

        Optional<ActivationCode> codeFromDb = activationCodeRepo.findById(code);

        if (!codeFromDb.isPresent()) {
            return false;

        } else if (codeFromDb.get().getExpiration().isBefore(LocalDateTime.now())) {

            activationCodeRepo.delete(codeFromDb.get());
            return false;
        }

        boolean b = userService.activateUser(codeFromDb.get().getUserId());
        activationCodeRepo.delete(codeFromDb.get());

        return b;
    }

    @Override
    public boolean forgotPassword(String email) {

        User user = userService.findByEmail(email);

        if (user == null) {
            return false;
        }

        String uuid = UUID.randomUUID().toString();
        ActivationCode code = new ActivationCode();
        code.setCode(uuid);
        code.setUserId(user.getId());

        activationCodeRepo.save(code);

        String msg = String.format("Hello, %s %s!\n" +
                "Your reset password code - %s. Please, send a POST request with this code and your new_password",
                user.getFirstName(), user.getLastName(), uuid);

        mailSender.send(email, "Reset password", msg);

        return true;
    }

    @Override
    public boolean resetPassword(String code, String newPassword) {

        Optional<ActivationCode> codeFromDb = activationCodeRepo.findById(code);

        if (!codeFromDb.isPresent()) {
            return false;
        } else if (codeFromDb.get().getExpiration().isBefore(LocalDateTime.now())) {
            activationCodeRepo.delete(codeFromDb.get());
            return false;
        }

        return userService.updatePassword(codeFromDb.get().getUserId(), newPassword);
    }

    @Override
    public LocalDateTime checkCodeValidity(String code) {

        Optional<ActivationCode> activationCode = activationCodeRepo.findById(code);

        return activationCode.map(ActivationCode::getExpiration).orElse(null);

    }
}
