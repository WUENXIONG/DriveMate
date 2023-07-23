package com.common.util;

import java.io.Serializable;
import java.util.List;

public class DataPagingDef implements Serializable {
    private static final long serialVersionUID = 1l;


    //总记录数
    private long totalRecordCount;

    //页记录
    private int pageSize;

    //总页数
    private int totalPageNum;

    //当前页号
    private int pageIndex;

    private List list;

    public DataPagingDef(List list, long totalRecordCount, int pageIndex, int pageSize){
        this.list = list;
        this.totalRecordCount = totalRecordCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalPageNum = (int)Math.ceil((double)totalRecordCount/pageSize);

    }


}
