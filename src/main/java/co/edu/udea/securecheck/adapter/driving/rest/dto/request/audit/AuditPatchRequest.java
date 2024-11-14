package co.edu.udea.securecheck.adapter.driving.rest.dto.request.audit;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuditPatchRequest {
    private String comment;
    private String objective;
    private String scope;
}
