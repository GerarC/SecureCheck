package co.edu.udea.securecheck.adapter.driving.rest.mapper.response;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.report.AuditReportResponse;
import co.edu.udea.securecheck.domain.model.report.AuditReport;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditReportResponseMapper {
    AuditReportResponse toResponse(AuditReport auditReport);
}
