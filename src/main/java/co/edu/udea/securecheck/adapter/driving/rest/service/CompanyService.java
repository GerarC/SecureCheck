package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.CompanyRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest companyRequest);
    CompanyResponse getCompany(String companyId);
    CompanyResponse deleteCompany(String companyId);
}
