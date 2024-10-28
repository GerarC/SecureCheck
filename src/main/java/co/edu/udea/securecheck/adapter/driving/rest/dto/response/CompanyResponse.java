package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {
    private String id;
    private String nit;
    private String name;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime createdAt;
}
