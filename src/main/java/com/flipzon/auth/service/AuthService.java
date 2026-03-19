package com.flipzon.auth.service;

import com.flipzon.auth.dto.*;
import com.flipzon.auth.entity.User;

public interface AuthService {

    User register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}