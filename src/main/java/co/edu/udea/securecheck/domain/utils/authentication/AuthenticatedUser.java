package co.edu.udea.securecheck.domain.utils.authentication;


import co.edu.udea.securecheck.domain.utils.enums.RoleName;

public class AuthenticatedUser {
    private String token;
    private RoleName role;
    private String id;

    private AuthenticatedUser(Builder builder) {
        this.token = builder.token;
        this.role = builder.role;
        this.id = builder.id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private RoleName role;
        private String id;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(RoleName role) {
            this.role = role;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public AuthenticatedUser build() {
            return new AuthenticatedUser(this);
        }
    }
}