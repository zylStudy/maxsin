package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/27.
 */

public class ActivityBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"pageInfo":{"count":2,"per_page":5,"current_page":1,"total_page":1},"list":[{"id":"3931","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/29.jpg","title":"艺术留学专家评审团全国巡讲 | 最牛offer季，双击领取，艺术留学100%成功率！","description":"揭秘连击名校offer\u201c内幕\u201d，前方高能，非战斗人员请速撤离！","actstart":"2018-04-21","actend":"2018-04-28","address":"","hits":"204","catname":"最新活动"},{"id":"5587","thumb":"http://www.mxsyzen.com/uploadfiles/image/201808/52.jpg","title":"9月开学季 | 乱花渐欲迷人眼，艺术留学就看这些点！","description":"【艺术留学百问百答】看完延禧攻略，海外名校进阶攻略了解一下？","actstart":"2018-09-08","actend":"2018-09-15","address":"","hits":"107","catname":"最新活动"}]}
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
         * pageInfo : {"count":2,"per_page":5,"current_page":1,"total_page":1}
         * list : [{"id":"3931","thumb":"http://www.mxsyzen.com/uploadfiles/image/201804/29.jpg","title":"艺术留学专家评审团全国巡讲 | 最牛offer季，双击领取，艺术留学100%成功率！","description":"揭秘连击名校offer\u201c内幕\u201d，前方高能，非战斗人员请速撤离！","actstart":"2018-04-21","actend":"2018-04-28","address":"","hits":"204","catname":"最新活动"},{"id":"5587","thumb":"http://www.mxsyzen.com/uploadfiles/image/201808/52.jpg","title":"9月开学季 | 乱花渐欲迷人眼，艺术留学就看这些点！","description":"【艺术留学百问百答】看完延禧攻略，海外名校进阶攻略了解一下？","actstart":"2018-09-08","actend":"2018-09-15","address":"","hits":"107","catname":"最新活动"}]
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
             * count : 2
             * per_page : 5
             * current_page : 1
             * total_page : 1
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
             * id : 3931
             * thumb : http://www.mxsyzen.com/uploadfiles/image/201804/29.jpg
             * title : 艺术留学专家评审团全国巡讲 | 最牛offer季，双击领取，艺术留学100%成功率！
             * description : 揭秘连击名校offer“内幕”，前方高能，非战斗人员请速撤离！
             * actstart : 2018-04-21
             * actend : 2018-04-28
             * address :
             * hits : 204
             * catname : 最新活动
             */

            private String id;
            private String thumb;
            private String title;
            private String description;
            private String actstart;
            private String actend;
            private String address;
            private String hits;
            private String catname;
            private String sc_num;
            private String bm_num;

            public String getSc_num() {
                return sc_num;
            }

            public void setSc_num(String sc_num) {
                this.sc_num = sc_num;
            }

            public String getBm_num() {
                return bm_num;
            }

            public void setBm_num(String bm_num) {
                this.bm_num = bm_num;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getHits() {
                return hits;
            }

            public void setHits(String hits) {
                this.hits = hits;
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
