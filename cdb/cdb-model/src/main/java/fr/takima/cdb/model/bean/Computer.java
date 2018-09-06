package fr.takima.cdb.model.bean;

import java.time.LocalDate;

public class Computer {

    private Long id;

    private String name;

    private final LocalDate introducedDate;

    private final LocalDate discontinuedDate;

    private final Company manufacturer;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        // Required parameters
        private Long id;
        private String name;
        //private final Company manufacturer;

        // Optional parameters - initialized to default values
        private LocalDate introducedDate = LocalDate.ofYearDay(2015, 35);
        private LocalDate discontinuedDate = LocalDate.ofYearDay(2018, 35);
        //private LocalDate introducedDate;
        //private LocalDate discontinuedDate;
        private Company manufacturer;

        /**
         * Constructor with required parameters.
         * @param id the computer id
         */
        public Builder(Long id) {
            this.id = id;
        }


        /**
         * Constructor with required parameters.
         * @param id the computer id
         * @param name the computer name
         */
        public Builder(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Constructor.
         * @param name the computer name
         */
        public Builder(String name) {
            this.name = name;
        }

        /**
         * Returns the introduced date.
         * @param val the computer introduced date
         * @return the introduced date
         */
        public Builder introducedDate(LocalDate val) {
            introducedDate = val;
            return this;
        }

        /**
         * Returns the discontinued date.
         * @param val the computer discontinued date
         * @return the discontinued date
         */
        public Builder discontinuedDate(LocalDate val) {
            discontinuedDate = val;
            return this;
        }

        /**
         * Returns the manufacturer.
         * @param val the computer manufacturer
         * @return the manufacturer
         */
        public Builder manufacturer(Company val) {
            manufacturer = val;
            return this;
        }

        /**
         * Build a computer.
         * @return the computer
         */
        public Computer build() {
            return new Computer(this);
        }
    }

    /**
     * Constructor.
     * @param builder the builder
     */
    private Computer(Builder builder) {
        id = builder.id;
        name = builder.name;
        manufacturer = builder.manufacturer;
        introducedDate = builder.introducedDate;
        discontinuedDate = builder.discontinuedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getIntroducedDate() {
        return introducedDate;
    }

    public LocalDate getDiscontinuedDate() {
        return discontinuedDate;
    }

    public Company getManufacturer() {
        return manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Computer computer = (Computer) o;

        if (id != null ? !id.equals(computer.id) : computer.id != null) {
            return false;
        }
        if (name != null ? !name.equals(computer.name) : computer.name != null) {
            return false;
        }
        if (introducedDate != null ? !introducedDate.equals(computer.introducedDate) : computer.introducedDate != null) {
            return false;
        }
        if (discontinuedDate != null ? !discontinuedDate.equals(computer.discontinuedDate) : computer.discontinuedDate != null) {
            return false;
        }
        return manufacturer != null ? manufacturer.equals(computer.manufacturer) : computer.manufacturer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (introducedDate != null ? introducedDate.hashCode() : 0);
        result = 31 * result + (discontinuedDate != null ? discontinuedDate.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", introducedDate=" + introducedDate +
                ", discontinuedDate=" + discontinuedDate +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
