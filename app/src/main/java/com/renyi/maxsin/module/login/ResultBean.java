package com.renyi.maxsin.module.login;

/**
 * Created by zhangyuliang on 2018/4/27.
 */

public class ResultBean {
    /**
     * code : 800
     * message : 成功
     * data : {"has_mobile_flag":"1","u_id":"1","is_stu":"2"}
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
         * has_mobile_flag : 1
         * u_id : 1
         * is_stu : 2
         */

        private String has_mobile_flag;
        private String u_id;
        private String is_stu;


        private String user_name;
        private String head_url;
        private String sex;
        private String account;
        private String code;
        private String stu_id;


        private String focus_num;
        private String fans_num;
        private String renqi;
        private String sid;
        private String add_flag;

        public String getAdd_flag() {
            return add_flag;
        }

        public void setAdd_flag(String add_flag) {
            this.add_flag = add_flag;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getFocus_num() {
            return focus_num;
        }

        public void setFocus_num(String focus_num) {
            this.focus_num = focus_num;
        }

        public String getFans_num() {
            return fans_num;
        }

        public void setFans_num(String fans_num) {
            this.fans_num = fans_num;
        }

        public String getRenqi() {
            return renqi;
        }

        public void setRenqi(String renqi) {
            this.renqi = renqi;
        }

        public String getStu_id() {
            return stu_id;
        }

        public void setStu_id(String stu_id) {
            this.stu_id = stu_id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHas_mobile_flag() {
            return has_mobile_flag;
        }

        public void setHas_mobile_flag(String has_mobile_flag) {
            this.has_mobile_flag = has_mobile_flag;
        }

        public String getU_id() {
            return u_id;
        }

        public void setU_id(String u_id) {
            this.u_id = u_id;
        }

        public String getIs_stu() {
            return is_stu;
        }

        public void setIs_stu(String is_stu) {
            this.is_stu = is_stu;
        }
    }
}
