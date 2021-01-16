package com.mehmetpekdemir.jwt.configuration.security;

import com.mehmetpekdemir.jwt.common.constant.ExceptionMessages;
import com.mehmetpekdemir.jwt.entity.RoleEntity;
import com.mehmetpekdemir.jwt.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AuthService authService;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds = 3600000;

    public String createToken(String username, RoleEntity role) {
        final Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", role.getName());

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        final UserDetails user = authService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ExceptionMessages.EXPIRED_OR_INVALID_JWT_TOKEN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
