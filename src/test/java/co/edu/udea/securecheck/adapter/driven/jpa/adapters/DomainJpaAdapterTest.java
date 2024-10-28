package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.DomainEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.DomainEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.DomainRepository;
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

class DomainJpaAdapterTest {

    @Mock
    private DomainRepository domainRepository;

    @Mock
    private DomainEntityMapper domainEntityMapper;

    @InjectMocks
    private DomainJpaAdapter domainJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDomains() {
        List<Domain> domains = List.of(
                new Domain(1L, 5, "name", "description", null)
        );
        List<DomainEntity> domainEntities = List.of(
                new DomainEntity(1L, 5, "name", "description", null)
        );

        when(domainEntityMapper.toDomains(domainEntities)).thenReturn(domains);
        when(domainRepository.findAll()).thenReturn(domainEntities);
        List<Domain> result = domainJpaAdapter.getDomains();
        verify(domainRepository).findAll();
        assertEquals(domains.size(), result.size());
        assertEquals(domains.get(0).getId(), result.get(0).getId());

    }

    @Test
    void getDomainControls() {
    }

    @Test
    void existsById() {
    }
}