package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.report.AuditReportResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.AuditReportResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.AuditReportService;
import co.edu.udea.securecheck.domain.api.ReportServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditReportServiceImpl implements AuditReportService {
    private final ReportServicePort reportServicePort;
    private final AuditReportResponseMapper auditReportResponseMapper;


    @Override
    public AuditReportResponse getAuditReport(Long auditId) {
        return auditReportResponseMapper.toResponse(
                reportServicePort.getAuditReport(auditId)
        );
    }
}
