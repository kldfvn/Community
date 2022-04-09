package com.nowcoder.community.entity;

// 分页
public class Page {
    //当前
    private int current=1;
    //每页上限
    private int limit=10;
    //数据总数
    private int rows;
    //路径
    private String path;

//    private int total;
//    private int from;
//    private int to;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1) this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1&&limit<=50) this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0) this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
//    当前页的起始行
    public int getOffset()
    {
        return (current-1)*limit;
    }
//    获取总页数
    public int getTotal()
    {
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }
//    获取起始页
    public int getFrom()
    {
        int from = current - 2;
        return from < 1 ? 1 : from;
    }
//    获取结束页
    public int getTo()
    {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
