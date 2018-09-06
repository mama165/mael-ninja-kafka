package fr.takima.cdb.model.dto;

import fr.takima.cdb.model.bean.Search;
import fr.takima.cdb.model.bean.Sort;

import java.util.List;
import java.util.Objects;

public class PageResponse<T> {

    private int pageIndex;

    private int pageSize;

    private Search search;

    private Sort sort;

    private int totalPages;

    private int totalElements;

    private List<T> elements;

    public static class Builder<T> {
        private int pageIndex;
        private int pageSize;
        private Search search;
        private Sort sort;
        private int totalPages;
        private int totalElements;
        private List<T> elements;

        public Builder() { }

        public Builder pageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder search(Search search) {
            this.search = search;
            return this;
        }

        public Builder sort(Sort totalElements) {
            this.sort = totalElements;
            return this;
        }

        public Builder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder totalElements(int totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder elements(List<T> elements) {
            this.elements = elements;
            return this;
        }

        public PageResponse<T> build() {
            return new PageResponse<T>(this);
        }

    }

    private PageResponse(Builder<T> builder) {
        pageIndex = builder.pageIndex;
        pageSize = builder.pageSize;
        search = builder.search;
        sort = builder.sort;
        totalPages = builder.totalPages;
        totalElements = builder.totalElements;
        elements = builder.elements;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponse<?> that = (PageResponse<?>) o;
        return pageIndex == that.pageIndex &&
                pageSize == that.pageSize &&
                totalPages == that.totalPages &&
                totalElements == that.totalElements &&
                Objects.equals(search, that.search) &&
                Objects.equals(sort, that.sort) &&
                Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageIndex, pageSize, search, sort, totalPages, totalElements, elements);
    }

    @Override
    public String toString() {
        return "PageResponseDTO{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", search=" + search +
                ", sort=" + sort +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", elements=" + elements +
                '}';
    }
}