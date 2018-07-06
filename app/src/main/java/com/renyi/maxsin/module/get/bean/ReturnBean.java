package com.renyi.maxsin.module.get.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/20.
 */

public class ReturnBean {
    /**
     * code : 800
     * message : 成功
     * data : []
     */

    private String code;
    private String message;
    private List<?> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
