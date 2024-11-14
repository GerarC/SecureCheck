package co.edu.udea.securecheck.domain.model.form;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;

import java.time.LocalDateTime;
import java.util.List;

public class AuditForm {
    private Long id;
    private LocalDateTime startedAt;
    private String comment;
    private String objective;
    private String scope;
    private AuditState state;
    private List<FormDomain> domains;

    public AuditForm(Builder builder) {
        this.id = builder.id;
        this.startedAt = builder.startedAt;
        this.comment = builder.comment;
        this.objective = builder.objective;
        this.scope = builder.scope;
        this.state = builder.state;
        this.domains = builder.domains;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AuditState getState() {
        return state;
    }

    public void setState(AuditState state) {
        this.state = state;
    }

    public List<FormDomain> getDomains() {
        return domains;
    }

    public void setDomains(List<FormDomain> domains) {
        this.domains = domains;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private  Long id;
        private LocalDateTime startedAt;
        private String objective;
        private String scope;
        private String comment;
        private AuditState state;
        private List<FormDomain> domains;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder startedAt(LocalDateTime startedAt) {
            this.startedAt = startedAt;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder objective(String objective) {
            this.objective = objective;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder state(AuditState state) {
            this.state = state;
            return this;
        }

        public Builder domains(List<FormDomain> domains) {
            this.domains = domains;
            return this;
        }

        public AuditForm build() {
            return new AuditForm(this);
        }
    }
}
/* I don't know if it's correct to get the form in this way,
 * but I'm going to use it because I don't see any other manner
 * */
