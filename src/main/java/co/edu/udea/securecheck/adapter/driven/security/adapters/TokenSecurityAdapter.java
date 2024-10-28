package co.edu.udea.securecheck.adapter.driven.security.adapters;

import co.edu.udea.securecheck.adapter.driven.security.service.JwtService;
import co.edu.udea.securecheck.adapter.driven.security.exceptions.InvalidTokenException;
import co.edu.udea.securecheck.adapter.driven.security.utils.SecurityConstants;
import co.edu.udea.securecheck.domain.model.TokenHolder;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.security.TokenSecurityPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenSecurityAdapter implements TokenSecurityPort {
    private final JwtService jwtService;

    @Override
    public TokenHolder createToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("role", SecurityConstants.ROLE_PREFIX
                        + user.getRole().getName().name());
        String token = jwtService.generateToken(claims, user.getId());

        return TokenHolder.builder()
                .token(token)
                .build();
    }

    @Override
    public boolean validateToken(String token, String username) {
        try {
            return jwtService.isTokenValid(token, username);
        } catch (InvalidTokenException e) {
            return false;
        }
    }

    @Override
    public String getUsername(String token) {
        return jwtService.extractUsername(token);
    }
}
