package co.edu.udea.securecheck.domain.utils.pagination;

import co.edu.udea.securecheck.domain.utils.Constants;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;

import java.util.Map;

@Generated
public class PageQuery {
    Integer page;
    Integer size;
    String sortBy;
    boolean ascending;

    public PageQuery(Map<String, String> query) {
        page = Constants.DEFAULT_PAGE_NUMBER;
        size = Constants.DEFAULT_PAGE_SIZE;
        sortBy = Constants.DEFAULT_SORT_COLUMN;
        ascending = Constants.DEFAULT_ASCENDING;
        if(query == null) return;

        if(query.containsKey("page")) page = Integer.parseInt(query.get("page"));
        if(query.containsKey("size")) size = Integer.parseInt(query.get("size"));
        if(query.containsKey("sortBy")) sortBy = query.get("sortBy");
        if(query.containsKey("ascending")) ascending = Boolean.parseBoolean(query.get("ascending"));
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
