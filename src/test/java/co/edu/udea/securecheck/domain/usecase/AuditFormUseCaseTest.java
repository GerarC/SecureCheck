package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.exceptions.CompanyHasNotActiveAuditException;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.*;
import co.edu.udea.securecheck.domain.model.form.AuditForm;
import co.edu.udea.securecheck.domain.model.form.FormControl;
import co.edu.udea.securecheck.domain.model.form.FormDomain;
import co.edu.udea.securecheck.domain.spi.persistence.*;
import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditFormUseCaseTest {

    @Mock
    private AuditPersistencePort auditPersistencePort;
    @Mock
    private CompanyPersistencePort companyPersistencePort;
    @Mock
    private DomainPersistencePort domainPersistencePort;
    @Mock
    private AnswerPersistencePort answerPersistencePort;

    @InjectMocks
    private AuditFormUseCase auditFormUseCase;

    private String companyId;
    private Audit activeAudit;
    private Domain testDomain;
    private Control testControl;
    private Question testQuestion;
    private Answer testAnswer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        companyId = "company123";

        // Setup mock audit data
        activeAudit = Audit.builder()
                .id(1L)
                .startedAt(LocalDateTime.now())
                .state(AuditState.ACTIVE)
                .comment("Active audit for testing")
                .build();

        // Setup mock domain, control, and answer data
        testControl = Control.builder()
                .id(1L)
                .index(1)
                .name("Test Control")
                .description("Description of test control")
                .build();

        testDomain = Domain.builder()
                .id(1L)
                .index(1)
                .name("Test Domain")
                .description("Test domain description")
                .controls(Set.of(testControl))
                .build();

        testQuestion = Question.builder()
                .id(1L)
                .body("Is the control implemented?")
                .control(testControl)
                .build();

        testAnswer = Answer.builder()
                .id(1L)
                .done(false)
                .control(testControl)
                .audit(activeAudit)
                .build();
    }

    @Test
    void getCurrentForm_Success() {
        // Mock the expected methods
        when(companyPersistencePort.existsById(companyId)).thenReturn(true);
        when(auditPersistencePort.getActive(companyId)).thenReturn(activeAudit);
        when(domainPersistencePort.getDomains()).thenReturn(List.of(testDomain));
        when(companyPersistencePort.getCompanyQuestions(any())).thenReturn(List.of(testQuestion));
        when(answerPersistencePort.getByAuditId(activeAudit.getId())).thenReturn(List.of(testAnswer));

        // Run the method
        AuditForm result = auditFormUseCase.getCurrentForm(companyId);

        // Assertions
        assertNotNull(result);
        assertEquals(activeAudit.getId(), result.getId());
        assertEquals(AuditState.ACTIVE, result.getState());
        assertEquals(1, result.getDomains().size());

        FormDomain resultDomain = result.getDomains().get(0);
        assertEquals(testDomain.getId(), resultDomain.getId());
        assertEquals(testDomain.getName(), resultDomain.getName());
        assertEquals(1, resultDomain.getControls().size());

        FormControl resultControl = resultDomain.getControls().get(0);
        assertEquals(testControl.getId(), resultControl.getId());
        assertEquals(testControl.getName(), resultControl.getName());
        assertEquals(testAnswer, resultControl.getAnswer());
        assertEquals(1, resultControl.getQuestions().size());
    }

    @Test
    void getCurrentForm_CompanyNotFound_ThrowsEntityNotFoundException() {
        // Set up mocks
        when(companyPersistencePort.existsById(companyId)).thenReturn(false);

        // Assert that exception is thrown
        assertThrows(EntityNotFoundException.class, () -> auditFormUseCase.getCurrentForm(companyId));
    }

    @Test
    void getCurrentForm_NoActiveAudit_ThrowsCompanyHasNotActiveAuditException() {
        // Set up mocks
        when(companyPersistencePort.existsById(companyId)).thenReturn(true);
        when(auditPersistencePort.getActive(companyId)).thenReturn(null);

        // Assert that exception is thrown
        assertThrows(CompanyHasNotActiveAuditException.class, () -> auditFormUseCase.getCurrentForm(companyId));
    }
}
