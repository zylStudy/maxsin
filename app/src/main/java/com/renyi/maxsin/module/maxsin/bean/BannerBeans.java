package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/28.
 */

public class BannerBeans {
    /**
     * code : 800
     * message : 成功
     * data : [{"id":"1","img_src":"http://renyi.mxsyzen.com/publish_imgs/1529636600530969_1_.jpg"},{"id":"2","img_src":"http://renyi.mxsyzen.com/publish_imgs/1529636600244152_4_.png"},{"id":"3","img_src":"http://renyi.mxsyzen.com/publish_imgs/1529636600847564_5_.png"}]
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
         * id : 1
         * img_src : http://renyi.mxsyzen.com/publish_imgs/1529636600530969_1_.jpg
         */

        private String id;
        private String img_src;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg_src() {
            return img_src;
        }

        public void setImg_src(String img_src) {
            this.img_src = img_src;
        }
    }
}
