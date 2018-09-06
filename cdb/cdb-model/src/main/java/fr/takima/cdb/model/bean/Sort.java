package fr.takima.cdb.model.bean;

public class Sort {
    private String column;
    private Orientation orientation;

    public Sort(final String column, final Orientation orientation) {
        this.column = column;
        this.orientation = orientation;
    }

    public String getColumn() {
        return column;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sort sort = (Sort) o;

        if (column != null ? !column.equals(sort.column) : sort.column != null) return false;
        return orientation == sort.orientation;
    }

    @Override
    public int hashCode() {
        int result = column != null ? column.hashCode() : 0;
        result = 31 * result + (orientation != null ? orientation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "column='" + column + '\'' +
                ", orientation=" + orientation +
                '}';
    }
}
