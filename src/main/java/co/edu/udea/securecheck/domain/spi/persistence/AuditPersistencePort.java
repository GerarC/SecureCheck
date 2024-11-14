package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Audit;

public interface AuditPersistencePort {
    Audit saveAudit(Audit audit);
    Audit getAudit(Long auditId);
    Audit getActive(String companyId);
    void deleteAudit(Long auditId);
}
