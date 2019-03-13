package com.yxb.cms.domain.dto;

import java.io.Serializable;

/*
 * @Author hzj
 * @ClassName Pagination
 * @Description 分页信息
 * @Date 14:29 2019/1/14
 * @Param
 * @return
 **/
public class Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页数
     */
    private int page;

    /**
     * 分页插件用参数
     */
    private int page2;

    /**
     * 每页行数
     */
    private int rows;

    /**
     * 排序列字段名
     */
    private String sort;

    /**
     * 排序方式，可以是 'asc'  'desc'，默认'asc'
     */
    private String order;

    public Pagination() {

    }
    public Pagination(int page,int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage2() {
        return page2;
    }

    public void setPage2(int page2) {
        this.page2 = page2;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * 计算从第几行
     */
    public void initPageIndex() {
        this.page2 = this.page;
        this.page = (page - 1) * rows;
    }

}
