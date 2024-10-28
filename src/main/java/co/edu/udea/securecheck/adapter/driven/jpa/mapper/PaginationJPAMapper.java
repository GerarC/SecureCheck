package co.edu.udea.securecheck.adapter.driven.jpa.mapper;


import co.edu.udea.securecheck.adapter.driven.jpa.utils.PaginationJPA;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import co.edu.udea.securecheck.domain.utils.pagination.PageQuery;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationJPAMapper {
    PaginationJPA toJPA(PageQuery pageQuery);
}
