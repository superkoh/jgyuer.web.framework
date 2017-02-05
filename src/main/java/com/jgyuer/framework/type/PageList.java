package com.jgyuer.framework.type;

import com.jgyuer.framework.lang.BaseObject;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by KOH on 16/7/15.
 */
public class PageList<T> extends BaseObject {
    private List<T> list;
    private int totalCnt;
    private int pageSize;
    private int pageNo;

    public PageList(List<T> list, Page page, int totalCnt) {
        this.list = list;
        this.pageSize = page.getPageSize();
        this.pageNo = page.getPageNo();
        this.totalCnt = totalCnt;
    }

    public <I> PageList(PageList<I> pageList, Function<? super I, ? extends T> mapper) {
        this.list = pageList.list.stream().map(mapper).collect(Collectors.toList());
        this.pageSize = pageList.pageSize;
        this.pageNo = pageList.pageNo;
        this.totalCnt = pageList.totalCnt;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
