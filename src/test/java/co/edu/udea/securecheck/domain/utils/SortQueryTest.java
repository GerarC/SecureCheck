package co.edu.udea.securecheck.domain.utils;
import co.edu.udea.securecheck.domain.exceptions.CompanyAlreadyHasActiveAuditException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.*;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.usecase.AuditUseCase;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditUseCaseTest {

    @Mock
    private AuditPersistencePort auditPersistencePort;
    @Mock
    private CompanyPersistencePort companyPersistencePort;
    @Mock
    private ControlPersistencePort controlPersistencePort;
    @Mock
    private AnswerPersistencePort answerPersistencePort;

    @InjectMocks
    private AuditUseCase auditUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAudit_ShouldCreateNewAudit() {
        // Arrange
        String companyId = "123";
        Company company = Company.builder().id(companyId).build();
        Control control = Control.builder().id(1L).build();
        Audit expectedAudit = Audit.builder()
                .company(company)
                .startedAt(LocalDateTime.now())
                .state(AuditState.ACTIVE)
                .build();

        when(companyPersistencePort.getCompany(companyId)).thenReturn(company);
        when(companyPersistencePort.existsById(companyId)).thenReturn(true);
        when(auditPersistencePort.getActive(companyId)).thenReturn(null); // No active audit
        when(controlPersistencePort.getAllControls()).thenReturn(List.of(control));
        when(auditPersistencePort.createAudit(any(Audit.class))).thenReturn(expectedAudit);

        // Act
        Audit audit = auditUseCase.createAudit(companyId);

        // Assert
        assertNotNull(audit);
        assertEquals(AuditState.ACTIVE, audit.getState());
        verify(answerPersistencePort).saveBatch(anyList());
    }

    @Test
    void createAudit_ShouldThrowCompanyAlreadyHasActiveAuditException() {
        // Arrange
        String companyId = "123";
        when(companyPersistencePort.existsById(companyId)).thenReturn(true);
        when(auditPersistencePort.getActive(companyId)).thenReturn(Audit.builder().build()); // Active audit exists

        // Act & Assert
        assertThrows(CompanyAlreadyHasActiveAuditException.class, () -> auditUseCase.createAudit(companyId));
    }

    @Test
    void createAudit_ShouldThrowEntityNotFoundException_WhenCompanyDoesNotExist() {
        // Arrange
        String companyId = "123";
        when(companyPersistencePort.existsById(companyId)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> auditUseCase.createAudit(companyId));
    }

    @Test
    void getAudits_ShouldReturnAuditList() {
        // Arrange
        String companyId = "123";
        SortQuery sortQuery = new SortQuery("startedAt", true);
        Audit audit = Audit.builder().company(Company.builder().id(companyId).build()).build();
        when(auditPersistencePort.getAudits(any(), any())).thenReturn(List.of(audit));

        // Act
        List<Audit> audits = auditUseCase.getAudits(companyId, sortQuery);

        // Assert
        assertNotNull(audits);
        assertEquals(0, audits.size());
    }
}
