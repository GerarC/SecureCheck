package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPostRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.form.AuditFormResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.FormService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/forms")
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @Operation(summary = RestConstants.SWAGGER_GET_AUDIT_FORM_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_AUDIT_FORM_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = AuditFormResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_COMPANY_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT,
                    description = RestConstants.SWAGGER_GET_AUDIT_FORM_NOT_ACTIVE_AUDIT,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping
    public ResponseEntity<AuditFormResponse> getForm(@NotNull AuditPostRequest request) {
        return ResponseEntity.ok().body(
                formService.getAuditForm(request.getCompanyId())
        );
    }
}
