package co.edu.udea.securecheck.adapter.driving.rest.dto.response.report;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReportDomainResponse {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private ControlStatisticResponse domainStatistic;
    private List<ReportControlResponse> controls;
}