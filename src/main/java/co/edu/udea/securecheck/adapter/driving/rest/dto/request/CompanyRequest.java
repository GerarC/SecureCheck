package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import co.edu.udea.securecheck.domain.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {
    @NotNull(message = Constants.EMPTY_NIT_FIELD_MESSAGE)
    private String nit;

    @NotNull(message = Constants.EMPTY_NAME_FIELD_MESSAGE)
    private String name;

    @NotNull(message = Constants.EMPTY_ADDRESS_FIELD_MESSAGE)
    private String address;

    @Size(min = 7, max = 255, message = Constants.EMAIL_OUT_OF_BOUNDS_MESSAGE)
    @Pattern(regexp = Constants.EMAIL_REGEX_RFC5322, message = Constants.NOT_VALID_EMAIL_MESSAGE)
    @NotNull(message = Constants.EMPTY_EMAIL_FIELD_MESSAGE)
    private String email;

    @Size(min = 10, max = 14, message = Constants.PHONE_OUT_OF_BOUNDS_MESSAGE)
    @Pattern(regexp = Constants.PHONE_NUMBER_REGEX, message = Constants.NOT_VALID_PHONE_NUMBER_MESSAGE)
    @NotNull(message = Constants.EMPTY_PHONE_FIELD_MESSAGE)
    private String phone;

    @NotNull(message = Constants.EMPTY_USER_ID_FIELD_MESSAGE)
    private String userId;
}
