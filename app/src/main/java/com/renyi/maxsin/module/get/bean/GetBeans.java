package com.renyi.maxsin.module.get.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/4/18.
 */

public class GetBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"total":"602","total_page":"61","per_page":"10","current_page":"1","get_list":[{"id":"3967","hits":"2","title":"Offer大爆炸丨如何get爱丁堡、皇艺研究生offer，来自神级学霸的申研成功经验","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/30.jpg","inputtime":"2018-04-18 01:58:16","catname":"最新活动"},{"id":"3931","hits":"24","title":"艺术留学专家评审团全国巡讲 | 最牛offer季，双击领取，艺术留学100%成功率！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/29.jpg","inputtime":"2018-04-13 03:53:47","catname":"最新活动"},{"id":"3920","hits":"4","title":"美行思远深圳 | 学服装设计，会创意立裁的人真的很能打。","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/28.jpg","inputtime":"2018-04-12 03:33:21","catname":"最新活动"},{"id":"3888","hits":"44","title":"伦艺直播课 | 切尔西艺术学院室内设计大师上线，教你五一之前搞定作品集！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/23.jpg","inputtime":"2018-04-09 06:09:54","catname":"最新活动"},{"id":"3877","hits":"57","title":"动画设计 Live | \u201c动画哈佛\u201d的老师帮你规划留学申请，简直爽飞了啊~","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/22.jpg","inputtime":"2018-04-08 06:47:46","catname":"最新活动"},{"id":"3876","hits":"10","title":"Offer大爆炸 | 2个月，4个项目，11个顶尖院校offer，被交互设计专业的他炸到无法fu吸！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/21.jpg","inputtime":"2018-04-08 01:53:28","catname":"最新活动"},{"id":"3840","hits":"7","title":"中央圣马丁和帕森斯设计学院大师强强联合，深度解答作品集制作难题","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/97.gif","inputtime":"2018-03-30 09:35:20","catname":"最新活动"},{"id":"3817","hits":"9","title":"直播课 | 纽约视觉艺术学院平面设计大师用干货喂饱你！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/92.jpg","inputtime":"2018-03-29 01:59:12","catname":"最新活动"},{"id":"3806","hits":"18","title":"室内设计讲座，意大利欧洲设计学院教授喊你听课","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/91.jpg","inputtime":"2018-03-28 01:34:43","catname":"最新活动"},{"id":"3795","hits":"125","title":"音乐留学大师行 | Maxsine × The Collective：一场与国际音乐大师的燃情音乐之旅","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/88.jpg","inputtime":"2018-03-27 06:39:37","catname":"最新活动"}]}
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
         * total : 602
         * total_page : 61
         * per_page : 10
         * current_page : 1
         * get_list : [{"id":"3967","hits":"2","title":"Offer大爆炸丨如何get爱丁堡、皇艺研究生offer，来自神级学霸的申研成功经验","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/30.jpg","inputtime":"2018-04-18 01:58:16","catname":"最新活动"},{"id":"3931","hits":"24","title":"艺术留学专家评审团全国巡讲 | 最牛offer季，双击领取，艺术留学100%成功率！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/29.jpg","inputtime":"2018-04-13 03:53:47","catname":"最新活动"},{"id":"3920","hits":"4","title":"美行思远深圳 | 学服装设计，会创意立裁的人真的很能打。","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/28.jpg","inputtime":"2018-04-12 03:33:21","catname":"最新活动"},{"id":"3888","hits":"44","title":"伦艺直播课 | 切尔西艺术学院室内设计大师上线，教你五一之前搞定作品集！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/23.jpg","inputtime":"2018-04-09 06:09:54","catname":"最新活动"},{"id":"3877","hits":"57","title":"动画设计 Live | \u201c动画哈佛\u201d的老师帮你规划留学申请，简直爽飞了啊~","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/22.jpg","inputtime":"2018-04-08 06:47:46","catname":"最新活动"},{"id":"3876","hits":"10","title":"Offer大爆炸 | 2个月，4个项目，11个顶尖院校offer，被交互设计专业的他炸到无法fu吸！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/21.jpg","inputtime":"2018-04-08 01:53:28","catname":"最新活动"},{"id":"3840","hits":"7","title":"中央圣马丁和帕森斯设计学院大师强强联合，深度解答作品集制作难题","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/97.gif","inputtime":"2018-03-30 09:35:20","catname":"最新活动"},{"id":"3817","hits":"9","title":"直播课 | 纽约视觉艺术学院平面设计大师用干货喂饱你！","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/92.jpg","inputtime":"2018-03-29 01:59:12","catname":"最新活动"},{"id":"3806","hits":"18","title":"室内设计讲座，意大利欧洲设计学院教授喊你听课","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/91.jpg","inputtime":"2018-03-28 01:34:43","catname":"最新活动"},{"id":"3795","hits":"125","title":"音乐留学大师行 | Maxsine × The Collective：一场与国际音乐大师的燃情音乐之旅","thumb":"http://www.mxsyzen.com/uploadfiles/image/201803/88.jpg","inputtime":"2018-03-27 06:39:37","catname":"最新活动"}]
         */

        private String total;
        private String total_page;
        private String per_page;
        private String current_page;
        private List<GetListBean> get_list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotal_page() {
            return total_page;
        }

        public void setTotal_page(String total_page) {
            this.total_page = total_page;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public List<GetListBean> getGet_list() {
            return get_list;
        }

        public void setGet_list(List<GetListBean> get_list) {
            this.get_list = get_list;
        }

        public static class GetListBean {
            /**
             * id : 3967
             * hits : 2
             * title : Offer大爆炸丨如何get爱丁堡、皇艺研究生offer，来自神级学霸的申研成功经验
             * thumb : http://www.mxsyzen.com/uploadfiles/image/201804/30.jpg
             * inputtime : 2018-04-18 01:58:16
             * catname : 最新活动
             */

            private String id;
            private String hits;
            private String title;
            private String thumb;
            private String inputtime;
            private String catname;
            private String leibie;
            private String actstart;
            private String address;
            private String baoming_count;
            private String like_count;
            private String keywords;
            private String catid;


            private String cover_img;
            private String description;
            private String tag_name;
            private String add_time;

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getBaoming_count() {
                return baoming_count;
            }

            public void setBaoming_count(String baoming_count) {
                this.baoming_count = baoming_count;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getActstart() {
                return actstart;
            }

            public void setActstart(String actstart) {
                this.actstart = actstart;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLeibie() {
                return leibie;
            }

            public void setLeibie(String leibie) {
                this.leibie = leibie;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHits() {
                return hits;
            }

            public void setHits(String hits) {
                this.hits = hits;
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
        }
    }
}
