package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CustomQuestionEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.CustomQuestionEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.CustomQuestionRepository;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.spi.persistence.CustomQuestionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomQuestionJpaAdapter implements CustomQuestionPersistencePort {
    private final CustomQuestionRepository customQuestionRepository;
    private final CustomQuestionEntityMapper customQuestionEntityMapper;

    @Override
    public Question save(Question question) {
        CustomQuestionEntity entity = customQuestionEntityMapper.toEntity(question);
        return customQuestionEntityMapper.toDomain(
                customQuestionRepository.save(entity)
        );
    }

    @Override
    public List<Question> saveAll(List<Question> questions) {
        List<CustomQuestionEntity> entities = customQuestionEntityMapper.toEntities(questions);
        return customQuestionEntityMapper.toDomains(
                customQuestionRepository.saveAll(entities)
        );
    }

    @Override
    public Question getQuestion(Long id) {
        return customQuestionEntityMapper.toDomain(
                customQuestionRepository.findById(id).orElse(null)
        );
    }

    @Override
    public Question update(Long id, Question question) {
        CustomQuestionEntity entity = customQuestionEntityMapper.toEntity(question);
        entity.setId(id);
        return customQuestionEntityMapper.toDomain(
                customQuestionRepository.save(entity)
        );
    }

    @Override
    public void delete(Long id) {
        customQuestionRepository.deleteById(id);
    }

    @Override
    public List<Question> getQuestionByControlIdAndCompanyId(String companyId, Long controlId){
        return customQuestionEntityMapper.toDomains(
                customQuestionRepository.findAllByCompanyIdAndControlId(companyId, controlId)
        );
    }

    @Override
    public List<Question> getAll() {
        return customQuestionEntityMapper.toDomains(
                customQuestionRepository.findAll()
        );
    }
}
