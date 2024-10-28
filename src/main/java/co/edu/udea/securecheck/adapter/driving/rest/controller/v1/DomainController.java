package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.ControlResponse;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.DomainResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.DomainService;
import co.edu.udea.securecheck.adapter.driving.rest.utils.RestConstants;
import co.edu.udea.securecheck.configuration.advisor.responses.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/domains")
@RequiredArgsConstructor
public class DomainController {

    private final DomainService domainService;

    @Operation(summary = RestConstants.SWAGGER_GET_ALL_DOMAIN_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_ALL_DOMAIN_SUCCESSFUL,
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DomainResponse.class)))),
    })
    @GetMapping
    public ResponseEntity<List<DomainResponse>> getDomains(){
        return ResponseEntity.ok(domainService.getDomains());
    }

    @Operation(summary = RestConstants.SWAGGER_GET_DOMAIN_CONTROLS_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_GET_DOMAIN_CONTROLS_SUCCESSFUL,
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ControlResponse.class)))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_DOMAIN_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @GetMapping("/{id}/controls")
    public ResponseEntity<List<ControlResponse>> getDomainControls(@PathVariable Long id){
        return ResponseEntity.ok(
                domainService.getDomainControls(id)
        );
    }
}
