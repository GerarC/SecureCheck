package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Question;

import java.util.List;

public interface DefaultQuestionPersistencePort {
    List<Question> getAll();
}
