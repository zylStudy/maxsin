package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/28.
 */

public class TagBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"user_tags":[{"id":"1","tag_name":"摄影","sort_num":0}],"tuijian":[{"id":"1","tag_name":"摄影","sort_num":0},{"id":"2","tag_name":"艺术","sort_num":0},{"id":"3","tag_name":"电影","sort_num":0},{"id":"4","tag_name":"绘画","sort_num":0}]}
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
        private List<UserTagsBean> user_tags;
        private List<TuijianBean> tuijian;

        public List<UserTagsBean> getUser_tags() {
            return user_tags;
        }

        public void setUser_tags(List<UserTagsBean> user_tags) {
            this.user_tags = user_tags;
        }

        public List<TuijianBean> getTuijian() {
            return tuijian;
        }

        public void setTuijian(List<TuijianBean> tuijian) {
            this.tuijian = tuijian;
        }

        public static class UserTagsBean {
            /**
             * id : 1
             * tag_name : 摄影
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

        public static class TuijianBean {
            /**
             * id : 1
             * tag_name : 摄影
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
}
