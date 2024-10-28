package co.edu.udea.securecheck.adapter.driven.jpa.utils;

import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SortJPA {
    private String sortBy;
    private boolean ascending;

    public Sort createSort(){
        if (this.getSortBy() == null || this.getSortBy().isEmpty()) return null;
        Sort.Direction direction = this.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        return Sort.by(direction, this.getSortBy());
    }
}
