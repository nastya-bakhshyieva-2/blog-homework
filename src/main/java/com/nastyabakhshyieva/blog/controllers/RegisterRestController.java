package com.nastyabakhshyieva.blog.controllers;

import com.nastyabakhshyieva.blog.dto.UserDto;
import com.nastyabakhshyieva.blog.entities.User;
import com.nastyabakhshyieva.blog.service.ActivationCodeService;
import com.nastyabakhshyieva.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterRestController {

    private final UserService userService;
    private final ActivationCodeService activationCodeService;

    @Autowired
    public RegisterRestController(UserService userService, ActivationCodeService activationCodeService) {
        this.userService = userService;
        this.activationCodeService = activationCodeService;
    }


    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadCredentialsException("Fields are not meet the requirements");

        } else if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {

            throw new BadCredentialsException("Passwords aren't equals");
        }

        User user = userService.registerUser(userDto);

        if (user != null) {

            activationCodeService.setActivationCode(user);
            return new ResponseEntity<>("User successfully registered. Please, check your email and verify the account",
                    HttpStatus.CREATED);
        } else {
            throw new BadCredentialsException("User with this email already exists");
        }

    }
}
