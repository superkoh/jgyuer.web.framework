package com.jgyuer.framework.api.request;

import com.jgyuer.framework.type.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class PageReq extends TransferableReq<Page> {
    @NotNull
    @ApiModelProperty(required = true, value = "每页数量")
    private Integer pageSize = 20;
    @NotNull
    @ApiModelProperty(required = true, value = "页码")
    private Integer pageNo = 1;
    @ApiModelProperty(required = true, value = "排序")
    private String orderBy;

    public Page toPage() {
        return this.toBean();
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
