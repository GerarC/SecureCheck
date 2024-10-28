package co.edu.udea.securecheck.domain.api.usecase;

import co.edu.udea.securecheck.domain.model.Domain;
import co.edu.udea.securecheck.domain.spi.persistence.DomainPersistencePort;
import co.edu.udea.securecheck.domain.usecase.DomainUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void getDomains() {
        List<Domain> domains = List.of(
                new Domain(1L, 5, "name", "description", null)
        );
        when(domainPersistencePort.getDomains()).thenReturn(domains);
        List<Domain> result = domainUseCase.getDomains();
        verify(domainPersistencePort).getDomains();
        assertEquals(domains, result);
    }

    @Test
    void getDomainControls() {
    }
}