package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import co.edu.udea.securecheck.domain.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotNull(message = Constants.EMPTY_EMAIL_FIELD_MESSAGE)
    @Pattern(regexp = Constants.EMAIL_REGEX_RFC5322, message = Constants.NOT_VALID_EMAIL_MESSAGE)
    private String email;

    @NotNull(message = Constants.EMPTY_PASSWORD_FIELD_MESSAGE)
    private String password;
}
