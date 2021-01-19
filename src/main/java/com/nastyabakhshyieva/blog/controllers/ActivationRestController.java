package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.service.ActivationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

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
    public String forgotPassword(@RequestParam(name = "email") String email) {

        activationCodeService.forgotPassword(email);

        return null;
    }


    @PostMapping("/reset")
    public String resetPassword(@RequestParam(name = "code") String code,
                                @RequestParam(name = "new_password") String newPassword) {

        activationCodeService.resetPassword(code, newPassword);

        return null;
    }



    @GetMapping("/check_code/{code}")
    public String checkCodeValidity(@PathVariable String code) {

        activationCodeService.checkCodeValidity(code);

        return null;
    }
}
