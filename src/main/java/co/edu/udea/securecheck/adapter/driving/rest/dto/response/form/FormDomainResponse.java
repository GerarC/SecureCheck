package co.edu.udea.securecheck.adapter.driving.rest.dto.response.form;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormDomainResponse {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private Set<FormControlResponse> controls;
}