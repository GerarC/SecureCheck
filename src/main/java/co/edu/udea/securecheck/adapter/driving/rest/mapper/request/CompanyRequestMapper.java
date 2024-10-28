package co.edu.udea.securecheck.adapter.driving.rest.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.CompanyRequest;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyRequestMapper {
    default User stringToUser(String userId) {
        return User.builder().id(userId).build();
    }

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "contactEmail", source = "email")
    @Mapping(target = "contactPhone", source = "phone")
    Company toDomain(CompanyRequest companyRequest);

    List<Company> toDomains(List<CompanyRequest> companyRequests);
}
