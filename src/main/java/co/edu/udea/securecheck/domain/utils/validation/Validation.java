package co.edu.udea.securecheck.domain.utils.validation;

public class Validation {
    //Attributes
    private final boolean valid;

    // Constructor
    private Validation(Builder builder) {
        this.valid = builder.valid;
    }

    public <X extends Throwable> void ifInvalidThrows(X throwable) throws X {
        if (!isValid()) throw throwable;
    }

    public boolean isValid() {
        return valid;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private boolean valid;

        public Builder valid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public Validation build() {
            return new Validation(this);
        }
    }
}
