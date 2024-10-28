package co.edu.udea.securecheck.domain.api;

import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.Question;
import co.edu.udea.securecheck.domain.utils.filters.QuestionFilter;

import java.util.List;

public interface CompanyServicePort {
    Company createCompany(Company company);
    Company getCompany(String companyId);
    Company deleteCompany(String companyId);
    List<Question> getCompanyQuestions(String companyId, QuestionFilter filter);
}
