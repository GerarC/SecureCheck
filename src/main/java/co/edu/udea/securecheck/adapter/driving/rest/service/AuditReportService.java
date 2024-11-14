package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.report.AuditReportResponse;

public interface AuditReportService {
    AuditReportResponse getAuditReport(Long auditId);
}
