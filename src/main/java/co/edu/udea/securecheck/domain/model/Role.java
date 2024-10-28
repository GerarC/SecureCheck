package co.edu.udea.securecheck.domain.model;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;

@Generated
public class Role {
    private Long id;
    private RoleName name;

    private Role(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private Long id;
        private RoleName name;

        public Builder() {
            // Builder Class
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(RoleName name) {
            this.name = name;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
