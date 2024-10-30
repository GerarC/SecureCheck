package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AnswerUpdateRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AnswerResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.AnswerRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.AnswerResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.AnswerService;
import co.edu.udea.securecheck.domain.api.AnswerServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerServicePort answerServicePort;
    private final AnswerRequestMapper answerRequestMapper;
    private final AnswerResponseMapper answerResponseMapper;

    @Override
    public List<AnswerResponse> updateBatch(List<AnswerUpdateRequest> answers) {
        return answerResponseMapper.toResponses(
                answerServicePort.updateBatch(
                        answerRequestMapper.toDomains(answers)
                )
        );
    }
}
