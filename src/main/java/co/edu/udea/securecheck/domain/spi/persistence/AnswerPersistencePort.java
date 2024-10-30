package co.edu.udea.securecheck.domain.spi.persistence;

import co.edu.udea.securecheck.domain.model.Answer;

import java.util.List;

public interface AnswerPersistencePort {
    boolean existsById(Long id);
    List<Answer> saveBatch(List<Answer> answers);
    List<Answer> getByAuditId(Long auditId);
}
