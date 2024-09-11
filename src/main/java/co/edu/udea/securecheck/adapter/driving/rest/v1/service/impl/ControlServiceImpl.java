package co.edu.udea.securecheck.adapter.driving.rest.v1.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.v1.dto.response.ControlResponse;
import co.edu.udea.securecheck.adapter.driving.rest.v1.dto.response.PageResponse;
import co.edu.udea.securecheck.adapter.driving.rest.v1.mapper.response.ControlResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.v1.service.ControlService;
import co.edu.udea.securecheck.domain.api.ControlServicePort;
import co.edu.udea.securecheck.domain.utils.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ControlServiceImpl implements ControlService {
    private final ControlServicePort controlServicePort;
    private final ControlResponseMapper controlResponseMapper;

    @Override
    public PageResponse<ControlResponse> getControls(Map<String, String> query) {
        return controlResponseMapper.toPageResponse(
                controlServicePort.getControls(
                        new PageQuery(query)
                )
        );
    }
}
