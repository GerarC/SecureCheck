package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerUpdateRequest {
    private Long id;
    private ControlOutcome outcome;
    private String comment;
}
