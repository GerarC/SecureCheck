package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;

import java.util.List;

public interface DomainServicePort {
    List<Domain> getDomains();
    List<Control> getDomainControls(Long id);
}
