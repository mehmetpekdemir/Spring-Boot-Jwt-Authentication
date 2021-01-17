package com.mehmetpekdemir.jwt.common.constant;

/**
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
public final class ExceptionMessages {

    private static final String UTILITY_CLASS = "Utility Class";

    private ExceptionMessages() {
        throw new IllegalArgumentException(UTILITY_CLASS);
    }

    public static final String EXPIRED_OR_INVALID_JWT_TOKEN = "Expired or invalid JWT token";

    public static final String INVALID_USERNAME_OR_PASSWORD_SUPPLIED = "Invalid username/password supplied";

    public static final String USER_NOT_FOUND = "User Not Found !";

    public static final String ROLE_NOT_FOUND = "Role Not Found !";

}
