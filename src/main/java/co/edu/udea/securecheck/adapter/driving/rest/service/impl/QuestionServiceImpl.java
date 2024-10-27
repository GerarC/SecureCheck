package co.edu.udea.securecheck.adapter.driving.rest.service.impl;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.QuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.UpdateQuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.request.QuestionRequestMapper;
import co.edu.udea.securecheck.adapter.driving.rest.mapper.response.QuestionResponseMapper;
import co.edu.udea.securecheck.adapter.driving.rest.service.QuestionService;
import co.edu.udea.securecheck.domain.api.QuestionServicePort;
import co.edu.udea.securecheck.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionServicePort questionServicePort;
    private final QuestionResponseMapper questionResponseMapper;
    private final QuestionRequestMapper questionRequestMapper;

    @Override
    public QuestionResponse createQuestion(QuestionRequest questionRequest) {
        Question question = questionRequestMapper.toDomain(questionRequest);
        return questionResponseMapper.toResponse(
                questionServicePort.save(question)
        );
    }

    @Override
    public QuestionResponse updateQuestion(Long id, UpdateQuestionRequest questionRequest) {
        Question question = questionRequestMapper.toDomain(questionRequest);
        return questionResponseMapper.toResponse(
                questionServicePort.update(id, question)
        );
    }

    @Override
    public QuestionResponse deleteQuestion(Long id) {
        return questionResponseMapper.toResponse(
                questionServicePort.delete(id)
        );
    }
}
