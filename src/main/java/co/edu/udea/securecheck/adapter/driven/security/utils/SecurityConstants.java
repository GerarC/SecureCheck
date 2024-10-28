package co.edu.udea.securecheck.adapter.driven.security.utils;

public class SecurityConstants {
    private SecurityConstants() {
        throw new IllegalStateException("Security Utility class");
    }

    public static final String INVALID_TOKEN_MESSAGE = "Given token is invalid";
    public static final String ROLE_PREFIX = "ROLE_";
}
