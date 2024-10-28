package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AuditEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.AuditEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.SortJPAMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.AuditRepository;
import co.edu.udea.securecheck.domain.exceptions.TypeAttributeDoesntExistsException;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.spi.persistence.AuditPersistencePort;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuditJpaAdapter implements AuditPersistencePort {
    private final AuditRepository auditRepository;
    private final AuditEntityMapper auditEntityMapper;
    private final SortJPAMapper sortJPAMapper;

    @Override
    public Audit createAudit(Audit audit) {
        AuditEntity auditEntity = auditEntityMapper.toEntity(audit);
        AuditEntity savedEntity = auditRepository.save(auditEntity);
        return auditEntityMapper.toBasicDomain(savedEntity);
    }

    @Override
    public Audit getActive(String companyId) {
        return auditEntityMapper.toBasicDomain(
                auditRepository.findFirstByCompanyIdAndState(companyId, AuditState.ACTIVE).orElse(null)
        );
    }

    @Override
    public List<Audit> getAudits(String companyId, SortQuery sortQuery) {
        Sort sort = sortQuery != null ? sortJPAMapper.toJPA(sortQuery).createSort() : Sort.unsorted();
        try {
            return auditEntityMapper.toDomains(
                    auditRepository.findAllByCompanyId(companyId, sort)
            );
        } catch (PropertyReferenceException e) {
            String column = sortQuery == null ? "" : sortQuery.getSortBy();
            throw new TypeAttributeDoesntExistsException(column, Control.class.getSimpleName());
        }
    }


    @Override
    public void deleteAudit(Long auditId) {
        // TODO: it's needed implement this method
    }

}
