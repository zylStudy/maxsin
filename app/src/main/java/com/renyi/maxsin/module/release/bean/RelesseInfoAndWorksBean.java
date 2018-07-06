package com.renyi.maxsin.module.release.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/6/22.
 */

public class RelesseInfoAndWorksBean {
    /**
     * code : 800
     * message : 成功
     * data : {"total_count":"1024","total_page":"103","page_size":"10","cur_page":"2","get_list":[{"id":"2028","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396629676230.jpg","tag_name":"交互，平面","title":"罗德岛设计学院录取要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2026","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529397212996629.jpg","tag_name":"交互，平面","title":"哈佛建筑学院怎么样","description":"描述","add_time":"2018-06-13"},{"id":"2024","cover_img":"http://www.mxsyzen.com/uploadfiles/image/201803/81.jpg","tag_name":"交互，平面","title":"作品集中那些令人惊艳的创意都是怎么来的？","description":"描述","add_time":"2018-06-13"},{"id":"2022","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396940421139.jpg","tag_name":"交互，平面","title":"纽约视觉艺术学院申请要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2020","cover_img":"http://www.mxsyzen.com/uploadfiles/20180418/1524038006153998.jpg","tag_name":"交互，平面","title":"过来人教你芝加哥艺术学院作品集要求","description":"描述","add_time":"2018-06-13"},{"id":"2018","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396629676230.jpg","tag_name":"交互，平面","title":"罗德岛设计学院录取要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2016","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529397212996629.jpg","tag_name":"交互，平面","title":"哈佛建筑学院怎么样","description":"描述","add_time":"2018-06-13"},{"id":"2014","cover_img":"http://www.mxsyzen.com/uploadfiles/image/201803/81.jpg","tag_name":"交互，平面","title":"作品集中那些令人惊艳的创意都是怎么来的？","description":"描述","add_time":"2018-06-13"},{"id":"2012","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396940421139.jpg","tag_name":"交互，平面","title":"纽约视觉艺术学院申请要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2010","cover_img":"http://www.mxsyzen.com/uploadfiles/20180418/1524038006153998.jpg","tag_name":"交互，平面","title":"过来人教你芝加哥艺术学院作品集要求","description":"描述","add_time":"2018-06-13"}]}
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
         * total_count : 1024
         * total_page : 103
         * page_size : 10
         * cur_page : 2
         * get_list : [{"id":"2028","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396629676230.jpg","tag_name":"交互，平面","title":"罗德岛设计学院录取要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2026","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529397212996629.jpg","tag_name":"交互，平面","title":"哈佛建筑学院怎么样","description":"描述","add_time":"2018-06-13"},{"id":"2024","cover_img":"http://www.mxsyzen.com/uploadfiles/image/201803/81.jpg","tag_name":"交互，平面","title":"作品集中那些令人惊艳的创意都是怎么来的？","description":"描述","add_time":"2018-06-13"},{"id":"2022","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396940421139.jpg","tag_name":"交互，平面","title":"纽约视觉艺术学院申请要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2020","cover_img":"http://www.mxsyzen.com/uploadfiles/20180418/1524038006153998.jpg","tag_name":"交互，平面","title":"过来人教你芝加哥艺术学院作品集要求","description":"描述","add_time":"2018-06-13"},{"id":"2018","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396629676230.jpg","tag_name":"交互，平面","title":"罗德岛设计学院录取要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2016","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529397212996629.jpg","tag_name":"交互，平面","title":"哈佛建筑学院怎么样","description":"描述","add_time":"2018-06-13"},{"id":"2014","cover_img":"http://www.mxsyzen.com/uploadfiles/image/201803/81.jpg","tag_name":"交互，平面","title":"作品集中那些令人惊艳的创意都是怎么来的？","description":"描述","add_time":"2018-06-13"},{"id":"2012","cover_img":"http://www.mxsyzen.com/uploadfiles/20180619/1529396940421139.jpg","tag_name":"交互，平面","title":"纽约视觉艺术学院申请要求有哪些","description":"描述","add_time":"2018-06-13"},{"id":"2010","cover_img":"http://www.mxsyzen.com/uploadfiles/20180418/1524038006153998.jpg","tag_name":"交互，平面","title":"过来人教你芝加哥艺术学院作品集要求","description":"描述","add_time":"2018-06-13"}]
         */

        private String total_count;
        private String total_page;
        private String page_size;
        private String cur_page;
        private List<GetListBean> get_list;

        public String getTotal_count() {
            return total_count;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public String getTotal_page() {
            return total_page;
        }

        public void setTotal_page(String total_page) {
            this.total_page = total_page;
        }

        public String getPage_size() {
            return page_size;
        }

        public void setPage_size(String page_size) {
            this.page_size = page_size;
        }

        public String getCur_page() {
            return cur_page;
        }

        public void setCur_page(String cur_page) {
            this.cur_page = cur_page;
        }

        public List<GetListBean> getGet_list() {
            return get_list;
        }

        public void setGet_list(List<GetListBean> get_list) {
            this.get_list = get_list;
        }

        public static class GetListBean {
            /**
             * id : 2028
             * cover_img : http://www.mxsyzen.com/uploadfiles/20180619/1529396629676230.jpg
             * tag_name : 交互，平面
             * title : 罗德岛设计学院录取要求有哪些
             * description : 描述
             * add_time : 2018-06-13
             */

            private String id;
            private String cover_img;
            private String tag_name;
            private String title;
            private String description;
            private String add_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }
        }
    }
}
