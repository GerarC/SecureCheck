package co.edu.udea.securecheck.adapter.driven.security.adapters;

import co.edu.udea.securecheck.domain.spi.security.AuthenticationSecurityPort;
import co.edu.udea.securecheck.domain.utils.authentication.AuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSecurityAdapter implements AuthenticationSecurityPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public void authenticate(AuthenticationInfo authenticationInfo) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationInfo.getId(),
                        authenticationInfo.getPassword()
                )
        );
    }
}
