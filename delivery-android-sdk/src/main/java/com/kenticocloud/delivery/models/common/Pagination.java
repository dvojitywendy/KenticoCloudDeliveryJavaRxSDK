package com.kenticocloud.delivery.models.common;

public class Pagination {

    private int skip;
    private int limit;
    private int count;
    private String nextPage;

    public Pagination(
            int skip,
            int limit,
            int count,
            String nextPage
    ){
        this.skip = skip;
        this.limit = limit;
        this.count = count;
        this.nextPage = nextPage;
    }

    public int getSkip(){
        return this.skip;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public String getNextPage() {
        return nextPage;
    }
}
