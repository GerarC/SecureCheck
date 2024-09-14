package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.CompanyRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.CompanyService;
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
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Operation(summary = RestConstants.SWAGGER_CREATE_COMPANY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED,
                    description = RestConstants.SWAGGER_CREATE_COMPANY_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_USER_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_FAILED,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(companyService.createCompany(companyRequest));
    }

    @Operation(summary = RestConstants.SWAGGER_GET_COMPANY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_COMPANY_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_GET_COMPANY_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(
                companyService.getCompany(id)
        );
    }

    @Operation(summary = RestConstants.SWAGGER_DELETE_COMPANY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_DELETE_COMPANY_SUCCESSFUL,
                    content = @Content(schema = @Schema(implementation = CompanyResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_GET_COMPANY_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyResponse> deleteCompany(@PathVariable String id){
        return ResponseEntity.ok(
                companyService.deleteCompany(id)
        );
    }

}
