package com.ivan.sunnyblog.web;

/**
 * Author: jinghaoliang
 * Date: 2021-09-25 12:01 a.m.
 **/

public class ResultInfo {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    private String info = null;
    private int status = SUCCESS;
    private Object data = null;
    private Long total;


    public ResultInfo() {}

    public ResultInfo(String info){
        this.info = info;
    }

    public ResultInfo(int status, String info){
        this.status = status;
        this.info = info;
    }


    public static ResultInfo getSucResult(String info){
        return new ResultInfo(SUCCESS, info);
    }


    public static ResultInfo getSucResult(Object data){
        ResultInfo result = new ResultInfo(null);
        result.setData(data);
        return result;
    }

    public static ResultInfo getErrResult(String info){
        return new ResultInfo(ERROR, info);
    }

//    public static ResultInfo getErrResult(String info){ return new ResultInfo(ERROR, info);}

    public static ResultInfo getResult(){
        return new ResultInfo(null);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ResultInfo{" +
                "info='" + info + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
