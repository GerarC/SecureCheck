package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.DomainResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.DomainResponseMapper;
import co.edu.udea.securecheck.domain.api.DomainServicePort;
import co.edu.udea.securecheck.domain.model.Domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DomainServiceImplTest {

    @Mock
    private DomainServicePort domainServicePort;

    @Mock
    private DomainResponseMapper domainResponseMapper;

    @InjectMocks
    private DomainServiceImpl domainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDomains() {
        List<Domain> domains = List.of(
                new Domain(1L, 5, "name", "description", null)
        );
        List<DomainResponse> domainResponses = List.of(
                new DomainResponse(1L, 5, "name", "description")
        );
        when(domainServicePort.getDomains()).thenReturn(domains);
        when(domainResponseMapper.toResponses(domains)).thenReturn(domainResponses);
        List<DomainResponse> responses = domainService.getDomains();
        verify(domainServicePort).getDomains();
        assertEquals(domainResponses, responses);
    }

    @Test
    void getDomainControls() {
    }
}