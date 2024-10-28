package co.edu.udea.securecheck.domain.spi.security;

import co.edu.udea.securecheck.domain.model.TokenHolder;
import co.edu.udea.securecheck.domain.model.User;

public interface TokenSecurityPort {
    TokenHolder createToken(User user);

    /**
     * @param token a token already generated
     * @param username id of the user
     * @return true if is a valid token false in the other case
     */
    boolean validateToken(String token, String username);

    String getUsername(String token);
}
