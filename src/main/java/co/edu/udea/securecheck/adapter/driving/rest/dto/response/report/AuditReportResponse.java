package co.edu.udea.securecheck.adapter.driving.rest.dto.response.report;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuditReportResponse {
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String comment;
    private String objective;
    private String scope;
    private ControlStatisticResponse auditStatistic;
    private List<ReportDomainResponse> domains;
    private UserReportResponse auditor;
    private CompanyResponse company;
}
