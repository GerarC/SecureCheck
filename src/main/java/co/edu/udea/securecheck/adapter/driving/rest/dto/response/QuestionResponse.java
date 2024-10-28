package co.edu.udea.securecheck.adapter.driving.rest.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionResponse {
    private Long id;
    private String body;
}
