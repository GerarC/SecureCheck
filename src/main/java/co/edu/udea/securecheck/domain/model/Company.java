package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;

import java.time.LocalDateTime;
import java.util.List;

@Generated
public class Company {
    private String id;
    private String nit;
    private String name;
    private String nick;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime createdAt;
    private List<Question> questions;
    private List<Audit> audits;
    private User user;

    private Company(Builder builder) {
        this.id = builder.id;
        this.nit = builder.nit;
        this.name = builder.name;
        this.nick = builder.nick;
        this.address = builder.address;
        this.contactEmail = builder.contactEmail;
        this.contactPhone = builder.contactPhone;
        this.createdAt = builder.createdAt;
        this.questions = builder.questions;
        this.user = builder.user;
        this.audits = builder.audits;
    }

    public Company() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Audit> getAudits() {
        return audits;
    }

    public void setAudits(List<Audit> audits) {
        this.audits = audits;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String nit;
        private String name;
        private String nick;
        private String address;
        private String contactEmail;
        private String contactPhone;
        private LocalDateTime createdAt;
        private List<Question> questions;
        private List<Audit> audits;
        private User user;

        public Builder() {
            // It's a Builder class
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder nit(String nit) {
            this.nit = nit;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nick(String nick) {
            this.nick = nick;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public Builder contactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder questions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Builder audits(List<Audit> audits){
            this.audits = audits;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }
}
