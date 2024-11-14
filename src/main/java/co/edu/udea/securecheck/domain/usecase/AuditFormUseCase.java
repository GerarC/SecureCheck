package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.AuditFormServicePort;
import co.edu.udea.securecheck.domain.exceptions.CompanyHasNotActiveAuditException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.*;
import co.edu.udea.securecheck.domain.model.form.AuditForm;
import co.edu.udea.securecheck.domain.model.form.FormDomain;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;

import java.util.List;

import static co.edu.udea.securecheck.domain.utils.ControlUtils.mapAnsweredControl;
import static co.edu.udea.securecheck.domain.utils.validation.ValidationUtils.validateOrThrow;

public class AuditFormUseCase implements AuditFormServicePort {
    private final AuditPersistencePort auditPersistencePort;
    private final CompanyPersistencePort companyPersistencePort;
    private final DomainPersistencePort domainPersistencePort;
    private final AnswerPersistencePort answerPersistencePort;

    public AuditFormUseCase(
            AuditPersistencePort auditPersistencePort,
            CompanyPersistencePort companyPersistencePort,
            DomainPersistencePort domainPersistencePort,
            AnswerPersistencePort answerPersistencePort
    ) {
        this.auditPersistencePort = auditPersistencePort;
        this.companyPersistencePort = companyPersistencePort;
        this.domainPersistencePort = domainPersistencePort;
        this.answerPersistencePort = answerPersistencePort;
    }

    @Override
    public AuditForm getCurrentForm(String companyId) {
        validateCanGetForm(companyId);
        Audit audit = auditPersistencePort.getActive(companyId);
        List<Domain> domains = domainPersistencePort.getDomains();
        List<Question> questions = getQuestions(companyId);
        List<Answer> answers = answerPersistencePort.getByAuditId(audit.getId());
        List<FormDomain> formDomains = domains.stream()
                .map(domain -> FormDomain.builder()
                        .id(domain.getId())
                        .name(domain.getName())
                        .description(domain.getDescription())
                        .index(domain.getIndex())
                        .controls(mapAnsweredControl(
                                domain.getControls(),
                                questions,
                                answers))
                        .build()).toList();

        return AuditForm.builder()
                .id(audit.getId())
                .startedAt(audit.getStartedAt())
                .state(audit.getState())
                .comment(audit.getComment())
                .objective(audit.getObjective())
                .scope(audit.getScope())
                .domains(formDomains)
                .build();
    }

    private void validateCanGetForm(String companyId) {
        validateOrThrow(companyPersistencePort.existsById(companyId),
                new EntityNotFoundException(Company.class.getSimpleName(), companyId)
        );
        validateOrThrow(auditPersistencePort.getActive(companyId) != null,
                new CompanyHasNotActiveAuditException(companyId)
        );
    }

    private List<Question> getQuestions(String companyId) {
        QuestionFilter filter = QuestionFilter.builder()
                .companyId(companyId)
                .build();
        return companyPersistencePort.getCompanyQuestions(filter);
    }

}
