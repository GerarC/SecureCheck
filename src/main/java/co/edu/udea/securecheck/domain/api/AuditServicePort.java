package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.utils.SortQuery;

import java.util.List;

public interface AuditServicePort {
    Audit createAudit(String companyId);
    List<Audit> getAudits(String companyId, SortQuery sort);
    Audit deleteAudit(Long auditId);
}
