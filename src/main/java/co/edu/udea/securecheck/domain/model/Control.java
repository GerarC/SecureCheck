package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;

import java.util.List;

@Generated
public class Control {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private Domain domain;
    private List<Question> questions;

    private Control(Builder builder) {
        this.id = builder.id;
        this.index = builder.index;
        this.name = builder.name;
        this.description = builder.description;
        this.domain = builder.domain;
        this.questions = builder.questions;
    }

    public Control() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer index;
        private String name;
        private String description;
        private Domain domain;
        private List<Question> questions;

        public Builder() {
            // Builder Class
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder index(Integer index) {
            this.index = index;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder domain(Domain domain) {
            this.domain = domain;
            return this;
        }

        public Builder questions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Control build() {
            return new Control(this);
        }
    }
}
