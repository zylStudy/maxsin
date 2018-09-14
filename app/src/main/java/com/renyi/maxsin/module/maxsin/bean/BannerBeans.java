package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/28.
 */

public class BannerBeans {

    /**
     * code : 800
     * message : 成功
     * data : [{"id":"3","img_src":"http://renyi.mxsyzen.com/img/app-banner-img.jpg","type":"1","type_id":"5587","title":"9月开学季 | 乱花渐欲迷人眼，艺术留学就看这些点！"},{"id":"5","img_src":"http://renyi.mxsyzen.com/img/app-banner-img3.jpg","type":"1","type_id":"6217","title":"MAXSINE × SAIC | 错过再等一年\u2026也未必再有！芝加哥艺术学院招生官见面会限时开启！"},{"id":"6","img_src":"http://renyi.mxsyzen.com/img/web.jpg","type":"3","type_id":"https://wj.qq.com/s/2689724/9934/","title":""}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    @Override
    public String toString() {
        return super.toString();
    }

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
         * id : 3
         * img_src : http://renyi.mxsyzen.com/img/app-banner-img.jpg
         * type : 1
         * type_id : 5587
         * title : 9月开学季 | 乱花渐欲迷人眼，艺术留学就看这些点！
         */

        private String id;
        private String img_src;
        private String type;
        private String type_id;




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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

    }
}
