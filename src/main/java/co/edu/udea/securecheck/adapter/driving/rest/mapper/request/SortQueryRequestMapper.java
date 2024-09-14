package co.edu.udea.securecheck.adapter.driving.rest.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.SortQueryRequest;
import co.edu.udea.securecheck.domain.utils.Generated;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SortQueryRequestMapper {
    SortQuery toDomain(SortQueryRequest sortQueryRequest);
}
