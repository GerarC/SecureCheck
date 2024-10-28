package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.exceptions.*;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.persistence.UserPersistencePort;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void testSaveUser_Success() {
        // Arrange
        User user = new User.Builder().id("1").name("John").birthdate(LocalDateTime.now().minusYears(20)).email("john@example.com").identityDocument("12345").build();
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdentityDocument(user.getIdentityDocument())).thenReturn(false);
        when(userPersistencePort.save(user)).thenReturn(user);

        // Act
        User result = userUseCase.save(user);

        // Assert
        assertEquals(user, result);
        verify(userPersistencePort, times(1)).save(user);
    }

    @Test
    void testSaveUser_UnderageUserException() {
        // Arrange
        User user = new User.Builder().birthdate(LocalDateTime.now().minusYears(17)).build();

        // Act & Assert
        assertThrows(UnderageUserException.class, () -> userUseCase.save(user));
    }

    @Test
    void testSaveUser_EmailAlreadyExistsException() {
        // Arrange
        User user = new User.Builder().email("john@example.com").birthdate(LocalDateTime.now().minusYears(20)).build();
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> userUseCase.save(user));
    }

    @Test
    void testSaveUser_IdentityDocumentAlreadyExistsException() {
        // Arrange
        User user = new User.Builder().identityDocument("12345").birthdate(LocalDateTime.now().minusYears(20)).build();
        when(userPersistencePort.existsByIdentityDocument(user.getIdentityDocument())).thenReturn(true);

        // Act & Assert
        assertThrows(IdentityDocumentAlreadyExistsException.class, () -> userUseCase.save(user));
    }

    @Test
    void testCreateAuditor() {
        // Arrange
        User user = new User.Builder().birthdate(LocalDateTime.now().minusYears(20)).build();
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.existsByIdentityDocument(user.getIdentityDocument())).thenReturn(false);
        when(userPersistencePort.save(user)).thenReturn(user);

        // Act
        User result = userUseCase.createAuditor(user);

        // Assert
        assertEquals(RoleName.AUDITOR, result.getRole().getName());
        verify(userPersistencePort, times(1)).save(user);
    }

    @Test
    void testGetUserCompanies_Success() {
        // Arrange
        String userId = "1";
        SortQuery sortQuery = new SortQuery("name", true);
        CompanyFilter filter = new CompanyFilter(null, "Test Company", null, null, null);
        List<Company> expectedCompanies = List.of(new Company.Builder().id("1").name("Test Company").build());
        when(userPersistencePort.existsById(userId)).thenReturn(true);
        when(userPersistencePort.getUserCompanies(userId, sortQuery, filter)).thenReturn(expectedCompanies);

        // Act
        List<Company> result = userUseCase.getUserCompanies(userId, sortQuery, filter);

        // Assert
        assertEquals(expectedCompanies, result);
        verify(userPersistencePort, times(1)).getUserCompanies(userId, sortQuery, filter);
    }

    @Test
    void testGetUserCompanies_EntityNotFoundException() {
        // Arrange
        String userId = "1";
        when(userPersistencePort.existsById(userId)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userUseCase.getUserCompanies(userId, null, null));
    }

    @Test
    void testGetUserByEmail_Success() {
        // Arrange
        String email = "john@example.com";
        User expectedUser = new User.Builder().email(email).build();
        when(userPersistencePort.getUserByEmail(email)).thenReturn(expectedUser);

        // Act
        User result = userUseCase.getUserByEmail(email);

        // Assert
        assertEquals(expectedUser, result);
        verify(userPersistencePort, times(1)).getUserByEmail(email);
    }

    @Test
    void testGetUserByEmail_EntityNotFoundException() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userPersistencePort.getUserByEmail(email)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userUseCase.getUserByEmail(email));
    }

    @Test
    void testGetUser_Success() {
        // Arrange
        String userId = "1";
        User expectedUser = new User.Builder().id(userId).build();
        when(userPersistencePort.getUser(userId)).thenReturn(expectedUser);

        // Act
        User result = userUseCase.getUser(userId);

        // Assert
        assertEquals(expectedUser, result);
        verify(userPersistencePort, times(1)).getUser(userId);
    }

    @Test
    void testGetUser_EntityNotFoundException() {
        // Arrange
        String userId = "nonexistent";
        when(userPersistencePort.getUser(userId)).thenReturn(null);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> userUseCase.getUser(userId));
    }
}
