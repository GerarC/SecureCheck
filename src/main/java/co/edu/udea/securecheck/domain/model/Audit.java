package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;

import java.time.LocalDateTime;
import java.util.List;

public class Audit {
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String comment;
    private Company company;
    private List<Answer> answers;
    private AuditState state;

    private Audit(Builder builder) {
        this.id = builder.id;
        this.startedAt = builder.startedAt;
        this.endedAt = builder.endedAt;
        this.comment = builder.comment;
        this.company = builder.company;
        this.answers = builder.answers;
        this.state = builder.state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public AuditState getState() {
        return state;
    }

    public void setState(AuditState state) {
        this.state = state;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String comment;
        private Company company;
        private List<Answer> answers;
        private AuditState state;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder startedAt(LocalDateTime startedAt) {
            this.startedAt = startedAt;
            return this;
        }

        public Builder endedAt(LocalDateTime endedAt) {
            this.endedAt = endedAt;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public Builder answers(List<Answer> answers) {
            this.answers = answers;
            return this;
        }

        public Builder state(AuditState state) {
            this.state = state;
            return this;
        }

        public Audit build() {
            return new Audit(this);
        }
    }
}
