package co.edu.udea.securecheck.adapter.driving.rest.dto.request.question;

import co.edu.udea.securecheck.domain.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionRequest {
    @NotNull(message = Constants.EMPTY_BODY_FIELD_MESSAGE)
    @Size(max = 255)
    private String body;

    @NotNull(message = Constants.EMPTY_CONTROL_ID_FIELD_MESSAGE)
    private Long controlId;

    @NotNull(message = Constants.EMPTY_COMPANY_ID_FIELD_MESSAGE)
    private String companyId;
}
