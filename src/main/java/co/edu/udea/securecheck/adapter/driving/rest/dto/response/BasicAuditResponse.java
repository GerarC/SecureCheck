package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BasicAuditResponse {
    private Long id;
    private LocalDateTime startedAt;
    private String comment;
    private String objective;
    private String scope;
    private AuditState state;
}
