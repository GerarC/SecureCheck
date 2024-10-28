package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.PageResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.ControlResponseMapper;
import co.edu.udea.securecheck.domain.api.ControlServicePort;
import co.edu.udea.securecheck.domain.model.Control;
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

class ControlServiceImplTest {
    @Mock
    ControlServicePort controlServicePort;

    @Mock
    ControlResponseMapper controlResponseMapper;

    @InjectMocks
    ControlServiceImpl controlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getControls() {
        Pagination<Control> controls = new Pagination<>(1, 1, 1, 1, List.of(
                new Control(1L, 5, "name", "description", null)
        ));
        PageResponse<ControlResponse> controlResponses = new PageResponse<>(1, 1, 1, 1, List.of(
                new ControlResponse(1L, 5, 5, "name", "description")
        ));
        Map<String,String> params = new HashMap<>();
        params.put("page", "1");
        params.put("size", "1");

        when(controlResponseMapper.toPageResponse(any())).thenReturn(controlResponses);
        when(controlServicePort.getControls(any())).thenReturn(controls);
        PageResponse<ControlResponse> response = controlService.getControls(params);
        verify(controlServicePort).getControls(any());
        assertEquals(controlResponses, response);
    }
}