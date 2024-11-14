package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPatchRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;

public interface AuditService {
    BasicAuditResponse createAudit(String companyId);
    AuditResponse deleteAudit(Long auditId);
    AuditResponse patchAudit(Long id, AuditPatchRequest request);
    AuditResponse setAsFinished(Long auditId);
}
