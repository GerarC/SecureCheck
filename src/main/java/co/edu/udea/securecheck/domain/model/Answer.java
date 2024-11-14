package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.enums.ControlOutcome;

public class Answer {
    private Long id;
    private ControlOutcome outcome;
    private String comment;
    private Control control;
    private Audit audit;

    private Answer(Builder builder) {
        this.id = builder.id;
        this.outcome = builder.outcome;
        this.comment = builder.comment;
        this.control = builder.control;
        this.audit = builder.audit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ControlOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(ControlOutcome outcome) {
        this.outcome = outcome;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private ControlOutcome outcome;
        private String comment;
        private Control control;
        private Audit audit;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder outcome(ControlOutcome done) {
            this.outcome = done;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder control(Control control) {
            this.control = control;
            return this;
        }

        public Builder audit(Audit audit) {
            this.audit = audit;
            return this;
        }

        public Answer build() {
            return new Answer(this);
        }
    }
}
