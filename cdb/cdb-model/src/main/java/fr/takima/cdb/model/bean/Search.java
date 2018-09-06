package fr.takima.cdb.model.bean;

import java.util.Objects;

public class Search {
    private String column;
    private String value;

    public Search(String value) {
        this.value = value;
    }

    public Search(String column, String value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Search)) return false;
        Search search = (Search) o;
        return Objects.equals(column, search.column) &&
                Objects.equals(value, search.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column);
    }

    @Override
    public String toString() {
        return "Search{" +
                "column='" + column + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}