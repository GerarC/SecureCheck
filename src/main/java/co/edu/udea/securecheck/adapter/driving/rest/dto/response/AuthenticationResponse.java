package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
    private RoleName role;
    private String id;
}
