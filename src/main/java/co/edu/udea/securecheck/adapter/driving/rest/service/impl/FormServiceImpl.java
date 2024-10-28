package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.form.AuditFormResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.AuditFormResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.FormService;
import co.edu.udea.securecheck.domain.api.AuditFormServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final AuditFormServicePort auditFormServicePort;
    private final AuditFormResponseMapper auditFormResponseMapper;

    @Override
    public AuditFormResponse getAuditForm(String companyId) {
        return auditFormResponseMapper.toResponse(
                auditFormServicePort.getCurrentForm(companyId)
        );
    }
}
