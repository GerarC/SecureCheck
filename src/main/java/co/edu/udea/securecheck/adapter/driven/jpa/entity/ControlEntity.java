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
@Table(name = "control")
public class ControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "index", nullable = false)
    private Integer index;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 7, max = 511)
    private String description;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private DomainEntity domain;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "control")
    private Set<CustomQuestionEntity> questions;
}
