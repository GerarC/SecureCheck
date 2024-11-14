package co.edu.udea.securecheck.adapter.driven.jpa.mapper;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CompanyEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.UserEntity;
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
public interface CompanyEntityMapper {
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "audits", ignore = true)
    Company toDomain(CompanyEntity companyEntity);
    List<Company> toDomains(List<CompanyEntity> companyEntities);
    CompanyEntity toEntity(Company company);

    default User user(UserEntity user){
        return User.builder().id(user.getId()).build();
    }
}
