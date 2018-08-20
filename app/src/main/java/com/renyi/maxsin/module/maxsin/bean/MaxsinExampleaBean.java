package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/20.
 */

public class MaxsinExampleaBean {
    /**
     * code : 800
     * message : 成功
     * data : {"total":"12","total_page":3,"per_page":5,"current_page":"1","caselist":[{"id":"810","title":"胡同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/23.png","keywords":"摄影","luquyuanxiao":"旧金山艺术大学","country":"美国","jiangxuejin":"","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/34.jpg"},{"id":"821","title":"王同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/120.jpg","keywords":"平面设计","luquyuanxiao":"纽约视觉艺术学院、马里兰艺术学院、萨凡纳艺术与设计学院","country":"美国","jiangxuejin":"","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/121.jpg"},{"id":"824","title":"董同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/149.jpg","keywords":"平面设计","luquyuanxiao":"萨凡纳艺术设计学院（13000奖学金）、普瑞特艺术学院（ 11000美金奖学金）","country":"美国","jiangxuejin":"13000美元","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/150.jpg"},{"id":"973","title":"王同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201612/468.jpg","keywords":"平面设计","luquyuanxiao":"芝加哥艺术学院、纽约视觉设计学院、马里兰艺术学院、普瑞特艺术学院（每年18000奖学金）","country":"美国","jiangxuejin":"72000奖学金","cover":"http://www.mxsyzen.com/uploadfiles/image/201612/629.jpg"},{"id":"1347","title":"姚同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201807/325.jpg","keywords":"平面设计","luquyuanxiao":"加州艺术学院（60000美金）、纽约视觉艺术学院（20000美金）、麻省艺术和设计学院（40000美金）、芝加哥艺术学院、萨凡纳艺术学院（32000美金奖学金）、普瑞特艺术学院","country":"美国","jiangxuejin":"60000美金","cover":"http://www.mxsyzen.com/uploadfiles/image/201705/369.jpg"}]}
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
         * total : 12
         * total_page : 3
         * per_page : 5
         * current_page : 1
         * caselist : [{"id":"810","title":"胡同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/23.png","keywords":"摄影","luquyuanxiao":"旧金山艺术大学","country":"美国","jiangxuejin":"","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/34.jpg"},{"id":"821","title":"王同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/120.jpg","keywords":"平面设计","luquyuanxiao":"纽约视觉艺术学院、马里兰艺术学院、萨凡纳艺术与设计学院","country":"美国","jiangxuejin":"","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/121.jpg"},{"id":"824","title":"董同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/149.jpg","keywords":"平面设计","luquyuanxiao":"萨凡纳艺术设计学院（13000奖学金）、普瑞特艺术学院（ 11000美金奖学金）","country":"美国","jiangxuejin":"13000美元","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/150.jpg"},{"id":"973","title":"王同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201612/468.jpg","keywords":"平面设计","luquyuanxiao":"芝加哥艺术学院、纽约视觉设计学院、马里兰艺术学院、普瑞特艺术学院（每年18000奖学金）","country":"美国","jiangxuejin":"72000奖学金","cover":"http://www.mxsyzen.com/uploadfiles/image/201612/629.jpg"},{"id":"1347","title":"姚同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201807/325.jpg","keywords":"平面设计","luquyuanxiao":"加州艺术学院（60000美金）、纽约视觉艺术学院（20000美金）、麻省艺术和设计学院（40000美金）、芝加哥艺术学院、萨凡纳艺术学院（32000美金奖学金）、普瑞特艺术学院","country":"美国","jiangxuejin":"60000美金","cover":"http://www.mxsyzen.com/uploadfiles/image/201705/369.jpg"}]
         */

        private String total;
        private int total_page;
        private int per_page;
        private String current_page;
        private List<CaselistBean> caselist;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public List<CaselistBean> getCaselist() {
            return caselist;
        }

        public void setCaselist(List<CaselistBean> caselist) {
            this.caselist = caselist;
        }

        public static class CaselistBean {
            /**
             * id : 810
             * title : 胡同学
             * thumb : http://www.mxsyzen.com/uploadfiles/image/201610/23.png
             * keywords : 摄影
             * luquyuanxiao : 旧金山艺术大学
             * country : 美国
             * jiangxuejin :
             * cover : http://www.mxsyzen.com/uploadfiles/image/201610/34.jpg
             */

            private String id;
            private String title;
            private String thumb;
            private String keywords;
            private String luquyuanxiao;
            private String country;
            private String jiangxuejin;
            private String cover;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getLuquyuanxiao() {
                return luquyuanxiao;
            }

            public void setLuquyuanxiao(String luquyuanxiao) {
                this.luquyuanxiao = luquyuanxiao;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getJiangxuejin() {
                return jiangxuejin;
            }

            public void setJiangxuejin(String jiangxuejin) {
                this.jiangxuejin = jiangxuejin;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
