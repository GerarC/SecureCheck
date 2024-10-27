package co.edu.udea.securecheck.adapter.driving.rest.service;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.QuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.UpdateQuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;

public interface QuestionService {
    QuestionResponse createQuestion(QuestionRequest questionRequest);
    QuestionResponse updateQuestion(Long id, UpdateQuestionRequest questionRequest);
    QuestionResponse deleteQuestion(Long id);
}
