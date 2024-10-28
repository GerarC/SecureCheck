package co.edu.udea.securecheck.domain.model;

public class Question {
    private Long id;
    private String body;
    private Control control;
    private Company company;

    private Question(Builder builder) {
        this.id = builder.id;
        this.body = builder.body;
        this.control = builder.control;
        this.company = builder.company;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
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
        private String body;
        private Control control;
        private Company company;

        public Builder() {
            // Builder Class
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder control(Control control) {
            this.control = control;
            return this;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
