package co.edu.udea.securecheck.domain.model.form;


import java.util.List;

public class FormDomain {
    private Long id;
    private Integer index;
    private String name;
    private String description;
    private List<FormControl> controls;

    private FormDomain(Builder builder) {
        this.id = builder.id;
        this.index = builder.index;
        this.name = builder.name;
        this.description = builder.description;
        this.controls = builder.controls;
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

    public List<FormControl> getControls() {
        return controls;
    }

    public void setControls(List<FormControl> controls) {
        this.controls = controls;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer index;
        private String name;
        private String description;
        private List<FormControl> controls;

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

        public Builder controls(List<FormControl> controls) {
            this.controls = controls;
            return this;
        }

        public FormDomain build() {
            return new FormDomain(this);
        }
    }
}