package co.edu.udea.securecheck.domain.api.usecase;

import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.usecase.ControlUseCase;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void getControls() {
        Pagination<Control> controls = new Pagination<>(1, 1, 1, 1, List.of(
                new Control(1L, 5, "name", "description", null)
        ));
        Map<String,String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "1");
        PageQuery query = new PageQuery(params);
        when(controlUseCase.getControls(query)).thenReturn(controls);
        Pagination<Control> result = controlUseCase.getControls(query);
        verify(controlPersistencePort).getControls(any());
        assertEquals(controls, result);
    }
}