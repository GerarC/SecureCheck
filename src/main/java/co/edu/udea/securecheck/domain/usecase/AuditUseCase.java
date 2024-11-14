package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.AuditServicePort;
import co.edu.udea.securecheck.domain.exceptions.CompanyAlreadyHasActiveAuditException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.model.Audit;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.spi.persistence.AnswerPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.AuditPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.CompanyPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.utils.StreamUtils;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;

import java.time.LocalDateTime;
import java.util.List;

import static co.edu.udea.securecheck.domain.utils.validation.ValidationUtils.validateOrThrow;

public class AuditUseCase implements AuditServicePort {
    private final AuditPersistencePort auditPersistencePort;
    private final CompanyPersistencePort companyPersistencePort;
    private final ControlPersistencePort controlPersistencePort;
    private final AnswerPersistencePort answerPersistencePort;

    public AuditUseCase(
            AuditPersistencePort auditPersistencePort,
            CompanyPersistencePort companyPersistencePort,
            ControlPersistencePort controlPersistencePort,
            AnswerPersistencePort answerPersistencePort
    ) {
        this.auditPersistencePort = auditPersistencePort;
        this.companyPersistencePort = companyPersistencePort;
        this.controlPersistencePort = controlPersistencePort;
        this.answerPersistencePort = answerPersistencePort;
    }

    @Override
    public Audit createAudit(String companyId) {
        validateCanCreateNewAudit(companyId);
        Company company = companyPersistencePort.getCompany(companyId);
        List<Control> controls = controlPersistencePort.getAllControls();

        Audit newAudit = Audit.builder()
                .company(company)
                .startedAt(LocalDateTime.now())
                .state(AuditState.ACTIVE)
                .build();

        Audit audit = auditPersistencePort.saveAudit(newAudit);
        List<Answer> answers = StreamUtils.map(controls,
                control -> Answer.builder()
                        .audit(audit)
                        .control(control)
                        .outcome(ControlOutcome.NOT_APPLICABLE)
                        .build()
        );

        answerPersistencePort.saveBatch(answers);
        return audit;
    }

    @Override
    public Audit deleteAudit(Long auditId) {
        Audit audit = getAudit(auditId);
        auditPersistencePort.deleteAudit(auditId);
        return audit;
    }

    @Override
    public Audit updateAudit(Long id, Audit audit) {
        Audit foundAudit = getAudit(id);

        if (audit.getComment() != null) foundAudit.setComment(audit.getComment());
        if (audit.getScope() != null) foundAudit.setScope(audit.getScope());
        if (audit.getObjective() != null) foundAudit.setObjective(audit.getObjective());

        return auditPersistencePort.saveAudit(foundAudit);
    }

    @Override
    public Audit setAsFinished(Long auditId) {
        Audit foundAudit = getAudit(auditId);
        foundAudit.setState(AuditState.FINALIZED);
        foundAudit.setEndedAt(LocalDateTime.now());
        return auditPersistencePort.saveAudit(foundAudit);
    }

    private void validateCanCreateNewAudit(String companyId) {
        validateOrThrow(companyPersistencePort.existsById(companyId),
                new EntityNotFoundException(Company.class.getSimpleName(), companyId)
        );
        // TODO: It seems that it creates various Active Audits - Verify this
        validateOrThrow(auditPersistencePort.getActive(companyId) == null,
                new CompanyAlreadyHasActiveAuditException(companyId)
        );
    }

    private Audit getAudit(Long auditId) {
        Audit audit = auditPersistencePort.getAudit(auditId);
        validateOrThrow(audit != null,
                new EntityNotFoundException(Audit.class.getSimpleName(), auditId.toString()));
        return audit;
    }

}
