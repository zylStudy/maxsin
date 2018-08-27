package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/27.
 */

public class NewsBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"pageInfo":{"count":1353,"per_page":5,"current_page":1,"total_page":271},"list":[{"id":"2","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q33642H5.png","title":"艺术留学要考虑四个方面","inputtime":"2015-04-08","hits":"383","catname":"留学指南"},{"id":"21","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q3595R91.jpg","title":"荒蛮的现实，荒蛮的艺术","inputtime":"2015-04-08","hits":"267","catname":"留学指南"},{"id":"26","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q40405449.jpg","title":"谈艺术不能架空现实","inputtime":"2015-04-08","hits":"204","catname":"留学指南"},{"id":"27","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q40540406.jpg","title":"艺术,个性,人道","inputtime":"2015-04-08","hits":"306","catname":"留学指南"},{"id":"33","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q41112c5.jpg","title":"美国艺术留学申请要求","inputtime":"2015-04-08","hits":"342","catname":"留学指南"}]}
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
         * pageInfo : {"count":1353,"per_page":5,"current_page":1,"total_page":271}
         * list : [{"id":"2","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q33642H5.png","title":"艺术留学要考虑四个方面","inputtime":"2015-04-08","hits":"383","catname":"留学指南"},{"id":"21","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q3595R91.jpg","title":"荒蛮的现实，荒蛮的艺术","inputtime":"2015-04-08","hits":"267","catname":"留学指南"},{"id":"26","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q40405449.jpg","title":"谈艺术不能架空现实","inputtime":"2015-04-08","hits":"204","catname":"留学指南"},{"id":"27","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q40540406.jpg","title":"艺术,个性,人道","inputtime":"2015-04-08","hits":"306","catname":"留学指南"},{"id":"33","thumb":"http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q41112c5.jpg","title":"美国艺术留学申请要求","inputtime":"2015-04-08","hits":"342","catname":"留学指南"}]
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
             * count : 1353
             * per_page : 5
             * current_page : 1
             * total_page : 271
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
             * thumb : http://www.mxsyzen.com/uploadfiles/news/150408/1-15040Q33642H5.png
             * title : 艺术留学要考虑四个方面
             * inputtime : 2015-04-08
             * hits : 383
             * catname : 留学指南
             */

            private String id;
            private String thumb;
            private String title;
            private String inputtime;
            private String hits;
            private String catname;
            private String description;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public String getInputtime() {
                return inputtime;
            }

            public void setInputtime(String inputtime) {
                this.inputtime = inputtime;
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
