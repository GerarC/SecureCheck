package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.PageResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;

import java.util.Map;

public interface ControlService {
    PageResponse<ControlResponse> getControls(Map<String, String> query);
}
