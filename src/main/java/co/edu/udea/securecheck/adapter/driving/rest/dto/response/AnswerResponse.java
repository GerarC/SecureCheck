package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerResponse {
    private Long id;
    private boolean done;
    private String comment;
}
