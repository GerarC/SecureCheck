package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AuthenticationRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.UserRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuthenticationResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.RegisterResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.UserRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.AuthenticationService;
import co.edu.udea.securecheck.domain.api.UserServicePort;
import co.edu.udea.securecheck.domain.api.security.AuthenticationServicePort;
import co.edu.udea.securecheck.domain.model.TokenHolder;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.Constants;
import co.edu.udea.securecheck.domain.utils.authentication.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final AuthenticationServicePort authenticationServicePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse registerAuditor(UserRequest userRequest) {
        User user = userRequestMapper.toDomain(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser = userServicePort.createAuditor(user);
        return RegisterResponse.builder()
                .message(String.format(Constants.AUDITOR_REGISTERED_MESSAGE,
                        savedUser.getName(),
                        savedUser.getLastname(),
                        savedUser.getEmail()))
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        AuthenticatedUser user = authenticationServicePort.authenticate(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );
        return AuthenticationResponse.builder()
                .token(user.getToken())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse validateToken(String token) {
        AuthenticatedUser user = authenticationServicePort.validateToken(
                TokenHolder.builder().token(token).build()
        );
        return AuthenticationResponse.builder()
                .token(user.getToken())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}
