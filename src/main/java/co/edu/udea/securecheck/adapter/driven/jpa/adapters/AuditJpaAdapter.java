package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.mapper.AuditEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.AnswerRepository;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.AuditRepository;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.spi.persistence.AuditPersistencePort;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditJpaAdapter implements AuditPersistencePort {
    private final AuditRepository auditRepository;
    private final AnswerRepository answerRepository;
    private final AuditEntityMapper auditEntityMapper;

    @Override
    public Audit saveAudit(Audit audit) {
        return auditEntityMapper.toDomain(
                auditRepository.save(
                        auditEntityMapper.toEntity(audit)
                )
        );
    }

    @Override
    public Audit getAudit(Long auditId) {
        return auditEntityMapper.toDomain(
                auditRepository.findById(auditId).orElse(null)
        );
    }

    @Override
    public Audit getActive(String companyId) {
        return auditEntityMapper.toDomain(
                auditRepository.findFirstByCompanyIdAndState(companyId, AuditState.ACTIVE).orElse(null)
        );
    }

    @Override
    public void deleteAudit(Long auditId) {
        auditRepository.findById(auditId)
                .ifPresent(audit -> answerRepository.deleteAll(audit.getAnswers()));
        auditRepository.deleteById(auditId);
    }
}
