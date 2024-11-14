package co.edu.udea.securecheck.adapter.driving.rest.dto.response.report;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ControlStatisticResponse {
    private Integer conformingControls;
    private Integer nonConformingControls;
    private Integer notApplicableControls;
}