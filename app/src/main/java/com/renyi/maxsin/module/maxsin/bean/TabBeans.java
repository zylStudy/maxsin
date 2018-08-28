package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/28.
 */

public class TabBeans {
    /**
     * code : 800
     * message : 成功
     * data : [{"id":"5","tag_name":"建筑","sort_num":0},{"id":"6","tag_name":"作品集","sort_num":0}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * tag_name : 建筑
         * sort_num : 0
         */

        private String id;
        private String tag_name;
        private int sort_num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public int getSort_num() {
            return sort_num;
        }

        public void setSort_num(int sort_num) {
            this.sort_num = sort_num;
        }
    }
}
