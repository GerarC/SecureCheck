package co.edu.udea.securecheck.adapter.driven.jpa.entity;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "audit")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "company", nullable = false)
    private CompanyEntity company;

    @OneToMany(mappedBy = "audit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AnswerEntity> answers;

    @Column(name = "state", nullable = false)
    private AuditState state;
}
