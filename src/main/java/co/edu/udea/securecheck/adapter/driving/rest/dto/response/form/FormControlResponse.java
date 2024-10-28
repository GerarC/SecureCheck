package co.edu.udea.securecheck.adapter.driving.rest.dto.response.form;

import co.edu.udea.securecheck.adapter.driving.rest.dto.response.QuestionResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormControlResponse {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private List<QuestionResponse> questions;
    private FormAnswerResponse answer;
}
