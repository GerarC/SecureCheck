package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.AuditFormServicePort;
import co.edu.udea.securecheck.domain.exceptions.CompanyHasNotActiveAuditException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.*;
import co.edu.udea.securecheck.domain.model.form.AuditForm;
import co.edu.udea.securecheck.domain.model.form.FormControl;
import co.edu.udea.securecheck.domain.model.form.FormDomain;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.utils.StreamUtils;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;

import java.util.List;

public class AuditFormUseCase implements AuditFormServicePort {
    private final AuditPersistencePort auditPersistencePort;
    private final CompanyPersistencePort companyPersistencePort;
    private final DomainPersistencePort domainPersistencePort;
    private final ControlPersistencePort controlPersistencePort;
    private final AnswerPersistencePort answerPersistencePort;

    public AuditFormUseCase(
            AuditPersistencePort auditPersistencePort,
            CompanyPersistencePort companyPersistencePort,
            DomainPersistencePort domainPersistencePort,
            ControlPersistencePort controlPersistencePort,
            AnswerPersistencePort answerPersistencePort
    ) {
        this.auditPersistencePort = auditPersistencePort;
        this.companyPersistencePort = companyPersistencePort;
        this.domainPersistencePort = domainPersistencePort;
        this.controlPersistencePort = controlPersistencePort;
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
                        .controls(mapFormControl(
                                domain.getControls().stream().toList(),
                                questions,
                                answers))
                        .build()).toList();

        return AuditForm.builder()
                .id(audit.getId())
                .startedAt(audit.getStartedAt())
                .state(audit.getState())
                .comment(audit.getComment())
                .domains(formDomains)
                .build();
    }

    private void validateCanGetForm(String companyId) {
        if (!companyPersistencePort.existsById(companyId))
            throw new EntityNotFoundException(Company.class.getSimpleName(), companyId);
        else if (auditPersistencePort.getActive(companyId) == null)
            throw new CompanyHasNotActiveAuditException(companyId);
    }

    private List<Question> getQuestions(String companyId) {
        QuestionFilter filter = QuestionFilter.builder()
                .companyId(companyId)
                .build();
        return companyPersistencePort.getCompanyQuestions(filter);
    }

    private List<FormControl> mapFormControl(List<Control> controls, List<Question> questions, List<Answer> answers) {
        return StreamUtils.map(controls,
                control -> FormControl.builder()
                        .id(control.getId())
                        .answer(StreamUtils.findAny(
                                answers,
                                answer -> answer.getControl().getId().equals(control.getId()),
                                new EntityNotFoundException(
                                        Question.class.getSimpleName(),
                                        control.getId().toString())))
                        .description(control.getDescription())
                        .name(control.getName())
                        .index(control.getIndex())
                        .questions(questions.stream()
                                .filter(question -> question.getControl().getId().equals(control.getId())).toList())
                        .build()
        );

    }
}
