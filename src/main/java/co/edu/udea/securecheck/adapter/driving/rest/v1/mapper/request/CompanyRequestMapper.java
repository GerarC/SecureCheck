package co.edu.udea.securecheck.adapter.driving.rest.v1.mapper.request;

import co.edu.udea.securecheck.adapter.driving.rest.v1.dto.request.CompanyRequest;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.utils.Generated;
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
        return new User(userId, null, null, null, null, null, null, null, null, null);
    }

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "contactEmail", source = "email")
    @Mapping(target = "contactPhone", source = "phone")
    Company toDomain(CompanyRequest companyRequest);

    List<Company> toDomains(List<CompanyRequest> companyRequests);
}
