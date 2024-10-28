package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;
import co.edu.udea.securecheck.domain.spi.persistence.DomainPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DomainUseCaseTest {

    @Mock
    private DomainPersistencePort domainPersistencePort;

    @InjectMocks
    private DomainUseCase domainUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDomains() {
        // Arrange
        List<Domain> expectedDomains = List.of(new Domain.Builder().id(1L).name("Domain1").build());
        when(domainPersistencePort.getDomains()).thenReturn(expectedDomains);

        // Act
        List<Domain> result = domainUseCase.getDomains();

        // Assert
        assertEquals(expectedDomains, result);
        verify(domainPersistencePort, times(1)).getDomains();
    }

    @Test
    void testGetDomainControlsWithExistingDomain() {
        // Arrange
        Long domainId = 1L;
        List<Control> expectedControls = List.of(new Control.Builder().id(1L).name("Control1").build());
        when(domainPersistencePort.existsById(domainId)).thenReturn(true);
        when(domainPersistencePort.getDomainControls(domainId)).thenReturn(expectedControls);

        // Act
        List<Control> result = domainUseCase.getDomainControls(domainId);

        // Assert
        assertEquals(expectedControls, result);
        verify(domainPersistencePort, times(1)).existsById(domainId);
        verify(domainPersistencePort, times(1)).getDomainControls(domainId);
    }

    @Test
    void testGetDomainControlsWithNonExistingDomain() {
        // Arrange
        Long domainId = 1L;
        when(domainPersistencePort.existsById(domainId)).thenReturn(false);

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> domainUseCase.getDomainControls(domainId));

        assertEquals("Entity of type 'Domain' with id '1' not found", exception.getMessage());
        verify(domainPersistencePort, times(1)).existsById(domainId);
        verify(domainPersistencePort, never()).getDomainControls(domainId);
    }
}
