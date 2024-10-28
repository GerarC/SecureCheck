package co.edu.udea.securecheck.adapter.driving.rest.mapper.response;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.PageResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Domain;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import co.edu.udea.securecheck.domain.utils.pagination.Pagination;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ControlResponseMapper {

    default Integer domainIndex(Domain domain) {
        return domain.getIndex();
    }

    ControlResponse toResponse(Control control);
    List<ControlResponse> toResponses(List<Control> controls);

    PageResponse<ControlResponse> toPageResponse(Pagination<Control> page);
}
