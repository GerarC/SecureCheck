package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.model.User;
import co.edu.udea.securecheck.domain.spi.persistence.CompanyPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.CustomQuestionPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.DefaultQuestionPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.UserPersistencePort;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyUseCaseTest {

    @Mock
    private CompanyPersistencePort companyPersistencePort;

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private DefaultQuestionPersistencePort defaultQuestionPersistencePort;

    @Mock
    private CustomQuestionPersistencePort customQuestionPersistencePort;

    @InjectMocks
    private CompanyUseCase companyUseCase;

    private Company mockCompany;
    private User mockUser;
    private Question mockQuestion;

    @BeforeEach
    public void setup() {
        mockUser = User.builder()
                .id("user123")
                .name("Test")
                .lastname("User")
                .email("testuser@example.com")
                .build();

        mockCompany = Company.builder()
                .id("company123")
                .name("Test Company")
                .user(mockUser)
                .build();

        mockQuestion = Question.builder()
                .id(1L)
                .body("Sample question")
                .company(mockCompany)
                .build();
    }

    @Test
    void testCreateCompany_Success() {
        when(userPersistencePort.existsById(mockUser.getId())).thenReturn(true);
        when(companyPersistencePort.createCompany(mockCompany)).thenReturn(mockCompany);
        when(defaultQuestionPersistencePort.getAll()).thenReturn(Collections.singletonList(mockQuestion));

        Company createdCompany = companyUseCase.createCompany(mockCompany);

        assertNotNull(createdCompany);
        assertEquals(mockCompany.getId(), createdCompany.getId());
        verify(customQuestionPersistencePort, times(1)).saveAll(anyList());
    }

    @Test
    void testCreateCompany_ThrowsEntityNotFoundExceptionForUser() {
        when(userPersistencePort.existsById(mockUser.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> companyUseCase.createCompany(mockCompany));
        verify(companyPersistencePort, never()).createCompany(any());
        verify(customQuestionPersistencePort, never()).saveAll(anyList());
    }

    @Test
    void testGetCompany_Success() {
        when(companyPersistencePort.getCompany(mockCompany.getId())).thenReturn(mockCompany);

        Company foundCompany = companyUseCase.getCompany(mockCompany.getId());

        assertNotNull(foundCompany);
        assertEquals(mockCompany.getId(), foundCompany.getId());
        verify(companyPersistencePort, times(1)).getCompany(mockCompany.getId());
    }

    @Test
    void testGetCompany_ThrowsEntityNotFoundException() {
        when(companyPersistencePort.getCompany(mockCompany.getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> companyUseCase.getCompany(mockCompany.getId()));
        verify(companyPersistencePort, times(1)).getCompany(mockCompany.getId());
    }

    @Test
    void testDeleteCompany_Success() {
        when(companyPersistencePort.getCompany(mockCompany.getId())).thenReturn(mockCompany);

        Company deletedCompany = companyUseCase.deleteCompany(mockCompany.getId());

        assertNotNull(deletedCompany);
        assertEquals(mockCompany.getId(), deletedCompany.getId());
        verify(companyPersistencePort, times(1)).deleteCompany(mockCompany.getId());
    }

    @Test
    void testDeleteCompany_ThrowsEntityNotFoundException() {
        when(companyPersistencePort.getCompany(mockCompany.getId())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> companyUseCase.deleteCompany(mockCompany.getId()));
        verify(companyPersistencePort, never()).deleteCompany(anyString());
    }

    @Test
    void testGetCompanyQuestions_Success() {
        QuestionFilter filter = QuestionFilter.builder().companyId(mockCompany.getId()).build();
        when(companyPersistencePort.existsById(mockCompany.getId())).thenReturn(true);
        when(companyPersistencePort.getCompanyQuestions(filter)).thenReturn(Collections.singletonList(mockQuestion));

        List<Question> questions = companyUseCase.getCompanyQuestions(mockCompany.getId(), filter);

        assertNotNull(questions);
        assertEquals(1, questions.size());
        verify(companyPersistencePort, times(1)).getCompanyQuestions(filter);
    }

    @Test
    void testGetCompanyQuestions_ThrowsEntityNotFoundException() {
        QuestionFilter filter = QuestionFilter.builder().companyId(mockCompany.getId()).build();
        when(companyPersistencePort.existsById(mockCompany.getId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> companyUseCase.getCompanyQuestions(mockCompany.getId(), filter));
        verify(companyPersistencePort, never()).getCompanyQuestions(filter);
    }
}
