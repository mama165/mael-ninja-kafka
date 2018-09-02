package fr.takima.cdb.dto;

public class ComputerDTO {

    private Long id;

    private String name;

    private String introduced;

    private String discontinued;

    private Long company;

    public static class Builder {
        private Long id;
        private String name;
        private String introduced;
        private String discontinued;
        private Long company;

        public Builder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder introduced(String val) {
            introduced = val;
            return this;
        }

        public Builder discontinued(String val) {
            discontinued = val;
            return this;
        }

        public Builder company(Long val) {
            company = val;
            return this;
        }

        public ComputerDTO build() { return new ComputerDTO(this); }
    }

    private ComputerDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.company = builder.company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    public String getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerDTO that = (ComputerDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (introduced != null ? !introduced.equals(that.introduced) : that.introduced != null) return false;
        if (discontinued != null ? !discontinued.equals(that.discontinued) : that.discontinued != null) return false;
        return company != null ? company.equals(that.company) : that.company == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introduced != null ? introduced.hashCode() : 0);
        result = 31 * result + (discontinued != null ? discontinued.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComputerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced='" + introduced + '\'' +
                ", discontinued='" + discontinued + '\'' +
                ", company=" + company +
                '}';
    }
}
