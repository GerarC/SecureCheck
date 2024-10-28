package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AuthenticationRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.UserRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuthenticationResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.RegisterResponse;

public interface AuthenticationService {
    RegisterResponse registerAuditor(UserRequest userRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    AuthenticationResponse validateToken(String token);
}
