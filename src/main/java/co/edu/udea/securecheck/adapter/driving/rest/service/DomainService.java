package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.DomainResponse;

import java.util.List;

public interface DomainService {
    List<DomainResponse> getDomains();
    List<ControlResponse> getDomainControls(Long id);
}
