package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/24.
 */

public class TeacherBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"pageInfo":{"count":16,"per_page":"5","current_page":1,"total_page":4},"list":[{"id":"4035","name":"Rita","major":"平面设计、插画","photo":"http://www.mxsyzen.com/uploadfiles/image/201805/123.png","educollege":"爱丁堡大学","experiencea":"曾任深圳市光合文化发展有限公司平面设计师","experienceb":"2年多艺术教育机构作品集辅导经验","experiencec":""}]}
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
         * pageInfo : {"count":16,"per_page":"5","current_page":1,"total_page":4}
         * list : [{"id":"4035","name":"Rita","major":"平面设计、插画","photo":"http://www.mxsyzen.com/uploadfiles/image/201805/123.png","educollege":"爱丁堡大学","experiencea":"曾任深圳市光合文化发展有限公司平面设计师","experienceb":"2年多艺术教育机构作品集辅导经验","experiencec":""}]
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
             * per_page : 5
             * current_page : 1
             * total_page : 4
             */

            private int count;
            private String per_page;
            private int current_page;
            private int total_page;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getPer_page() {
                return per_page;
            }

            public void setPer_page(String per_page) {
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
             * id : 4035
             * name : Rita
             * major : 平面设计、插画
             * photo : http://www.mxsyzen.com/uploadfiles/image/201805/123.png
             * educollege : 爱丁堡大学
             * experiencea : 曾任深圳市光合文化发展有限公司平面设计师
             * experienceb : 2年多艺术教育机构作品集辅导经验
             * experiencec :
             */

            private String id;
            private String name;
            private String major;
            private String photo;
            private String educollege;
            private String desc;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getEducollege() {
                return educollege;
            }

            public void setEducollege(String educollege) {
                this.educollege = educollege;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}
