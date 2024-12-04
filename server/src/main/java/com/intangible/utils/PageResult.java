package com.intangible.utils;

import java.util.List;

public class PageResult<T> {

    private long total;

    private List<T> records;

    public PageResult(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
