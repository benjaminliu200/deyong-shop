package com.deyong.search.pojo;

import java.util.List;

/**
 * Created by benjamin on 2017/1/19.
 */
public class SearchResult {
    // 商品列表
    private List<Item> items;
    // 总纪录数
    private long recordCount;
    // 总页数
    private long pageCount;
    // 当前页
    private long pageCur;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getPageCur() {
        return pageCur;
    }

    public void setPageCur(long pageCur) {
        this.pageCur = pageCur;
    }
}
