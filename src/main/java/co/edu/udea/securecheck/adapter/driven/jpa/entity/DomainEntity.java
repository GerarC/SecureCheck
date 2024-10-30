package co.edu.udea.securecheck.adapter.driven.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "domain")
public class DomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "index", nullable = false, unique = true)
    private Integer index;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 7, max = 511)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "domain")
    private Set<ControlEntity> controls; // NOSONAR
}
