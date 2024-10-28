package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.SortQueryRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyFilterRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;

import java.util.List;

public interface UserService {
    List<CompanyResponse> getUserCompanies(String id, SortQueryRequest sort, CompanyFilterRequest filter);
}
