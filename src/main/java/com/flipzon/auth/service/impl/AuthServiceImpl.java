package com.flipzon.auth.service.impl;

import com.flipzon.auth.dto.*;
import com.flipzon.auth.entity.User;
import com.flipzon.auth.enums.Role;
import com.flipzon.auth.exception.InvalidCredentialsException;
import com.flipzon.auth.exception.UserNotFoundException;
import com.flipzon.auth.repository.UserRepository;
import com.flipzon.auth.service.AuthService;
import com.flipzon.auth.jwtutil.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public User register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        return userRepository.save(user);

    }

    public AuthResponse login(LoginRequest request) {
        log.info("In login method");

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null){
            throw new UserNotFoundException("User not found in the database");
        }
        log.info("user found {}",user.getEmail());

        if (user.getPassword().matches(passwordEncoder.encode(request.getPassword()))){
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        String accessToken = jwtService.generateAccessToken(user);

        AuthResponse response = new AuthResponse(accessToken);

        return response;
    }
}
