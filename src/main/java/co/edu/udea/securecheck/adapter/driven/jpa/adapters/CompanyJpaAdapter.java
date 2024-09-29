package co.edu.udea.securecheck.adapter.driven.jpa.adapters;

import co.edu.udea.securecheck.adapter.driven.jpa.entity.CompanyEntity;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.CompanyEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.mapper.CustomQuestionEntityMapper;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.CompanyRepository;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.spi.CompanyPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyJpaAdapter implements CompanyPersistencePort {
    private final CompanyRepository companyRepository;
    private final CompanyEntityMapper companyEntityMapper;
    private final CustomQuestionEntityMapper customQuestionEntityMapper;

    @Override
    @Transactional
    public Company createCompany(Company company) {
        CompanyEntity entity = companyEntityMapper.toEntity(company);
        return companyEntityMapper.toDomain(
                companyRepository.save(entity)
        );
    }

    @Override
    public Company getCompany(String id) {
        return companyEntityMapper.toDomain(
                companyRepository.findById(id).orElse(null)
        );
    }

    @Override
    public boolean existsById(String id) {
        return companyRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Question> getCompanyQuestions(String id) {
        CompanyEntity entity = companyRepository.findById(id).orElse(null);
        if (entity == null) return List.of();
        return customQuestionEntityMapper.toDomainWithoutExtraData(
                entity.getQuestions().stream().toList()
        );
    }
}
