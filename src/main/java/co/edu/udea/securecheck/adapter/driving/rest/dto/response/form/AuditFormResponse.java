package co.edu.udea.securecheck.adapter.driving.rest.dto.response.form;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuditFormResponse {
    private Long id;
    private LocalDateTime startedAt;
    private String comment;
    private AuditState state;
    private List<FormDomainResponse> domains;
}
/* I don't know if it's correct to get the form in this way,
 * but I'm going to use it because I don't see any other manner
 * */
