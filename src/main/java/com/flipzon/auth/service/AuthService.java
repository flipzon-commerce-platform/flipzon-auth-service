package com.flipzon.auth.service;

import com.flipzon.auth.dto.*;
import com.flipzon.auth.entity.User;
import com.flipzon.auth.enums.Role;
import com.flipzon.auth.repository.UserRepository;
import com.flipzon.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public ResponseEntity<AuthResponse> register(RegisterRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(user);

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(user);

        AuthResponse response =
                new AuthResponse(accessToken, refreshToken);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<AuthResponse> login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = refreshTokenService.createRefreshToken(user);

        AuthResponse response =
                new AuthResponse(accessToken, refreshToken);

        return ResponseEntity.ok(response);
    }
}