package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.CompanyRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyQuestionFilterRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest companyRequest);
    CompanyResponse getCompany(String companyId);
    CompanyResponse deleteCompany(String companyId);
    List<QuestionResponse> getCompanyQuestions(String companyId, CompanyQuestionFilterRequest filterRequest);
}
