package com.mehmetpekdemir.jwt.service;

import com.mehmetpekdemir.jwt.dto.request.LoginRequest;
import com.mehmetpekdemir.jwt.dto.request.SignUpRequest;
import com.mehmetpekdemir.jwt.dto.response.LoginResponse;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
public interface UserService {

    LoginResponse login(LoginRequest loginRequest);

    void signUp(SignUpRequest signUpRequest);

    boolean existsUserByUsername(String username);

}
