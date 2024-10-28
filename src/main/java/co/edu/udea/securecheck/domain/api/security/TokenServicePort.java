package co.edu.udea.securecheck.domain.api.security;

public interface TokenServicePort {
    boolean validateToken(String token, String username);
    String getUsername(String token);
}
