package co.edu.udea.securecheck.adapter.driving.rest.dto.response.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormAnswerResponse {
    private Long id;
    private boolean done;
    private String comment;
}
