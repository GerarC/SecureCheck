package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.CompanyRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyQuestionFilterRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.CompanyRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.filter.CompanyQuestionFilterRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.CompanyResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.QuestionResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.CompanyService;
import co.edu.udea.securecheck.domain.api.CompanyServicePort;
import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyServicePort companyServicePort;
    private final CompanyRequestMapper companyRequestMapper;
    private final CompanyResponseMapper companyResponseMapper;
    private final QuestionResponseMapper questionResponseMapper;
    private final CompanyQuestionFilterRequestMapper companyQuestionFilterRequestMapper;

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        Company company = companyRequestMapper.toDomain(companyRequest);
        return companyResponseMapper.toResponse(
                companyServicePort.createCompany(company)
        );
    }

    @Override
    public CompanyResponse getCompany(String companyId) {
        return companyResponseMapper.toResponse(
                companyServicePort.getCompany(companyId)
        );
    }

    @Override
    public CompanyResponse deleteCompany(String companyId) {
        return companyResponseMapper.toResponse(
                companyServicePort.deleteCompany(companyId)
        );
    }

    @Override
    public List<QuestionResponse> getCompanyQuestions(String companyId, CompanyQuestionFilterRequest filterRequest) {
        QuestionFilter filter = companyQuestionFilterRequestMapper.toDomain(filterRequest);
        return questionResponseMapper.toResponses(
                companyServicePort.getCompanyQuestions(companyId, filter)
        );
    }
}
