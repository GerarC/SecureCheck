package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;

public interface AuditService {
    BasicAuditResponse createAudit(String companyId);
    BasicAuditResponse deleteAudit(Long auditId);
}
