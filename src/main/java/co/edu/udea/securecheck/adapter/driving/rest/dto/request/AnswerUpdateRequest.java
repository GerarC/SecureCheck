package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnswerUpdateRequest {
    private Long id;
    private boolean done;
    private String comment;
}
