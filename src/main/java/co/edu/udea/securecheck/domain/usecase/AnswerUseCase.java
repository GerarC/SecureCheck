package co.edu.udea.securecheck.domain.usecase;

import co.edu.udea.securecheck.domain.api.AnswerServicePort;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.spi.persistence.AnswerPersistencePort;
import co.edu.udea.securecheck.domain.utils.StreamUtils;

import java.util.ArrayList;
import java.util.List;

public class AnswerUseCase implements AnswerServicePort {
    private final AnswerPersistencePort answerPersistencePort;

    public AnswerUseCase(AnswerPersistencePort answerPersistencePort) {
        this.answerPersistencePort = answerPersistencePort;
    }

    @Override
    public List<Answer> updateBatch(List<Answer> answers) {
        List<Answer> updatedAnswers = new ArrayList<>();
        List<Answer> foundAnswers = answerPersistencePort.getAnswersById(
                StreamUtils.map(answers, Answer::getId)
        );

        StreamUtils.zip(foundAnswers, answers).forEach((foundAnswer, answer) -> {
            foundAnswer.setOutcome(answer.getOutcome());
            foundAnswer.setComment(answer.getComment());
            updatedAnswers.add(foundAnswer);
        });

        return answerPersistencePort.saveBatch(updatedAnswers);
    }
}
