package co.edu.udea.securecheck.domain.utils.filters;

import co.edu.udea.securecheck.domain.utils.annotation.Generated;

import java.time.LocalDateTime;

@Generated
public class CompanyFilter {
    private final String nit;
    private final String name;
    private final String nick;
    private final LocalDateTime createdAfter;
    private final LocalDateTime createdBefore;

    public CompanyFilter(String nit, String name, String nick, LocalDateTime createdAfter, LocalDateTime createdBefore) {
        this.nit = nit;
        this.name = name;
        this.nick = nick;
        this.createdAfter = createdAfter;
        this.createdBefore = createdBefore;
    }

    private CompanyFilter(Builder builder){
        this.nit = builder.nit;
        this.name = builder.name;
        this.nick = builder.nick;
        this.createdAfter = builder.createdAfter;
        this.createdBefore = builder.createdBefore;
    }

    public String getNit() {
        return nit;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public LocalDateTime getCreatedAfter() {
        return createdAfter;
    }

    public LocalDateTime getCreatedBefore() {
        return createdBefore;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String nit;
        private String name;
        private String nick;
        private LocalDateTime createdAfter;
        private LocalDateTime createdBefore;

        public Builder() {
           // Builder Class
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

        public Builder createdAfter(LocalDateTime createdAfter) {
            this.createdAfter = createdAfter;
            return this;
        }

        public Builder createdBefore(LocalDateTime createdBefore) {
            this.createdBefore = createdBefore;
            return this;
        }

        public CompanyFilter build() {
            return new CompanyFilter(this);
        }
    }
}
