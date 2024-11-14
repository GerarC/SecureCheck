package co.edu.udea.securecheck.domain.model.report;

import co.edu.udea.securecheck.domain.model.Company;
import co.edu.udea.securecheck.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;


public class AuditReport {
    private Long id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String comment;
    private String objective;
    private String scope;
    private ControlStatistic auditStatistic;
    private List<ReportDomain> domains;
    private User auditor;
    private Company company;

    private AuditReport(Builder builder) {
        this.id = builder.id;
        this.startedAt = builder.startedAt;
        this.endedAt = builder.endedAt;
        this.comment = builder.comment;
        this.objective = builder.objective;
        this.scope = builder.scope;
        this.auditStatistic = builder.auditStatistic;
        this.domains = builder.domains;
        this.auditor = builder.auditor;
        this.company = builder.company;
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

    public ControlStatistic getAuditStatistic() {
        return auditStatistic;
    }

    public void setAuditStatistic(ControlStatistic auditStatistic) {
        this.auditStatistic = auditStatistic;
    }

    public List<ReportDomain> getDomains() {
        return domains;
    }

    public void setDomains(List<ReportDomain> domains) {
        this.domains = domains;
    }

    public User getAuditor() {
        return auditor;
    }

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String comment;
        private String objective;
        private String scope;
        private ControlStatistic auditStatistic;
        private List<ReportDomain> domains;
        private User auditor;
        private Company company;

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

        public Builder objective(String objective) {
            this.objective = objective;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder auditStatistic(ControlStatistic auditStatistic) {
            this.auditStatistic = auditStatistic;
            return this;
        }

        public Builder domains(List<ReportDomain> domains) {
            this.domains = domains;
            return this;
        }

        public Builder auditor(User auditor) {
            this.auditor = auditor;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public AuditReport build() {
            return new AuditReport(this);
        }
    }
}