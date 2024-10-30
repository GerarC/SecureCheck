package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AnswerUpdateRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AnswerResponse;

import java.util.List;

public interface AnswerService {
    List<AnswerResponse> updateBatch(List<AnswerUpdateRequest> answers);
}
