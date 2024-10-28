package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.ControlEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.ControlEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.PaginationJPAMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.ControlRepository;
import co.edu.udea.securecheck.adapter.driven.jpa.utils.PaginationJPA;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ControlJpaAdapterTest {

    @Mock
    ControlRepository controlRepository;

    @Mock
    ControlEntityMapper controlEntityMapper;

    @Mock
    PaginationJPAMapper paginationJPAMapper;

    @InjectMocks
    ControlJpaAdapter controlJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getControls() {
        Pagination<Control> controls = new Pagination<>(1, 1, 1, 1, List.of(
                new Control(1L, 5, "name", "description", null)
        ));
        Page<ControlEntity> controlEntities = new PageImpl<>(List.of(
                new ControlEntity(1L, 5, "name", "description", null)
        ));
        PaginationJPA paginationJPA = new PaginationJPA(1, 1, null, false);
        when(paginationJPAMapper.toJPA(any())).thenReturn(paginationJPA);
        when(controlEntityMapper.toPagination(controlEntities)).thenReturn(controls);
        when(controlRepository.findAll(any(Pageable.class))).thenReturn(controlEntities);
        Pagination<Control> returnedControl = controlJpaAdapter.getControls(new PageQuery(new HashMap<>()));
        verify(controlRepository).findAll(any(Pageable.class));
        assertEquals(returnedControl, controls);
    }
}