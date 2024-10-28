package co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyFilterRequest {
    private String nit;
    private String name;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;
}
