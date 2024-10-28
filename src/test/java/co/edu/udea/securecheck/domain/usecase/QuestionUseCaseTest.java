package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.exceptions.MaxQuestionReachedException;
import co.edu.udea.securecheck.domain.exceptions.MinQuestionReachedException;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.spi.persistence.CompanyPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.CustomQuestionPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionUseCaseTest {

    @Mock
    private CustomQuestionPersistencePort customQuestionPersistencePort;

    @Mock
    private ControlPersistencePort controlPersistencePort;

    @Mock
    private CompanyPersistencePort companyPersistencePort;

    @InjectMocks
    private QuestionUseCase questionUseCase;

    private Company mockCompany;
    private Control mockControl;
    private Question mockQuestion;

    @BeforeEach
    public void setup() {
        mockCompany = Company.builder().id("1").name("Test Company").nit("TC").build();
        mockControl = Control.builder().id(1L).name("Control1").build();
        mockQuestion = Question.builder().id(1L).body("Question Body").control(mockControl).company(mockCompany).build();
    }

    @Test
    void testSaveQuestion_Success() {
        when(controlPersistencePort.existsById(mockControl.getId())).thenReturn(true);
        when(companyPersistencePort.existsById(mockCompany.getId())).thenReturn(true);
        when(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(mockCompany.getId(), mockControl.getId()))
                .thenReturn(Collections.singletonList(mockQuestion));

        when(customQuestionPersistencePort.save(mockQuestion)).thenReturn(mockQuestion);

        Question savedQuestion = questionUseCase.save(mockQuestion);

        assertEquals(mockQuestion, savedQuestion);
        verify(customQuestionPersistencePort, times(1)).save(mockQuestion);
    }

    @Test
    void testSaveQuestion_ThrowsMaxQuestionReachedException() {
        when(controlPersistencePort.existsById(mockControl.getId())).thenReturn(true);
        when(companyPersistencePort.existsById(mockCompany.getId())).thenReturn(true);
        when(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(mockCompany.getId(), mockControl.getId()))
                .thenReturn(Collections.nCopies(3, mockQuestion));

        assertThrows(MaxQuestionReachedException.class, () -> questionUseCase.save(mockQuestion));
        verify(customQuestionPersistencePort, never()).save(mockQuestion);
    }

    @Test
    void testSaveQuestion_ThrowsEntityNotFoundException() {
        when(controlPersistencePort.existsById(mockControl.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> questionUseCase.save(mockQuestion));
        verify(customQuestionPersistencePort, never()).save(mockQuestion);
    }

    @Test
    void testUpdateQuestion_Success() {
        when(customQuestionPersistencePort.getQuestion(mockQuestion.getId())).thenReturn(mockQuestion);
        when(customQuestionPersistencePort.update(mockQuestion.getId(), mockQuestion)).thenReturn(mockQuestion);

        Question updatedQuestion = questionUseCase.update(mockQuestion.getId(), mockQuestion);

        assertEquals(mockQuestion.getBody(), updatedQuestion.getBody());
        verify(customQuestionPersistencePort, times(1)).update(mockQuestion.getId(), mockQuestion);
    }

    @Test
    void testUpdateQuestion_ThrowsEntityNotFoundException() {
        when(customQuestionPersistencePort.getQuestion(mockQuestion.getId())).thenReturn(null);

        try {
            questionUseCase.update(mockQuestion.getId(), mockQuestion);
        } catch (EntityNotFoundException e) {
            assertEquals("Entity of type 'Question' with id '1' not found", e.getMessage());
        }
        verify(customQuestionPersistencePort, never()).update(anyLong(), any());
    }

    @Test
    void testDeleteQuestion_Success() {
        when(customQuestionPersistencePort.getQuestion(mockQuestion.getId())).thenReturn(mockQuestion);
        when(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(mockCompany.getId(), mockControl.getId()))
                .thenReturn(Collections.nCopies(2, mockQuestion));

        Question deletedQuestion = questionUseCase.delete(mockQuestion.getId());

        assertEquals(mockQuestion, deletedQuestion);
        verify(customQuestionPersistencePort, times(1)).delete(mockQuestion.getId());
    }

    @Test
    void testDeleteQuestion_ThrowsMinQuestionReachedException() {
        when(customQuestionPersistencePort.getQuestion(mockQuestion.getId())).thenReturn(mockQuestion);
        when(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(mockCompany.getId(), mockControl.getId()))
                .thenReturn(Collections.singletonList(mockQuestion));

        assertThrows(MinQuestionReachedException.class, () -> questionUseCase.delete(mockQuestion.getId())); // NOSONAR
        verify(customQuestionPersistencePort, never()).delete(anyLong());
    }

    @Test
    void testDeleteQuestion_ThrowsEntityNotFoundException() {
        when(customQuestionPersistencePort.getQuestion(mockQuestion.getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> questionUseCase.delete(mockQuestion.getId()));  // NOSONAR
        verify(customQuestionPersistencePort, never()).delete(anyLong());
    }
}
