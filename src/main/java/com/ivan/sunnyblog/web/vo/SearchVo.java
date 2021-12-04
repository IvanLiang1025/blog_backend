package com.ivan.sunnyblog.web.vo;

/**
 * Author: jinghaoliang
 * Date: 2021-10-01 3:58 p.m.
 **/

public class SearchVo {
    private int page = 1;
    private int limit = 10;

    private Long categoryId;

    private Long total;

    public  SearchVo() {}

    public SearchVo(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SearchVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", categoryId=" + categoryId +
                ", total=" + total +
                '}';
    }
}
