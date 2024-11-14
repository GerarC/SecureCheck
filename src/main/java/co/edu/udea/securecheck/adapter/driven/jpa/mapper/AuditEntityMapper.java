package co.edu.udea.securecheck.adapter.driven.jpa.mapper;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AnswerEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.AuditEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.ControlEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.entity.CompanyEntity;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import org.mapstruct.*;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuditEntityMapper {
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "endedAt", ignore = true)
    @Named("basicAuditDomain")
    Audit toBasicDomain(AuditEntity auditEntity);

    Audit toDomain(AuditEntity audit);

    default Company company(CompanyEntity entity) {
        return Company.builder()
            .id(entity.getId())
            .build();
    }

    List<Audit> toDomains(List<AuditEntity> auditEntities);

    AuditEntity toEntity(Audit audit);

    default Answer answer(AnswerEntity answerEntity) {
        ControlEntity control = answerEntity.getControl();
        return Answer.builder()
                .id(answerEntity.getId())
                .control(Control.builder()
                        .id(control.getId())
                        .index(control.getIndex())
                        .name(control.getName())
                        .description(control.getDescription())
                        .build())
                .outcome(answerEntity.getOutcome())
                .comment(answerEntity.getComment())
                .build();
    }
}
