package co.edu.udea.securecheck.adapter.driving.rest.dto.response.report;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserReportResponse {
    private String id;
    private String name;
    private String lastname;
    private String identityDocument;
    private String phone;
    private String email;
}
