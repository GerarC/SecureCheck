package co.edu.udea.securecheck.domain.model.report;

public class ControlStatistic {
    private Integer conformingControls;
    private Integer nonConformingControls;
    private Integer notApplicableControls;

    private ControlStatistic(Builder builder) {
        this.conformingControls = builder.conformingControls;
        this.nonConformingControls = builder.nonConformingControls;
        this.notApplicableControls = builder.notApplicableControls;
    }

    public Integer getConformingControls() {
        return conformingControls;
    }

    public void setConformingControls(Integer conformingControls) {
        this.conformingControls = conformingControls;
    }

    public Integer getNonConformingControls() {
        return nonConformingControls;
    }

    public void setNonConformingControls(Integer nonConformingControls) {
        this.nonConformingControls = nonConformingControls;
    }

    public Integer getNotApplicableControls() {
        return notApplicableControls;
    }

    public void setNotApplicableControls(Integer notApplicableControls) {
        this.notApplicableControls = notApplicableControls;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer conformingControls;
        private Integer nonConformingControls;
        private Integer notApplicableControls;

        public Builder conformingControls(Integer conformingControls) {
            this.conformingControls = conformingControls;
            return this;
        }

        public Builder nonConformingControls(Integer nonConformingControls) {
            this.nonConformingControls = nonConformingControls;
            return this;
        }

        public Builder notApplicableControls(Integer notApplicableControls) {
            this.notApplicableControls = notApplicableControls;
            return this;
        }

        public ControlStatistic build() {
            return new ControlStatistic(this);
        }
    }
}