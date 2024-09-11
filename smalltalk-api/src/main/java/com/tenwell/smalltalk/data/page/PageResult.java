package com.tenwell.smalltalk.data.page;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class PageResult<T> {

    private int pageNo;
    private int pageSize;
    private long totalCount;
    private long count;
    private List<T> items;

    protected PageResult() {
    }

    protected PageResult(int pageNo, int pageSize, long totalCount, long count, List<T> items) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.count = count;
        this.items = items;
    }

    static public <T> PageResult<T> of(int pageNo, int pageSize, long totalCount, List<T> items) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.pageNo = pageNo;
        pageResult.pageSize = pageSize;
        pageResult.totalCount = totalCount;
        pageResult.count = items.size();
        pageResult.items = items;
        return pageResult;
    }

    static public <T> PageResult<T> empty(int pageNo, int pageSize) {
        return of(0, pageNo, pageSize, List.of());
    }

}
