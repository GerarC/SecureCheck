package co.edu.udea.securecheck.adapter.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "custom_question")
public class CustomQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custom_question_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "control_id")
    private ControlEntity control;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
}
