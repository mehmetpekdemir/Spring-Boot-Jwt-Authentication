package com.mehmetpekdemir.jwt.common.constant;

/**
 * Swagger Api Response Messages
 *
 * @author MEHMET PEKDEMIR
 * @since 1.0
 */
public final class SwaggerMessages {
    private static final String UTILITY_CLASS = "Utility Class";

    private SwaggerMessages() {
        throw new IllegalArgumentException(UTILITY_CLASS);
    }

    public static final String SUCCESSFUL_LOGIN = "Successful login.";

    public static final String UNSUCCESSFUL_LOGIN = "Unsuccessful login.";

    public static final String FORBIDDEN = "Accessing the resource you were trying to reach is forbidden.";

}
