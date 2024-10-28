package co.edu.udea.securecheck.adapter.driven.jpa.utils;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationJPA {
    private Integer page;
    private Integer size;
    private String sortBy;
    private boolean ascending;

    /**
     * Return a pageable object to use in a query, the options are the attributes of the PaginationJPA instance
     *
     * @return Return the prepared pageable
     */
    public Pageable createPageable() {
        Pageable pageable;
        if (this.getSortBy() == null || this.getSortBy().isEmpty()) {
            pageable = PageRequest.of(this.getPage(), this.size);
        } else {
            Sort.Direction direction = this.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sort = Sort.by(direction, this.getSortBy());
            pageable = PageRequest.of(this.getPage(), this.size).withSort(sort);
        }
        return pageable;
    }
}
