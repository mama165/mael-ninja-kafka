package fr.takima.cdb.model;

public class PageRequest {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageSize;
    private Search search;
    private Sort sort;
    private int offset;

    public static class Builder {
        private int pageSize = DEFAULT_PAGE_SIZE;
        private Search search;
        private Sort sort;
        private int offset;

        public Builder pageSize(int val) {
            if (val == 0) {
                throw new IllegalArgumentException("Page size cannot be equal to 0");
            }
            pageSize = val;
            return this;
        }

        public Builder search(Search val) {
            search = val;
            return this;
        }

        public Builder sort(Sort val) {
            sort = val;
            return this;
        }
        public Builder offset(int val) {
            offset = val;
            return this;
        }

        public PageRequest build() {
            return new PageRequest(this);
        }
    }

    private PageRequest(Builder builder) {
        pageSize = builder.pageSize;
        search = builder.search;
        sort = builder.sort;
        offset = builder.offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Search getSearch() {
        return search;
    }

    public Sort getSort() {
        return sort;
    }

    public int getOffset() {
        return offset;
    }

    public int getPageIndex() {
        if (offset < pageSize || pageSize == 0) {
            return 1;
        }
        return (offset / pageSize) + 1;
    }

    public void setPageSize(int pageSize) {
        if (pageSize == 0) {
            throw new IllegalArgumentException("Page size cannot be equal to 0");
        }
        this.pageSize = pageSize;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }



    public void setOffset(int offset) {
        this.offset = offset;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageRequest that = (PageRequest) o;

        if (pageSize != that.pageSize) return false;
        if (search != null ? !search.equals(that.search) : that.search != null) return false;
        return sort != null ? sort.equals(that.sort) : that.sort == null;
    }

    @Override
    public int hashCode() {
        int result = pageSize;
        result = 31 * result + (search != null ? search.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageSize=" + pageSize +
                ", search='" + search + '\'' +
                ", sort=" + sort +
                '}';
    }
}
