package fr.takima.cdb.model;

import java.util.List;
import java.util.Objects;

public class Page<T> {

    private int pageIndex;
    private List<T> elements;
    private int totalElements;
    private int totalPages;


    public Page(int pageIndex, List<T> elements, int totalElements, int totalPages) {
        if (pageIndex <= 0) {
            throw new IllegalArgumentException(String.format("Page index must be positive. Got: %s", pageIndex));
        }
        this.pageIndex = pageIndex;
        this.elements = elements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List<T> getElements() {
        return elements;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return pageIndex == page.pageIndex &&
                totalElements == page.totalElements &&
                totalPages == page.totalPages &&
                Objects.equals(elements, page.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageIndex, elements, totalElements, totalPages);
    }
}
