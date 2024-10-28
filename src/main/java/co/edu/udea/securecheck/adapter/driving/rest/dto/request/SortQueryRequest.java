package co.edu.udea.securecheck.adapter.driving.rest.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SortQueryRequest {
    String sortBy;
    boolean ascending;
}
