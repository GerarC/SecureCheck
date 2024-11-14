package co.edu.udea.securecheck.adapter.driving.rest.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPatchRequest;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditRequestMapper {
    Audit toDomain(AuditPatchRequest request);
}
