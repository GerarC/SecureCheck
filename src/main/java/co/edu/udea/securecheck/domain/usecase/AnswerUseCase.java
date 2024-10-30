package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.AnswerServicePort;
import co.edu.udea.securecheck.domain.exceptions.EntityNotFoundException;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.spi.persistence.AnswerPersistencePort;

import java.util.List;

public class AnswerUseCase implements AnswerServicePort {
    private final AnswerPersistencePort answerPersistencePort;

    public AnswerUseCase(AnswerPersistencePort answerPersistencePort) {
        this.answerPersistencePort = answerPersistencePort;
    }

    @Override
    public List<Answer> updateBatch(List<Answer> answers) {
        answers.forEach(answer -> {
            if (!answerPersistencePort.existsById(answer.getId()))
                throw new EntityNotFoundException(Answer.class.getSimpleName(), answer.getId().toString());
        });
        return answerPersistencePort.saveBatch(answers);
    }
}
