package co.edu.udea.securecheck.domain.model.form;

import co.edu.udea.securecheck.domain.model.Answer;
import co.edu.udea.securecheck.domain.model.Question;

import java.util.List;

public class FormControl {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private List<Question> questions;
    private Answer answer;

    private FormControl(Builder builder) {
        this.id = builder.id;
        this.index = builder.index;
        this.name = builder.name;
        this.description = builder.description;
        this.questions = builder.questions;
        this.answer = builder.answer;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer index;
        private String name;
        private String description;
        private List<Question> questions;
        private Answer answer;

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

        public Builder questions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Builder answer(Answer answer) {
            this.answer = answer;
            return this;
        }

        public FormControl build() {
            return new FormControl(this);
        }
    }
}
