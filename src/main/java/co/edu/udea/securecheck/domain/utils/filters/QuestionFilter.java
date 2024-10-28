package co.edu.udea.securecheck.domain.utils.filters;

public class QuestionFilter {
    private String companyId;
    private Long controlId;
    private String body;

    private QuestionFilter(Builder builder) {
        this.companyId = builder.companyId;
        this.controlId = builder.controlId;
        this.body = builder.body;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getControlId() {
        return controlId;
    }

    public void setControlId(Long controlId) {
        this.controlId = controlId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String companyId;
        private Long controlId;
        private String body;

        public Builder(){
            //Builder class
        }

        public Builder companyId(String companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder controlId(Long controlId) {
            this.controlId = controlId;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public QuestionFilter build(){
            return new QuestionFilter(this);
        }
    }
}
