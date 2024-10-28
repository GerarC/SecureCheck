package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;

import java.time.LocalDateTime;
import java.util.List;

@Generated
public class User {
    private String id;
    private String name;
    private String lastname;
    private String identityDocument;
    private LocalDateTime birthdate;
    private String phone;
    private String email;
    private String password;
    private Role role;
    private List<Company> companies;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.identityDocument = builder.identityDocument;
        this.birthdate = builder.birthdate;
        this.phone = builder.phone;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.companies = builder.companies;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String lastname;
        private String identityDocument;
        private LocalDateTime birthdate;
        private String phone;
        private String email;
        private String password;
        private Role role;
        private List<Company> companies;

        public Builder() {
            // Builder Class
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder identityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public Builder birthdate(LocalDateTime birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder companies(List<Company> companies) {
            this.companies = companies;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
