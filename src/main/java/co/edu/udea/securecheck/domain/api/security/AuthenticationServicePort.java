package co.edu.udea.securecheck.domain.api.security;

import co.edu.udea.securecheck.domain.model.TokenHolder;
import co.edu.udea.securecheck.domain.utils.authentication.AuthenticatedUser;

public interface AuthenticationServicePort {
    AuthenticatedUser authenticate(String email, String password);
    AuthenticatedUser validateToken(TokenHolder tokenHolder);
}
