package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;

import java.util.List;

public interface DomainPersistencePort {
    List<Domain> getDomains();
    List<Control> getDomainControls(Long id);
    boolean existsById(Long id);
}
