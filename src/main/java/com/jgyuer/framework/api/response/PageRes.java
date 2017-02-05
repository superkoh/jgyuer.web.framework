package com.jgyuer.framework.api.response;

import com.jgyuer.framework.type.PageList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApiModel
public class PageRes<T extends BizRes> extends ListRes<T> {

    @ApiModelProperty(required = true, value = "页码")
    private Integer pageSize;
    @ApiModelProperty(required = true, value = "每页数量")
    private Integer pageNo;
    @ApiModelProperty(value = "全部数量")
    private Integer totalCnt;

    private Integer prevPage;
    private Integer nextPage;
    private List<InnerPageStruct> pageRange;

    public <I> PageRes(PageList<I> pageList, Function<? super I, ? extends T> mapper) {
        this(pageList.getList().stream().map(mapper).collect(Collectors.toList()), pageList.getPageSize(), pageList
                .getPageNo(), pageList.getTotalCnt());
    }

    public PageRes(List<T> list, Integer pageSize, Integer pageNo, Integer totalCnt) {
        super(list);
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalCnt = totalCnt;

        int totalPage = (this.totalCnt / this.pageSize) + 1;
        if (this.totalCnt % this.pageSize == 0) {
            totalPage--;
        }
        int startPage = this.pageNo - 2;
        int endPage = this.pageNo + 2;
        if (endPage < 5) {
            endPage = 5;
        }
        if (endPage > totalPage) {
            startPage -= (endPage - totalPage);
            endPage = totalPage;
        }
        if (startPage < 1) {
            startPage = 1;
        }

        pageRange = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageRange.add(new InnerPageStruct(i, i == this.pageNo));
        }
        this.prevPage = this.pageNo > 1 ? this.pageNo - 1 : null;
        this.nextPage = this.pageNo < totalPage ? this.pageNo + 1 : null;
    }

    private static class InnerPageStruct {
        private int pageNo;
        private boolean isCurrentPage;

        InnerPageStruct(int pageNo, boolean isCurrentPage) {
            this.pageNo = pageNo;
            this.isCurrentPage = isCurrentPage;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public boolean getIsCurrentPage() {
            return isCurrentPage;
        }

        public void setIsCurrentPage(boolean isCurrentPage) {
            this.isCurrentPage = isCurrentPage;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Integer getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<InnerPageStruct> getPageRange() {
        return pageRange;
    }

    public void setPageRange(List<InnerPageStruct> pageRange) {
        this.pageRange = pageRange;
    }
}

