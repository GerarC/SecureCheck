package co.edu.udea.securecheck.adapter.driven.jpa.entity;


import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "outcome", nullable = false)
    private ControlOutcome outcome;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "control", nullable = false)
    private ControlEntity control; // NOSONAR

    @ManyToOne
    @JoinColumn(name = "audit", nullable = false)
    private AuditEntity audit; // NOSONAR
}
