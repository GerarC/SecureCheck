package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import co.edu.udea.securecheck.domain.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotNull(message = Constants.EMPTY_NAME_FIELD_MESSAGE)
    private String name;

    @NotNull(message = Constants.EMPTY_LASTNAME_FIELD_MESSAGE)
    private String lastname;

    @Size(min = 6, max = 15, message = Constants.IDENTITY_DOCUMENT_OUT_OF_BOUNDS_MESSAGE)
    @Pattern(regexp = Constants.IDENTITY_DOCUMENT_REGEX, message = Constants.NOT_NUMERIC_IDENTITY_DOCUMENT_MESSAGE)
    @NotNull(message = Constants.EMPTY_IDENTITY_DOCUMENT_FIELD_MESSAGE)
    private String identityDocument;

    @NotNull(message = Constants.EMPTY_BIRTHDATE_FIELD_MESSAGE)
    @Past(message = Constants.FUTURE_BIRTH_DATE_MESSAGE)
    private LocalDateTime birthdate;

    @Size(min = 10, max = 14, message = Constants.PHONE_OUT_OF_BOUNDS_MESSAGE)
    @Pattern(regexp = Constants.PHONE_NUMBER_REGEX, message = Constants.NOT_VALID_PHONE_NUMBER_MESSAGE)
    @NotNull(message = Constants.EMPTY_PHONE_FIELD_MESSAGE)
    private String phone;

    @Size(min = 7, max = 255, message = Constants.EMAIL_OUT_OF_BOUNDS_MESSAGE)
    @Pattern(regexp = Constants.EMAIL_REGEX_RFC5322, message = Constants.NOT_VALID_EMAIL_MESSAGE)
    @NotNull(message = Constants.EMPTY_EMAIL_FIELD_MESSAGE)
    private String email;

    @Size(min = 7, max = 15, message = Constants.PASSWORD_OUT_OF_BOUNDS_MESSAGE)
    @NotNull(message = Constants.EMPTY_PASSWORD_FIELD_MESSAGE)
    private String password;
}
