package co.edu.udea.securecheck.domain.api.usecase;

import co.edu.udea.securecheck.domain.exceptions.EmailAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.IdentityDocumentAlreadyExistsException;
import co.edu.udea.securecheck.domain.exceptions.UnderageUserException;
import co.edu.udea.securecheck.domain.model.Role;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.persistence.UserPersistencePort;
import co.edu.udea.securecheck.domain.usecase.UserUseCase;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
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
    private UserPersistencePort userPersistencePort;

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
                new Role(null, RoleName.ADMIN),
                null
                );

        // Declare what happens
        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByIdentityDocument(any())).thenReturn(false);
        when(userPersistencePort.save(any())).thenReturn(user);
        // Test
        User savedUser = userUseCase.save(user);

        // Verify and assert
        verify(userPersistencePort).save(any());
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
                new Role(null, RoleName.ADMIN),
                null
        );

        // Declare what happens
        when(userPersistencePort.existsByEmail(any())).thenReturn(true);
        when(userPersistencePort.existsByIdentityDocument(any())).thenReturn(false);
        when(userPersistencePort.save(any())).thenReturn(user);

        // Test exception
        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.save(user));

        // Verify
        verify(userPersistencePort, times(0)).save(any());
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
                new Role(null, RoleName.ADMIN),
                null
        );
        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByIdentityDocument(any())).thenReturn(true);
        when(userPersistencePort.save(any())).thenReturn(user);
        assertThrows(IdentityDocumentAlreadyExistsException.class, () -> userUseCase.save(user));
        verify(userPersistencePort, times(0)).save(any());
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
                new Role(null, RoleName.ADMIN),
                null
        );
        when(userPersistencePort.existsByEmail(any())).thenReturn(false);
        when(userPersistencePort.existsByIdentityDocument(any())).thenReturn(true);
        when(userPersistencePort.save(any())).thenReturn(user);
        assertThrows(UnderageUserException.class, () -> userUseCase.save(user));
        verify(userPersistencePort, times(0)).save(any());
    }
}