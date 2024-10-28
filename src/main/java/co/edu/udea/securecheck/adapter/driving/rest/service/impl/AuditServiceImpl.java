package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;
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

    @Override
    public BasicAuditResponse createAudit(String companyId) {
        return auditResponseMapper.toBasicResponse(
                auditServicePort.createAudit(companyId)
        );
    }

    @Override
    public BasicAuditResponse deleteAudit(Long auditId) {
        return null;
    }
}
