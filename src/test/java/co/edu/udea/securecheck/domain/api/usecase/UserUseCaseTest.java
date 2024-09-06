package co.edu.udea.securecheck.domain.api.usecase;

import co.edu.udea.securecheck.domain.exceptions.EmailAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.IdentityDocumentAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.UnderageUserException;
import co.edu.udea.securecheck.domain.model.Role;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.UserPersitencePort;
import co.edu.udea.securecheck.domain.utils.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private UserPersitencePort userPersitencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        // Define mock data
        User user = new User(
                null,
                "admin",
                "admin",
                "0000000001",
                LocalDateTime.MIN,
                "+573332223232",
                "admin@admin.com",
                "password",
                new Role(null, RoleName.ADMIN));

        // Declare what happens
        when(userPersitencePort.existsByEmail(any())).thenReturn(false);
        when(userPersitencePort.existsByIdentityDocument(any())).thenReturn(false);
        when(userPersitencePort.save(any())).thenReturn(user);
        // Test
        User savedUser = userUseCase.save(user);

        // Verify and assert
        verify(userPersitencePort).save(any());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    void saveEmailAlreadyExists() {
        // Instance mock data
        User user = new User(
                null,
                "admin",
                "admin",
                "0000000001",
                LocalDateTime.MIN,
                "+573332223232",
                "admin@admin.com",
                "password",
                new Role(null, RoleName.ADMIN));

        // Declare what happens
        when(userPersitencePort.existsByEmail(any())).thenReturn(true);
        when(userPersitencePort.existsByIdentityDocument(any())).thenReturn(false);
        when(userPersitencePort.save(any())).thenReturn(user);

        // Test exception
        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.save(user));

        // Verify
        verify(userPersitencePort, times(0)).save(any());
    }

    @Test
    void saveDocumentAlreadyExists() {
        User user = new User(
                null,
                "admin",
                "admin",
                "0000000001",
                LocalDateTime.MIN,
                "+573332223232",
                "admin@admin.com",
                "password",
                new Role(null, RoleName.ADMIN));
        when(userPersitencePort.existsByEmail(any())).thenReturn(false);
        when(userPersitencePort.existsByIdentityDocument(any())).thenReturn(true);
        when(userPersitencePort.save(any())).thenReturn(user);
        assertThrows(IdentityDocumentAlreadyExistsException.class, () -> userUseCase.save(user));
        verify(userPersitencePort, times(0)).save(any());
    }

    @Test
    void saveUnderageUser() {
        User user = new User(
                null,
                "admin",
                "admin",
                "0000000001",
                LocalDateTime.now(),
                "+573332223232",
                "admin@admin.com",
                "password",
                new Role(null, RoleName.ADMIN));
        when(userPersitencePort.existsByEmail(any())).thenReturn(false);
        when(userPersitencePort.existsByIdentityDocument(any())).thenReturn(true);
        when(userPersitencePort.save(any())).thenReturn(user);
        assertThrows(UnderageUserException.class, () -> userUseCase.save(user));
        verify(userPersitencePort, times(0)).save(any());
    }
}