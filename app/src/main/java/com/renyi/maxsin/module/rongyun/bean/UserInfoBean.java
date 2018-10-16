package com.renyi.maxsin.module.rongyun.bean;


/**
 * Created by zhangyuliang on 2018/10/16.
 */

public class UserInfoBean {
    /**
     * code : 800
     * message : 成功
     * data : {"has_mobile_flag":"1","u_id":"1","is_stu":"2"}
     */

    private String code;
    private String message;
    private  DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String name;
        private String headphoto;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadphoto() {
            return headphoto;
        }

        public void setHeadphoto(String headphoto) {
            this.headphoto = headphoto;
        }
    }
}
