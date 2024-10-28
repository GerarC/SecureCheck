package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.AnswerEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.AnswerEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.AnswerRepository;
import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.spi.persistence.AnswerPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerJpaAdapter implements AnswerPersistencePort {
    private final AnswerRepository answerRepository;
    private final AnswerEntityMapper answerEntityMapper;

    @Override
    public List<Answer> saveBatch(List<Answer> answers) {
        List<AnswerEntity> answerEntities = answerEntityMapper.toEntities(answers);
        List<AnswerEntity> savedEntities = answerRepository.saveAll(answerEntities);
        return answerEntityMapper.toDomains(savedEntities);
    }

    @Override
    public List<Answer> getByAuditId(Long auditId) {
        return answerEntityMapper.toDomains(
                answerRepository.findAllByAuditId(auditId)
        );
    }
}
