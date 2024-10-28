package co.edu.udea.securecheck.domain.model;

public class TokenHolder {
    private String token;

    private TokenHolder(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;

        public Builder() {
            // Builder class
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public TokenHolder build() {
            return new TokenHolder(this);
        }

    }
}
