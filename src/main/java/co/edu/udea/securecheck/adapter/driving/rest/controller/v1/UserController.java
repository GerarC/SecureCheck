package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.SortQueryRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter.CompanyFilterRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.CompanyResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.UserService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = RestConstants.SWAGGER_GET_USER_COMPANIES_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_USER_COMPANIES_SUCCESSFUL,
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ControlResponse.class)))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_USER_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping("/{id}/companies")
    public ResponseEntity<List<CompanyResponse>> getCompanies(
            @PathVariable String id,
            @Parameter(name = "sort",
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = SortQueryRequest.class),
                    style = ParameterStyle.FORM)
            @Nullable SortQueryRequest sortQueryRequest,
            @Parameter(name = "filter",
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = CompanyFilterRequest.class),
                    style = ParameterStyle.FORM)
            @Nullable CompanyFilterRequest companyFilterRequest) {
        return ResponseEntity.ok(userService.getUserCompanies(id, sortQueryRequest, companyFilterRequest));
    }
}
