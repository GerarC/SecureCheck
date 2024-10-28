package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControlUseCaseTest {

    @Mock
    private ControlPersistencePort controlPersistencePort;

    @InjectMocks
    private ControlUseCase controlUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetControls() {
        // Arrange
        PageQuery pageQuery = new PageQuery(null); // or create with specific params
        Pagination<Control> expectedPagination = new Pagination<>(
                1, 10, 100, 10, Collections.emptyList()
        );

        when(controlPersistencePort.getControls(pageQuery)).thenReturn(expectedPagination);

        // Act
        Pagination<Control> result = controlUseCase.getControls(pageQuery);

        // Assert
        assertEquals(expectedPagination, result);
        verify(controlPersistencePort, times(1)).getControls(pageQuery);
    }
}
