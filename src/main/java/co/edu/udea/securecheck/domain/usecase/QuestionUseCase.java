package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.QuestionServicePort;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.exceptions.MaxQuestionReachedException;
import co.edu.udea.securecheck.domain.exceptions.MinQuestionReachedException;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Control;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.spi.persistence.CompanyPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.ControlPersistencePort;
import co.edu.udea.securecheck.domain.spi.persistence.CustomQuestionPersistencePort;

import static co.edu.udea.securecheck.domain.utils.validation.ValidationUtils.validateOrThrow;

public class QuestionUseCase implements QuestionServicePort {

    private final CustomQuestionPersistencePort customQuestionPersistencePort;
    private final ControlPersistencePort controlPersistencePort;
    private final CompanyPersistencePort companyPersistencePort;

    public QuestionUseCase(
            CustomQuestionPersistencePort customQuestionPersistencePort,
            ControlPersistencePort controlPersistencePort,
            CompanyPersistencePort companyPersistencePort
    ) {
        this.customQuestionPersistencePort = customQuestionPersistencePort;
        this.controlPersistencePort = controlPersistencePort;
        this.companyPersistencePort = companyPersistencePort;
    }

    @Override
    public Question save(Question question) {
        validateCanSaveQuestion(question);
        return customQuestionPersistencePort.save(question);
    }

    @Override
    public Question update(Long id, Question question) {
        Question foundQuestion = getQuestionById(id);
        if (question.getBody() != null) foundQuestion.setBody(question.getBody());
        return customQuestionPersistencePort.update(id, foundQuestion);
    }

    @Override
    public Question delete(Long id) {
        Question foundQuestion = getQuestionById(id);
        validateCanDeleteQuestion(foundQuestion);
        customQuestionPersistencePort.delete(id);
        return foundQuestion;
    }

    private void validateCanSaveQuestion(Question question) {
        validateOrThrow(controlPersistencePort.existsById(question.getControl().getId()),
                new EntityNotFoundException(Control.class.getSimpleName(), question.getControl().getId().toString())
        );
        validateOrThrow(companyPersistencePort.existsById(question.getCompany().getId()),
                new EntityNotFoundException(Company.class.getSimpleName(), question.getCompany().getId())
        );
        validateOrThrow(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(
                        question.getCompany().getId(),
                        question.getControl().getId()
                ).size() < 3,
                new MaxQuestionReachedException()
        );
    }

    private void validateCanDeleteQuestion(Question question) {
        validateOrThrow(customQuestionPersistencePort.getQuestionByControlIdAndCompanyId(
                        question.getCompany().getId(),
                        question.getControl().getId()).size() > 1,
                new MinQuestionReachedException()
        );
    }

    private Question getQuestionById(Long id) {
        Question foundQuestion = customQuestionPersistencePort.getQuestion(id);
        validateOrThrow(foundQuestion != null,
                new EntityNotFoundException(Question.class.getSimpleName(), id.toString())
        );
        return foundQuestion;
    }
}
