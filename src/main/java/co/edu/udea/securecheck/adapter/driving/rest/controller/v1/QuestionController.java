package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.QuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.question.UpdateQuestionRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.QuestionService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.securecheck.configuration.advisor.responses.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @Operation(summary = RestConstants.SWAGGER_CREATE_QUESTION_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED,
                    description = RestConstants.SWAGGER_CREATE_QUESTION_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = QuestionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_CONTROL_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_COMPANY_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_FAILED,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody @Valid QuestionRequest questionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                questionService.createQuestion(questionRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_UPDATE_QUESTION_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_UPDATE_QUESTION_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = QuestionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_QUESTION_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_FAILED,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Long id, @RequestBody @Valid UpdateQuestionRequest questionRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                questionService.updateQuestion(id, questionRequest)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_DELETE_QUESTION_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_DELETE_QUESTION_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = QuestionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_QUESTION_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionResponse> deleteQuestion(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                questionService.deleteQuestion(id)
        );
    }
}
