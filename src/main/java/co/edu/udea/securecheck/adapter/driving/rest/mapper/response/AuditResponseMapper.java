package co.edu.udea.securecheck.adapter.driving.rest.mapper.response;


import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditResponseMapper {
    BasicAuditResponse toBasicResponse(Audit audit);
    AuditResponse toResponse(Audit audit);
    List<AuditResponse> toResponses(List<Audit> audits);
}
