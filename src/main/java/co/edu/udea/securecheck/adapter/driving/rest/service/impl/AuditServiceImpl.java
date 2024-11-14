package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPatchRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.AuditRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.AuditResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.AuditService;
import co.edu.udea.securecheck.domain.api.AuditServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditServicePort auditServicePort;
    private final AuditResponseMapper auditResponseMapper;
    private final AuditRequestMapper auditRequestMapper;

    @Override
    public BasicAuditResponse createAudit(String companyId) {
        return auditResponseMapper.toBasicResponse(
                auditServicePort.createAudit(companyId)
        );
    }

    @Override
    public AuditResponse deleteAudit(Long auditId) {
        return auditResponseMapper.toResponse(
                auditServicePort.deleteAudit(auditId)
        );
    }

    @Override
    public AuditResponse patchAudit(Long id, AuditPatchRequest request) {
        return auditResponseMapper.toResponse(
                auditServicePort.updateAudit(id,
                        auditRequestMapper.toDomain(request)
                )
        );
    }

    @Override
    public AuditResponse setAsFinished(Long auditId) {
        return auditResponseMapper.toResponse(
                auditServicePort.setAsFinished(auditId)
        );
    }
}
