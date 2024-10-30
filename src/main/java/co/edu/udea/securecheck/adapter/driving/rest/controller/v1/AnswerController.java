package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AnswerUpdateRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AnswerResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.RegisterResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.AnswerService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = RestConstants.SWAGGER_UPDATE_ANSWERS_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_UPDATE_ANSWERS_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_FAILED,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PatchMapping
    public ResponseEntity<List<AnswerResponse>> updateBatch(@RequestBody @Valid List<AnswerUpdateRequest> requests){
        return ResponseEntity.ok(answerService.updateBatch(requests));
    }
}
