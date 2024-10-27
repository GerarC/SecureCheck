package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Question;

import java.util.List;

public interface CustomQuestionPersistencePort {
    List<Question> getAll();
    Question save(Question question);
    List<Question> saveAll(List<Question> questions);
    Question getQuestion(Long id);
    Question update(Long id, Question question);
    void delete(Long id);
}
