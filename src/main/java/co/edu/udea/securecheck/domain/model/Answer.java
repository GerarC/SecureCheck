package co.edu.udea.securecheck.domain.model;

public class Answer {
    private Long id;
    private boolean done;
    private String comment;
    private Control control;
    private Audit audit;

    private Answer(Builder builder) {
        this.id = builder.id;
        this.done = builder.done;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
        private boolean done;
        private String comment;
        private Control control;
        private Audit audit;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder done(boolean done) {
            this.done = done;
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
