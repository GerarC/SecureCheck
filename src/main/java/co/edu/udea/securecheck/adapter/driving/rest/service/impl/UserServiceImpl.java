package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.SortQueryRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyFilterRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.SortQueryRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.filter.CompanyFilterRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.CompanyResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.UserService;
import co.edu.udea.securecheck.domain.api.UserServicePort;
import co.edu.udea.securecheck.domain.utils.SortQuery;
import co.edu.udea.securecheck.domain.utils.filters.CompanyFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServicePort userServicePort;
    private final CompanyResponseMapper companyResponseMapper;
    private final SortQueryRequestMapper sortQueryRequestMapper;
    private final CompanyFilterRequestMapper companyFilterRequestMapper;

    @Override
    public List<CompanyResponse> getUserCompanies(String id, SortQueryRequest sortRequest, CompanyFilterRequest filterRequest) {
        SortQuery sort = sortQueryRequestMapper.toDomain(sortRequest);
        CompanyFilter filter = companyFilterRequestMapper.toDomain(filterRequest);
        return companyResponseMapper.toResponses(
                userServicePort.getUserCompanies(id, sort, filter)
        );
    }
}
