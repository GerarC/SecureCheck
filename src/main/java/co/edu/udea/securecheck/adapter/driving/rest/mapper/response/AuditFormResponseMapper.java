package co.edu.udea.securecheck.adapter.driving.rest.mapper.response;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.form.AuditFormResponse;
import co.edu.udea.securecheck.domain.model.form.AuditForm;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditFormResponseMapper {
    AuditFormResponse toResponse(AuditForm auditForm);
}
