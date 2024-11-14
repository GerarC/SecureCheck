package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.report.AuditReport;

public interface ReportServicePort {
    AuditReport getAuditReport(Long auditId);
}
