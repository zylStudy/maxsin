package com.renyi.maxsin.module.mvp.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/7/24.
 */

public class MvpRecommendBean {
    /**
     * code : 800
     * message : 成功
     * data : [{"id":"12","nickname":"lei","head_url":"","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","is_focus":"1"},{"id":"133","nickname":"","head_url":"","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","is_focus":"1"}]
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
         * id : 12
         * nickname : lei
         * head_url :
         * cover_img : http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg
         * is_focus : 1
         */

        private String id;
        private String nickname;
        private String head_url;
        private String cover_img;
        private String is_focus;
        private String total;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHead_url() {
            return head_url;
        }

        public void setHead_url(String head_url) {
            this.head_url = head_url;
        }

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(String is_focus) {
            this.is_focus = is_focus;
        }
    }
}
