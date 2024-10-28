package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.DomainEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.exceptions.NotDomainVerifiedException;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.DomainEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.DomainRepository;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;
import co.edu.udea.securecheck.domain.spi.persistence.DomainPersistencePort;
import co.edu.udea.securecheck.domain.utils.StreamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainJpaAdapter implements DomainPersistencePort {

    private final DomainRepository domainRepository;
    private final DomainEntityMapper domainEntityMapper;

    @Override
    public List<Domain> getDomains() {
        return domainEntityMapper.toDomains(domainRepository.findAll());
    }

    @Override
    public List<Control> getDomainControls(Long id) {
        DomainEntity domainEntity = domainRepository.findById(id).orElse(null);
        if(domainEntity == null) throw new NotDomainVerifiedException();
        Domain domain = domainEntityMapper.toDomain(domainEntity);
        return StreamUtils.map(domain.getControls(), control -> {
            control.setDomain(domain);
            return control;
        });
    }

    @Override
    public boolean existsById(Long id) {
        return domainRepository.existsById(id);
    }
}
