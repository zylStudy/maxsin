package com.renyi.maxsin.module.get.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/19.
 */

public class ActivityBean {


    /**
     * code : 800
     * message : 成功
     * data : {"title":"动画设计 Live | \u201c动画哈佛\u201d的老师帮你规划留学申请，简直爽飞了啊~","speaker":"","speakerphoto":"","address":"","inputtime":"2018-04-08 06:47:46","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/22.jpg","hits":"58","actstart":"","actend":"","courseurl":"","url_status":"0","touxiang_img":[{"img":"http://renyi.mxsyzen.com/img/5.jpg"},{"img":"http://renyi.mxsyzen.com/img/3.jpg"},{"img":"http://renyi.mxsyzen.com/img/2.jpg"},{"img":"http://renyi.mxsyzen.com/img/1.jpg"},{"img":"http://renyi.mxsyzen.com/img/4.jpg"}],"content":"111"}
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
         * title : 动画设计 Live | “动画哈佛”的老师帮你规划留学申请，简直爽飞了啊~
         * speaker :
         * speakerphoto :
         * address :
         * inputtime : 2018-04-08 06:47:46
         * thumb : http://www.mxsyzen.com/uploadfiles/image/201804/22.jpg
         * hits : 58
         * actstart :
         * actend :
         * courseurl :
         * url_status : 0
         * touxiang_img : [{"img":"http://renyi.mxsyzen.com/img/5.jpg"},{"img":"http://renyi.mxsyzen.com/img/3.jpg"},{"img":"http://renyi.mxsyzen.com/img/2.jpg"},{"img":"http://renyi.mxsyzen.com/img/1.jpg"},{"img":"http://renyi.mxsyzen.com/img/4.jpg"}]
         * content : 111
         */

        private String title;
        private String speaker;
        private String speakerphoto;
        private String address;
        private String inputtime;
        private String thumb;
        private String hits;
        private String actstart;
        private String actend;
        private String courseurl;
        private String url_status;
        private String content;
        private String description;
        private String shoucang_status;
        private String baoming_status;
        private List<TouxiangImgBean> touxiang_img;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShoucang_status() {
            return shoucang_status;
        }

        public void setShoucang_status(String shoucang_status) {
            this.shoucang_status = shoucang_status;
        }

        public String getBaoming_status() {
            return baoming_status;
        }

        public void setBaoming_status(String baoming_status) {
            this.baoming_status = baoming_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSpeaker() {
            return speaker;
        }

        public void setSpeaker(String speaker) {
            this.speaker = speaker;
        }

        public String getSpeakerphoto() {
            return speakerphoto;
        }

        public void setSpeakerphoto(String speakerphoto) {
            this.speakerphoto = speakerphoto;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getActstart() {
            return actstart;
        }

        public void setActstart(String actstart) {
            this.actstart = actstart;
        }

        public String getActend() {
            return actend;
        }

        public void setActend(String actend) {
            this.actend = actend;
        }

        public String getCourseurl() {
            return courseurl;
        }

        public void setCourseurl(String courseurl) {
            this.courseurl = courseurl;
        }

        public String getUrl_status() {
            return url_status;
        }

        public void setUrl_status(String url_status) {
            this.url_status = url_status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<TouxiangImgBean> getTouxiang_img() {
            return touxiang_img;
        }

        public void setTouxiang_img(List<TouxiangImgBean> touxiang_img) {
            this.touxiang_img = touxiang_img;
        }

        public static class TouxiangImgBean {
            /**
             * img : http://renyi.mxsyzen.com/img/5.jpg
             */

            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
