package com.flipzon.auth.service;

import com.flipzon.auth.entity.RefreshToken;
import com.flipzon.auth.entity.User;
import com.flipzon.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenDuration;

    public String createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDuration));

        refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {

            refreshTokenRepository.delete(token);

            throw new RuntimeException("Refresh token expired");
        }

        return token;
    }
}