package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerResponse {
    private Long id;
    private ControlOutcome outcome;
    private String comment;
}
