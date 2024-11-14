package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Audit;

public interface AuditServicePort {
    Audit createAudit(String companyId);
    Audit deleteAudit(Long auditId);
    Audit updateAudit(Long id, Audit audit);
    Audit setAsFinished(Long auditId);
}
