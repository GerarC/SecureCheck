package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.mapper.DefaultQuestionEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.DefaultQuestionRepository;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.spi.persistence.DefaultQuestionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultQuestionJpaAdapter implements DefaultQuestionPersistencePort {
    private final DefaultQuestionRepository repository;
    private final DefaultQuestionEntityMapper mapper;

    @Override
    public List<Question> getAll() {
        return mapper.toDomains(
                repository.findAll()
        );
    }
}
