package co.edu.udea.securecheck.domain.utils.filters;

import co.edu.udea.securecheck.domain.utils.enums.AuditState;

import java.time.LocalDateTime;

public class AuditFilter {
    private final LocalDateTime startedAfter;
    private final LocalDateTime startedBefore;
    private final AuditState auditState;

    public AuditFilter(Builder builder) {
        this.startedAfter = builder.startedAfter;
        this.startedBefore = builder.startedBefore;
        this.auditState = builder.auditState;
    }

    public LocalDateTime getStartedAfter() {
        return startedAfter;
    }

    public LocalDateTime getStartedBefore() {
        return startedBefore;
    }

    public AuditState getAuditState() {
        return auditState;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private  LocalDateTime startedAfter;
        private LocalDateTime startedBefore;
        private AuditState auditState;

        public Builder startedAfter(LocalDateTime startedAfter) {
            this.startedAfter = startedAfter;
            return this;
        }

        public Builder startedBefore(LocalDateTime startedBefore) {
            this.startedBefore = startedBefore;
            return this;
        }

        public Builder auditState(AuditState auditState) {
            this.auditState = auditState;
            return this;
        }

        public AuditFilter build() {
            return new AuditFilter(this);
        }
    }
}
