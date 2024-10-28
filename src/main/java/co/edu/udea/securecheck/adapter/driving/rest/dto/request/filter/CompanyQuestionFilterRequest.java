package co.edu.udea.securecheck.adapter.driving.rest.dto.request.filter;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyQuestionFilterRequest {
    private Long controlId;
    private String body;
}
