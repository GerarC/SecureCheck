package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuditResponse {
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String comment;
    private String objective;
    private String scope;
    private AuditState state;
}
