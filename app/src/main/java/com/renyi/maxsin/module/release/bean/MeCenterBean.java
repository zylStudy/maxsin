package com.renyi.maxsin.module.release.bean;

/**
 * Created by zhangyuliang on 2018/6/22.
 */

public class MeCenterBean {
    /**
     * code : 800
     * message : 成功
     * data : {"id":1,"user_name":"SunFat","head_url":"http://renyi.mxsyzen.com/user_head/1526374505569404.jpg","renqi":"1","account":"12555555555","fans_num":"1","focus_num":"2","is_fans":"1"}
     */

    private String code;
    private String message;
    private DataBean data;

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
        /**
         * id : 1
         * user_name : SunFat
         * head_url : http://renyi.mxsyzen.com/user_head/1526374505569404.jpg
         * renqi : 1
         * account : 12555555555
         * fans_num : 1
         * focus_num : 2
         * is_fans : 1
         */

        private int id;
        private String user_name;
        private String head_url;
        private String renqi;
        private String account;
        private String fans_num;
        private String focus_num;
        private String is_fans;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }

        public String getRenqi() {
            return renqi;
        }

        public void setRenqi(String renqi) {
            this.renqi = renqi;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public String getFocus_num() {
            return focus_num;
        }

        public void setFocus_num(String focus_num) {
            this.focus_num = focus_num;
        }

        public String getIs_fans() {
            return is_fans;
        }

        public void setIs_fans(String is_fans) {
            this.is_fans = is_fans;
        }
    }
}
