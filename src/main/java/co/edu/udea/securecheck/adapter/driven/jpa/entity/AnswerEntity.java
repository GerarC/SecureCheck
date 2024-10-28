package co.edu.udea.securecheck.adapter.driven.jpa.entity;


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

    @Column(name = "done", nullable = false)
    private boolean done;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "control", nullable = false)
    private ControlEntity control;

    @ManyToOne
    @JoinColumn(name = "audit", nullable = false)
    private AuditEntity audit;
}
