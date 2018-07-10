package com.renyi.maxsin.module.me.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/7/10.
 */

public class FollowBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"total_page":"3","data":[{"id":"1","user_name":"刘美娜","head_url":"http://renyi.mxsyzen.com/user_head/1529573101239565.image","content_count":"1017","work_count":"1020","flag":"1"},{"id":"2","user_name":"段世成","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"3","user_name":"潘相妤","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"4","user_name":"姜奕西","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"19","user_name":"牛二","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"20","user_name":"柳帅","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"21","user_name":"石欣月","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"23","user_name":"徐梓涵","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"24","user_name":"姜蕴纯","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"25","user_name":"金羽宣","head_url":"","content_count":"0","work_count":"0","flag":"0"}]}
     */

    private String code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total_page : 3
         * data : [{"id":"1","user_name":"刘美娜","head_url":"http://renyi.mxsyzen.com/user_head/1529573101239565.image","content_count":"1017","work_count":"1020","flag":"1"},{"id":"2","user_name":"段世成","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"3","user_name":"潘相妤","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"4","user_name":"姜奕西","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"19","user_name":"牛二","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"20","user_name":"柳帅","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"21","user_name":"石欣月","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"23","user_name":"徐梓涵","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"24","user_name":"姜蕴纯","head_url":"","content_count":"0","work_count":"0","flag":"0"},{"id":"25","user_name":"金羽宣","head_url":"","content_count":"0","work_count":"0","flag":"0"}]
         */

        private String total_page;
        private List<DataBean> data;

        public String getTotal_page() {
            return total_page;
        }

        public void setTotal_page(String total_page) {
            this.total_page = total_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * user_name : 刘美娜
             * head_url : http://renyi.mxsyzen.com/user_head/1529573101239565.image
             * content_count : 1017
             * work_count : 1020
             * flag : 1
             */

            private String id;
            private String user_name;
            private String head_url;
            private String content_count;
            private String work_count;
            private String flag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getContent_count() {
                return content_count;
            }

            public void setContent_count(String content_count) {
                this.content_count = content_count;
            }

            public String getWork_count() {
                return work_count;
            }

            public void setWork_count(String work_count) {
                this.work_count = work_count;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }
        }
    }
}
