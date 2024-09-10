package co.edu.udea.securecheck.adapter.driving.rest.v1.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DomainResponse {
    private Long id;
    private Integer index;
    private String name;
    private String description;
}
