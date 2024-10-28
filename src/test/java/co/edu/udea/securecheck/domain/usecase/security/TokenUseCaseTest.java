package co.edu.udea.securecheck.domain.usecase.security;

import co.edu.udea.securecheck.domain.spi.security.TokenSecurityPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenUseCaseTest {

    @Mock
    private TokenSecurityPort tokenSecurityPort;

    @InjectMocks
    private TokenUseCase tokenUseCase;

    private String testToken;
    private String testUsername;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testToken = "testToken";
        testUsername = "testUsername";
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        when(tokenSecurityPort.validateToken(testToken, testUsername)).thenReturn(true);

        boolean result = tokenUseCase.validateToken(testToken, testUsername);

        assertTrue(result);

        verify(tokenSecurityPort).validateToken(testToken, testUsername);
    }

    @Test
    void validateToken_InvalidToken_ReturnsFalse() {
        when(tokenSecurityPort.validateToken(testToken, testUsername)).thenReturn(false);

        boolean result = tokenUseCase.validateToken(testToken, testUsername);

        assertFalse(result);

        verify(tokenSecurityPort).validateToken(testToken, testUsername);
    }

    @Test
    void getUsername_ValidToken_ReturnsUsername() {
        when(tokenSecurityPort.getUsername(testToken)).thenReturn(testUsername);

        String result = tokenUseCase.getUsername(testToken);

        assertEquals(testUsername, result);

        verify(tokenSecurityPort).getUsername(testToken);
    }

    @Test
    void getUsername_InvalidToken_ReturnsNull() {
        when(tokenSecurityPort.getUsername(testToken)).thenReturn(null);

        String result = tokenUseCase.getUsername(testToken);

        assertNull(result);

        verify(tokenSecurityPort).getUsername(testToken);
    }
}
