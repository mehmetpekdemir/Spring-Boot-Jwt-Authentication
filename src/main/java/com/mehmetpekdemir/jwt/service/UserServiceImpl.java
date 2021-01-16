package com.mehmetpekdemir.jwt.service;

import com.mehmetpekdemir.jwt.common.constant.ExceptionMessages;
import com.mehmetpekdemir.jwt.configuration.security.JwtTokenProvider;
import com.mehmetpekdemir.jwt.dto.request.LoginRequest;
import com.mehmetpekdemir.jwt.dto.response.LoginResponse;
import com.mehmetpekdemir.jwt.entity.UserEntity;
import com.mehmetpekdemir.jwt.exception.CustomException;
import com.mehmetpekdemir.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();
        try {
            final UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(ExceptionMessages.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            final String token = jwtTokenProvider.createToken(username, user.getRole());
            return new LoginResponse(token);
        } catch (AuthenticationException authenticationException) {
            throw new CustomException(ExceptionMessages.INVALID_USERNAME_OR_PASSWORD_SUPPLIED, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
