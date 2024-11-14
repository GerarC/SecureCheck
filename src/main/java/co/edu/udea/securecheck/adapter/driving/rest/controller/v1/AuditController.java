package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPatchRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit.AuditPostRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.AuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.BasicAuditResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.RegisterResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.report.AuditReportResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.AuditReportService;
import co.edu.udea.securecheck.adapter.driving.rest.service.AuditService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.securecheck.configuration.advisor.responses.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/audits")
@RequiredArgsConstructor
public class AuditController {
    private final AuditService auditService;
    private final AuditReportService auditReportService;

    @Operation(summary = RestConstants.SWAGGER_CREATE_AUDIT_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED,
                    description = RestConstants.SWAGGER_CREATE_AUDIT_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_CREATE_AUDIT_COMPANY_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_CONFLICT,
                    description = RestConstants.SWAGGER_CREATE_AUDIT_COMPANY_ALREADY_HAS_AN_ACTIVE_ONE,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_FAILED,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping
    public ResponseEntity<BasicAuditResponse> createAudit(@RequestBody AuditPostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                auditService.createAudit(request.getCompanyId())
        );
    }

    // TODO: Swagger documentation
    @PatchMapping("/{id}")
    public ResponseEntity<AuditResponse> patchAudit(@PathVariable Long id, @RequestBody AuditPatchRequest request) {
        return ResponseEntity.ok(
                auditService.patchAudit(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuditResponse> deleteAudit(@PathVariable Long id) {
        return ResponseEntity.ok(
                auditService.deleteAudit(id)
        );
    }

    @PatchMapping("/{id}/state/finished")
    public ResponseEntity<AuditResponse> finishAudit(@PathVariable Long id) {
        return ResponseEntity.ok(
                auditService.setAsFinished(id)
        );
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<AuditReportResponse> getAuditReport(@PathVariable Long id){
        return ResponseEntity.ok(
                auditReportService.getAuditReport(id)
        );
    }

}
