package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Answer;

import java.util.List;

public interface AnswerServicePort {
    List<Answer> updateBatch(List<Answer> answers);
}
