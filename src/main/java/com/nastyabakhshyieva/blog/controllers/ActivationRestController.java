package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.ForgotPasswordDto;
import com.nastyabakhshyieva.blog.dto.ResetPasswordDto;
import com.nastyabakhshyieva.blog.service.ActivationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class ActivationRestController {

    private final ActivationCodeService activationCodeService;

    @Autowired
    public ActivationRestController(ActivationCodeService activationCodeService) {
        this.activationCodeService = activationCodeService;
    }

    @GetMapping("/confirm/{code}")
    public ResponseEntity<String> activateUser(@PathVariable String code) {

        if (activationCodeService.activateUser(code)) {
            return new ResponseEntity<>("Account successfully activated", HttpStatus.OK);
        } else {
            throw new BadCredentialsException("Your link expired or invalid");
        }
    }


    @PostMapping("/forgot_password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid ForgotPasswordDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadCredentialsException("Fields are not meet the requirements");
        }

        if (activationCodeService.forgotPassword(dto.getEmail())) {
            return new ResponseEntity<>("The reset code was sent. Please, check your email",
                    HttpStatus.OK);
        } else {
            throw new BadCredentialsException("User with email " + dto.getEmail() + " not founded");
        }

    }


    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            throw new BadCredentialsException("Fields are not meet the requirements");

        } else if (!dto.getNewPassword().equals(dto.getRepeatPassword())) {
            throw new BadCredentialsException("Passwords aren't equals");
        }

        if (activationCodeService.resetPassword(dto.getCode(), dto.getNewPassword())) {

            return new ResponseEntity<>("Password successfully updated! Now you can login",
                    HttpStatus.OK);

        } else {
            throw new BadCredentialsException("Reset password code expired or invalid");
        }

    }


    @GetMapping("/check_code/{code}")
    public String checkCodeValidity(@PathVariable String code) {

        LocalDateTime expirationDate = activationCodeService.checkCodeValidity(code);

        if (expirationDate != null) {
            return "Code will be expire at: " + expirationDate.toString();

        } else {
            return "Code doesn't exists";

        }

    }
}
