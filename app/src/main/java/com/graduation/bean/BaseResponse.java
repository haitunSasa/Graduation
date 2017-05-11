package com.graduation.bean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by xsf
 * on 2016.09.9:47
 */
public class BaseResponse<T> implements Serializable {
    public int flag;
    public int errCode;
    public String cause;

    public T data;

    public boolean success() {
        return flag==1;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "flag='" + flag + '\'' +
                ", errCode='" + errCode + '\''+
                ", cause='" + cause + '\'' +
                ", data=" + data +
                '}';
    }
}
