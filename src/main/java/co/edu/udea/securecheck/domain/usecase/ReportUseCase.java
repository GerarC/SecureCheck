package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.ReportServicePort;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.*;
import co.edu.udea.securecheck.domain.model.report.AuditReport;
import co.edu.udea.securecheck.domain.model.report.ControlStatistic;
import co.edu.udea.securecheck.domain.model.report.ReportDomain;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static co.edu.udea.securecheck.domain.utils.ControlUtils.mapAnsweredControl;
import static co.edu.udea.securecheck.domain.utils.validation.ValidationUtils.validateOrThrow;

public class ReportUseCase implements ReportServicePort {
    private final AuditPersistencePort auditPersistencePort;
    private final DomainPersistencePort domainPersistencePort;
    private final AnswerPersistencePort answerPersistencePort;
    private final CompanyPersistencePort companyPersistencePort;
    private final UserPersistencePort userPersistencePort;


    public ReportUseCase(
            AuditPersistencePort auditPersistencePort,
            DomainPersistencePort domainPersistencePort,
            AnswerPersistencePort answerPersistencePort,
            CompanyPersistencePort companyPersistencePort, UserPersistencePort userPersistencePort
    ) {
        this.auditPersistencePort = auditPersistencePort;
        this.domainPersistencePort = domainPersistencePort;
        this.answerPersistencePort = answerPersistencePort;
        this.companyPersistencePort = companyPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public AuditReport getAuditReport(Long auditId) {
        Audit audit = auditPersistencePort.getAudit(auditId);
        validateOrThrow(audit != null,
                new EntityNotFoundException(Audit.class.getSimpleName(), auditId.toString()));
        assert audit != null;


        List<Domain> domains = domainPersistencePort.getDomains();
        List<Question> questions = companyPersistencePort.getCompanyQuestions(QuestionFilter.builder().companyId(audit.getCompany().getId()).build());
        List<Answer> answers = answerPersistencePort.getByAuditId(audit.getId());
        List<ReportDomain> reportDomains = domains.stream().map(
                domain -> reportDomainBuilder(domain, questions, answers)
        ).toList();

        AtomicReference<Integer> conformed = new AtomicReference<>(0);
        AtomicReference<Integer> nonConformed = new AtomicReference<>(0);
        AtomicReference<Integer> notApplicable = new AtomicReference<>(0);
        reportDomains.forEach(domain -> {
            conformed.getAndSet(conformed.get() + domain.getDomainStatistic().getConformingControls());
            nonConformed.getAndSet(nonConformed.get() + domain.getDomainStatistic().getNonConformingControls());
            notApplicable.getAndSet(notApplicable.get() + domain.getDomainStatistic().getNotApplicableControls());
        });
        Company company = companyPersistencePort.getCompany(audit.getCompany().getId());
        User user = userPersistencePort.getUser(company.getUser().getId());

        ControlStatistic reportStats = ControlStatistic.builder()
                .conformingControls(conformed.get())
                .nonConformingControls(nonConformed.get())
                .notApplicableControls(notApplicable.get())
                .build();

        return AuditReport.builder()
                .id(auditId)
                .comment(audit.getComment())
                .objective(audit.getObjective())
                .scope(audit.getScope())
                .startedAt(audit.getStartedAt())
                .endedAt(audit.getEndedAt())
                .auditStatistic(reportStats)
                .domains(reportDomains)
                .company(company)
                .auditor(user)
                .build();
    }

    private ReportDomain reportDomainBuilder(Domain domain, List<Question> questions, List<Answer> answers) {
        ReportDomain reportDomain = ReportDomain.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .index(domain.getIndex())
                .controls(mapAnsweredControl(
                        domain.getControls(),
                        questions,
                        answers))
                .build();
        AtomicReference<Integer> conformed = new AtomicReference<>(0);
        AtomicReference<Integer> nonConformed = new AtomicReference<>(0);
        AtomicReference<Integer> notApplicable = new AtomicReference<>(0);
        reportDomain.getControls().forEach(control -> {
            if (control.getAnswer().getOutcome() == ControlOutcome.CONFORMING) conformed.getAndSet(conformed.get() + 1);
            else if (control.getAnswer().getOutcome() == ControlOutcome.NONCONFORMING)
                nonConformed.getAndSet(nonConformed.get() + 1);
            else if (control.getAnswer().getOutcome() == ControlOutcome.NOT_APPLICABLE)
                notApplicable.getAndSet(notApplicable.get() + 1);
        });
        reportDomain.setDomainStatistic(ControlStatistic.builder()
                .conformingControls(conformed.get())
                .nonConformingControls(nonConformed.get())
                .notApplicableControls(notApplicable.get())
                .build());
        return reportDomain;
    }
}
