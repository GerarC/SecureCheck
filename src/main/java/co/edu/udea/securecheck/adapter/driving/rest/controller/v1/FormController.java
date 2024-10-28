package co.edu.udea.securecheck.adapter.driving.rest.controller.v1;

import co.edu.udea.securecheck.adapter.driving.rest.dto.request.AuditRequest;
import co.edu.udea.securecheck.adapter.driving.rest.dto.response.form.AuditFormResponse;
import co.edu.udea.securecheck.adapter.driving.rest.service.FormService;
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

    @GetMapping
    public ResponseEntity<AuditFormResponse> getForm(@NotNull AuditRequest request) {
        return ResponseEntity.ok().body(
                formService.getAuditForm(request.getCompanyId())
        );
    }
}
