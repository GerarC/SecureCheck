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
public class UpdateQuestionRequest {
    @NotNull(message = Constants.EMPTY_BODY_FIELD_MESSAGE)
    @Size(max = 255)
    private String body;
}
