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
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.StreamUtils;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;

import java.time.LocalDateTime;
import java.util.List;

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

        Audit audit = auditPersistencePort.createAudit(newAudit);
        List<Answer> answers = StreamUtils.map(controls,
                control -> Answer.builder()
                        .audit(audit)
                        .control(control)
                        .done(false)
                        .build()
        );

        answerPersistencePort.saveBatch(answers);
        return audit;
    }

    @Override
    public List<Audit> getAudits(String companyId, SortQuery sort) {
        return List.of();
    }

    @Override
    public Audit deleteAudit(Long auditId) {
        return null;
    }

    private void validateCanCreateNewAudit(String companyId) {
        if (!companyPersistencePort.existsById(companyId))
            throw new EntityNotFoundException(Company.class.getSimpleName(), companyId);
        else if (auditPersistencePort.getActive(companyId) != null)
            throw new CompanyAlreadyHasActiveAuditException(companyId);
    }
}
