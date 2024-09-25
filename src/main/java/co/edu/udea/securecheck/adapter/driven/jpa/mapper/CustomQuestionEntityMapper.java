package co.edu.udea.securecheck.adapter.driven.jpa.mapper;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CompanyEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.ControlEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.CustomQuestionEntity;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Question;
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
public interface CustomQuestionEntityMapper {
    @Mapping(target = "domain", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Control control(ControlEntity controlEntity);

    default Company company(CompanyEntity companyEntity){
        return Company.builder()
                .id(companyEntity.getId())
                .nit(companyEntity.getNit())
                .name(companyEntity.getName())
                .nick(companyEntity.getNick())
                .address(companyEntity.getAddress())
                .contactEmail(companyEntity.getContactEmail())
                .contactPhone(companyEntity.getContactPhone())
                .createdAt(companyEntity.getCreatedAt())
                .build();
    }

    Question toDomain(CustomQuestionEntity questionEntity);
    List<Question> toDomains(List<CustomQuestionEntity> questionEntities);

    CustomQuestionEntity toEntity(Question question);
    List<CustomQuestionEntity> toEntities(List<Question> questions);
}