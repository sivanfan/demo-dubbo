package com.ule.demo.common.utils;

import com.ule.demo.common.springside.PageInfo;

import java.util.List;

/**
 * @Author fanxl
 * @Description pagehelper.Page到PageInfo 转换工具类
 * @Date 17:37 2019/1/28
 **/
public class PageUtils {

    public static <E> PageInfo<E> convertPage(com.github.pagehelper.Page<E> page){
        PageInfo<E> resultPage = new PageInfo<>();
        setPageProp(page, resultPage);
        resultPage.setResults(page.getResult());
        return resultPage;
    }

    public static <E> PageInfo<E> convertPage(com.github.pagehelper.Page page, List<E> list){
        PageInfo<E> resultPage = new PageInfo<>();
        setPageProp(page, resultPage);
        resultPage.setResults(list);
        return resultPage;
    }

    private static void setPageProp(com.github.pagehelper.Page page, PageInfo resultPage) {
        resultPage.setPageNo(page.getPageNum());
        resultPage.setPageSize(page.getPageSize());
        resultPage.setTotalPage(page.getPages());
        resultPage.setTotalRecord(new Long(page.getTotal()).intValue());
    }

}
