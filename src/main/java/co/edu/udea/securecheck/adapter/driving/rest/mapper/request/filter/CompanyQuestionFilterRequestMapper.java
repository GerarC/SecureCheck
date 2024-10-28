package co.edu.udea.securecheck.adapter.driving.rest.mapper.request.filter;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyQuestionFilterRequest;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyQuestionFilterRequestMapper {
    QuestionFilter toDomain(CompanyQuestionFilterRequest companyRequest);
}
