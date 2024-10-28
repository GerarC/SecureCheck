package co.edu.udea.securecheck.domain.usecase.security;

import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.exceptions.ExpiredTokenException;
import co.edu.udea.securecheck.domain.exceptions.InvalidTokenException;
import co.edu.udea.securecheck.domain.model.Role;
import co.edu.udea.securecheck.domain.model.TokenHolder;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.security.AuthenticationSecurityPort;
import co.edu.udea.securecheck.domain.spi.security.TokenSecurityPort;
import co.edu.udea.securecheck.domain.spi.persistence.UserPersistencePort;
import co.edu.udea.securecheck.domain.utils.authentication.AuthenticatedUser;
import co.edu.udea.securecheck.domain.utils.authentication.AuthenticationInfo;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationUseCaseTest {

    @Mock
    private TokenSecurityPort tokenSecurityPort;
    @Mock
    private AuthenticationSecurityPort authenticationSecurityPort;
    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    private User testUser;
    private String email;
    private String password;
    private TokenHolder testTokenHolder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        email = "test@example.com";
        password = "password123";

        testUser = User.builder()
                .id("user123")
                .email(email)
                .role(
                        Role.builder()
                                .name(RoleName.AUDITOR)
                                .build()
                )
                .build();

        testTokenHolder = TokenHolder.builder()
                .token("testToken")
                .build();
    }

    @Test
    void authenticate_Success() {
        // Mock the expected behavior
        when(userPersistencePort.getUserByEmail(email)).thenReturn(testUser);
        when(tokenSecurityPort.createToken(testUser)).thenReturn(testTokenHolder);

        // Run the method
        AuthenticatedUser result = authenticationUseCase.authenticate(email, password);

        // Assertions
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getRole().getName(), result.getRole());
        assertEquals(testTokenHolder.getToken(), result.getToken());

        // Verify interactions
        verify(authenticationSecurityPort).authenticate(any(AuthenticationInfo.class));
        verify(tokenSecurityPort).createToken(testUser);
    }

    @Test
    void authenticate_UserNotFound_ThrowsEntityNotFoundException() {
        // Mock user not found
        when(userPersistencePort.getUserByEmail(email)).thenReturn(null);

        // Assert that exception is thrown
        assertThrows(EntityNotFoundException.class, () -> authenticationUseCase.authenticate(email, password));
    }

    @Test
    void validateToken_Success() {
        // Mock the expected behavior
        when(tokenSecurityPort.getUsername(testTokenHolder.getToken())).thenReturn(testUser.getId());
        when(tokenSecurityPort.validateToken(testTokenHolder.getToken(), testUser.getId())).thenReturn(true);
        when(userPersistencePort.getUser(testUser.getId())).thenReturn(testUser);

        // Run the method
        AuthenticatedUser result = authenticationUseCase.validateToken(testTokenHolder);

        // Assertions
        assertNotNull(result);
        assertEquals(testTokenHolder.getToken(), result.getToken());
        assertEquals(testUser.getRole().getName(), result.getRole());
        assertEquals(testUser.getId(), result.getId());
    }

    @Test
    void validateToken_ExpiredToken_ThrowsExpiredTokenException() {
        // Mock the expected behavior
        when(tokenSecurityPort.getUsername(testTokenHolder.getToken())).thenReturn(testUser.getId());
        when(tokenSecurityPort.validateToken(testTokenHolder.getToken(), testUser.getId())).thenReturn(false);

        // Assert that exception is thrown
        assertThrows(ExpiredTokenException.class, () -> authenticationUseCase.validateToken(testTokenHolder));
    }

    @Test
    void validateToken_InvalidToken_ThrowsInvalidTokenException() {
        // Mock an exception being thrown when trying to validate the token
        when(tokenSecurityPort.getUsername(testTokenHolder.getToken())).thenThrow(new RuntimeException("Token retrieval error"));

        // Assert that exception is thrown
        assertThrows(InvalidTokenException.class, () -> authenticationUseCase.validateToken(testTokenHolder));
    }
}
