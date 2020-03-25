package com.ule.demo.common.springside;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PageInfo
 * @Author fanxl
 * @Description 分页信息类
 * @Date 15:03  2019/1/28
 * @Version 1.0
 **/
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 3521154044740383683L;

    private int pageNo = 1;//页码，默认是第一页
    private int pageSize = 10;//每页显示的记录数，默认是10
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private Object queryParam;
    private List<T> results = new ArrayList();//对应的当前页记录

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Object getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(Object queryParam) {
        this.queryParam = queryParam;
    }
}
