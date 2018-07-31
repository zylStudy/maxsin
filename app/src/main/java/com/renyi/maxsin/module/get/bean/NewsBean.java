package com.renyi.maxsin.module.get.bean;

/**
 * Created by zhangyuliang on 2018/4/19.
 */

public class NewsBean {



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


        private String zan_status;
        private String sc_status;
        private String title;
        private String inputtime;
        private String catname;
        private String thumb;
        private String content;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getZan_status() {
            return zan_status;
        }

        public void setZan_status(String zan_status) {
            this.zan_status = zan_status;
        }

        public String getSc_status() {
            return sc_status;
        }

        public void setSc_status(String sc_status) {
            this.sc_status = sc_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
