package co.edu.udea.securecheck.adapter.driven.jpa.mapper;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AnswerEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.ControlEntity;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.model.Control;
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
public interface AnswerEntityMapper {
    @Mapping(target = "audit", ignore = true)
    Answer toDomain(AnswerEntity domainEntity);

    List<Answer> toDomains(List<AnswerEntity> domainEntities);

    AnswerEntity toEntity(Answer domain);

    List<AnswerEntity> toEntities(List<Answer> domainEntities);

    default Control control(ControlEntity controlEntity) {
        return Control.builder()
                .id(controlEntity.getId())
                .name(controlEntity.getName())
                .description(controlEntity.getDescription())
                .index(controlEntity.getIndex())
                .build();
    }
}