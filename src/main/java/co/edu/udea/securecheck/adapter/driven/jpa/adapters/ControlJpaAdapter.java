package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.mapper.ControlEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.PaginationJPAMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.ControlRepository;
import co.edu.udea.securecheck.domain.exceptions.TypeAttributeDoesntExistsException;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ControlJpaAdapter implements ControlPersistencePort {
    private final ControlRepository controlRepository;
    private final ControlEntityMapper controlEntityMapper;
    private final PaginationJPAMapper paginationJPAMapper;

    @Override
    public Pagination<Control> getControls(PageQuery pageQuery) {
        Pageable pageable = paginationJPAMapper
                .toJPA(pageQuery)
                .createPageable();
        try {
            return controlEntityMapper.toPagination(controlRepository.findAll(pageable));
        } catch (PropertyReferenceException e) {
            throw new TypeAttributeDoesntExistsException(pageQuery.getSortBy(), Control.class.getSimpleName());
        }
    }

    @Override
    public List<Control> getAllControls() {
        return controlEntityMapper.toDomains(
                controlRepository.findAll()
        );
    }

    @Override
    public boolean existsById(Long id) {
        return controlRepository.existsById(id);
    }
}
