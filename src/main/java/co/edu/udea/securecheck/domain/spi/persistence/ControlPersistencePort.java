package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;

import java.util.List;

public interface ControlPersistencePort {
    Pagination<Control> getControls(PageQuery pageQuery);
    List<Control> getAllControls();
    boolean existsById(Long id);
}
