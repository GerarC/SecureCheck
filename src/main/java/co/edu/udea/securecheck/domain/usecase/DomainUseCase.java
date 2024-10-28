package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.DomainServicePort;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;
import co.edu.udea.securecheck.domain.spi.persistence.DomainPersistencePort;

import java.util.List;

public class DomainUseCase implements DomainServicePort {
    private final DomainPersistencePort domainPersistencePort;

    public DomainUseCase(DomainPersistencePort domainPersistencePort) {
        this.domainPersistencePort = domainPersistencePort;
    }

    @Override
    public List<Domain> getDomains() {
        return domainPersistencePort.getDomains();
    }

    @Override
    public List<Control> getDomainControls(Long id) {
        if(!domainPersistencePort.existsById(id)) throw new EntityNotFoundException(Domain.class.getSimpleName(), id.toString());
        return domainPersistencePort.getDomainControls(id);
    }
}
