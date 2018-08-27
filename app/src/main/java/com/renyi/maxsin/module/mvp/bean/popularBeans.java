package com.renyi.maxsin.module.mvp.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/7/27.
 */

public class PopularBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"pageInfo":{"count":16,"per_page":10,"current_page":1,"total_page":2},"list":[{"id":"2","zp_id":"2114","nickname":"tom","head_url":"","title":"Bksajbxkjasbkj","add_time":"2018-07-27","tag_name":"艺术,摄影,绘画,建筑,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1532680322075827_cover_.jpg","maxdate":"2018-07-27","is_focus":"0"},{"id":"1078","zp_id":"2106","nickname":"kobe","head_url":"http://renyi.mxsyzen.com/user_head/1531905471904273.png","title":"雪糕","add_time":"2018-07-25","tag_name":"光明光明,艺术,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1532484262474093_cover_.jpg","maxdate":"2018-07-24","is_focus":"0"},{"id":"133","zp_id":"2105","nickname":"","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"12","zp_id":"2104","nickname":"lei","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1115","zp_id":"2113","nickname":"jack","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1112","zp_id":"2110","nickname":"tiny","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1113","zp_id":"2111","nickname":"luna","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1114","zp_id":"2112","nickname":"jeff","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1079","zp_id":"2109","nickname":"","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1080","zp_id":"2102","nickname":"啦啦啦","head_url":"http://renyi.mxsyzen.com/user_head/1526369582725162.image","title":"今天好天气老狼请吃鸡","add_time":"2018-07-18","tag_name":"摄影,艺术,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531900598524625_cover_.jpg","maxdate":"","is_focus":"0"}]}
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
         * pageInfo : {"count":16,"per_page":10,"current_page":1,"total_page":2}
         * list : [{"id":"2","zp_id":"2114","nickname":"tom","head_url":"","title":"Bksajbxkjasbkj","add_time":"2018-07-27","tag_name":"艺术,摄影,绘画,建筑,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1532680322075827_cover_.jpg","maxdate":"2018-07-27","is_focus":"0"},{"id":"1078","zp_id":"2106","nickname":"kobe","head_url":"http://renyi.mxsyzen.com/user_head/1531905471904273.png","title":"雪糕","add_time":"2018-07-25","tag_name":"光明光明,艺术,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1532484262474093_cover_.jpg","maxdate":"2018-07-24","is_focus":"0"},{"id":"133","zp_id":"2105","nickname":"","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"12","zp_id":"2104","nickname":"lei","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1115","zp_id":"2113","nickname":"jack","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1112","zp_id":"2110","nickname":"tiny","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1113","zp_id":"2111","nickname":"luna","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1114","zp_id":"2112","nickname":"jeff","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1079","zp_id":"2109","nickname":"","head_url":"","title":"零零落落","add_time":"2018-07-18","tag_name":"摄影","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531902163764942_cover_.jpg","maxdate":"","is_focus":"0"},{"id":"1080","zp_id":"2102","nickname":"啦啦啦","head_url":"http://renyi.mxsyzen.com/user_head/1526369582725162.image","title":"今天好天气老狼请吃鸡","add_time":"2018-07-18","tag_name":"摄影,艺术,作品集","cover_img":"http://renyi.mxsyzen.com/publish_imgs/1531900598524625_cover_.jpg","maxdate":"","is_focus":"0"}]
         */

        private PageInfoBean pageInfo;
        private List<ListBean> list;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageInfoBean {
            /**
             * count : 16
             * per_page : 10
             * current_page : 1
             * total_page : 2
             */

            private int count;
            private int per_page;
            private int current_page;
            private int total_page;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public int getTotal_page() {
                return total_page;
            }

            public void setTotal_page(int total_page) {
                this.total_page = total_page;
            }
        }

        public static class ListBean {
            /**
             * id : 2
             * zp_id : 2114
             * nickname : tom
             * head_url :
             * title : Bksajbxkjasbkj
             * add_time : 2018-07-27
             * tag_name : 艺术,摄影,绘画,建筑,作品集
             * cover_img : http://renyi.mxsyzen.com/publish_imgs/1532680322075827_cover_.jpg
             * maxdate : 2018-07-27
             * is_focus : 0
             */

            private String id;
            private String user_id;
            private String zp_id;
            private String nickname;
            private String head_url;
            private String title;
            private String add_time;
            private String tag_name;
            private String cover_img;
            private String maxdate;
            private String is_focus;
            private String user_name;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id='" + id + '\'' +
                        ", zp_id='" + zp_id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", head_url='" + head_url + '\'' +
                        ", title='" + title + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", tag_name='" + tag_name + '\'' +
                        ", cover_img='" + cover_img + '\'' +
                        ", maxdate='" + maxdate + '\'' +
                        ", is_focus='" + is_focus + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZp_id() {
                return zp_id;
            }

            public void setZp_id(String zp_id) {
                this.zp_id = zp_id;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getMaxdate() {
                return maxdate;
            }

            public void setMaxdate(String maxdate) {
                this.maxdate = maxdate;
            }

            public String getIs_focus() {
                return is_focus;
            }

            public void setIs_focus(String is_focus) {
                this.is_focus = is_focus;
            }
        }
    }
}
