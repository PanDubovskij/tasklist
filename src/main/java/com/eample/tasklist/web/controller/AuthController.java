package com.eample.tasklist.web.controller;

import com.eample.tasklist.domain.user.User;
import com.eample.tasklist.service.AuthService;
import com.eample.tasklist.service.UserService;
import com.eample.tasklist.web.dto.auth.JwtRequest;
import com.eample.tasklist.web.dto.auth.JwtResponse;
import com.eample.tasklist.web.dto.mappers.UserMapper;
import com.eample.tasklist.web.dto.user.UserDto;
import com.eample.tasklist.web.dto.validation.OnCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(
            final @Validated @RequestBody JwtRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public UserDto register(
            final @Validated(OnCreate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(final @RequestBody String refreshToken) {
        return authService.refresh(refreshToken);
    }
}
