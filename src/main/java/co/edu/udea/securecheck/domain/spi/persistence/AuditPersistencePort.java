package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.utils.SortQuery;

import java.util.List;

public interface AuditPersistencePort {
    Audit createAudit(Audit audit);
    Audit getActive(String companyId);
    List<Audit> getAudits(String companyId, SortQuery sort);
    void deleteAudit(Long auditId);
}
