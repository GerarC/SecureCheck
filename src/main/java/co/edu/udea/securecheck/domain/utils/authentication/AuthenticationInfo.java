package co.edu.udea.securecheck.domain.utils.authentication;

public class AuthenticationInfo {
    private String id;
    private String password;

    private AuthenticationInfo(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        private String id;
        private String password;

        public Builder() {
            // Builder class
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationInfo build() {
            return new AuthenticationInfo(this);
        }
    }
}
