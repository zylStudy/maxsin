package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/20.
 */

public class MaxsinUniversityRankBeans {

    /**
     * code : 800
     * message : 成功
     * data : {"pageInfo":{"count":181,"per_page":5,"current_page":1,"total_page":37},"colleglist":[{"id":"881","chname":"纽卡斯尔大学","enname":"Newcastle University","location":"英国英格兰泰恩河畔纽卡斯尔市","country":"英国_United Kingdom","applydifficulty":"困难","major":"建筑设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/57.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/91.jpg","applyendtime":"12月31日","degree":"学士,硕士","toefl":"","ielts":"6.5-7.5"},{"id":"882","chname":"雷丁大学","enname":"University of Reading","location":"伯克郡雷丁市","country":"英国_United Kingdom","applydifficulty":"困难","major":"平面设计|影视制作","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/53.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201612/51.jpg","applyendtime":"1月1日","degree":"学士,硕士","toefl":"","ielts":"6.5-7.0"},{"id":"883","chname":"伦敦大学学院","enname":"University College London","location":"英国伦敦高尔街","country":"英国_United Kingdom","applydifficulty":"困难","major":"景观设计|纯艺术","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/61.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/137.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"92","ielts":"6.5"},{"id":"884","chname":"金斯顿大学","enname":"Kingston University","location":"英国伦敦泰晤士河畔","country":"英国_United Kingdom","applydifficulty":"困难","major":"室内设计|摄影","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/67.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/101.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"},{"id":"885","chname":"考文垂大学","enname":"Coventry University","location":"英国考文垂普里奥里街","country":"英国_United Kingdom","applydifficulty":"困难","major":"平面设计|纯艺术|展示设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/71.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/135.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"}]}
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
         * pageInfo : {"count":181,"per_page":5,"current_page":1,"total_page":37}
         * colleglist : [{"id":"881","chname":"纽卡斯尔大学","enname":"Newcastle University","location":"英国英格兰泰恩河畔纽卡斯尔市","country":"英国_United Kingdom","applydifficulty":"困难","major":"建筑设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/57.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/91.jpg","applyendtime":"12月31日","degree":"学士,硕士","toefl":"","ielts":"6.5-7.5"},{"id":"882","chname":"雷丁大学","enname":"University of Reading","location":"伯克郡雷丁市","country":"英国_United Kingdom","applydifficulty":"困难","major":"平面设计|影视制作","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/53.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201612/51.jpg","applyendtime":"1月1日","degree":"学士,硕士","toefl":"","ielts":"6.5-7.0"},{"id":"883","chname":"伦敦大学学院","enname":"University College London","location":"英国伦敦高尔街","country":"英国_United Kingdom","applydifficulty":"困难","major":"景观设计|纯艺术","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/61.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/137.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"92","ielts":"6.5"},{"id":"884","chname":"金斯顿大学","enname":"Kingston University","location":"英国伦敦泰晤士河畔","country":"英国_United Kingdom","applydifficulty":"困难","major":"室内设计|摄影","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/67.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/101.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"},{"id":"885","chname":"考文垂大学","enname":"Coventry University","location":"英国考文垂普里奥里街","country":"英国_United Kingdom","applydifficulty":"困难","major":"平面设计|纯艺术|展示设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/71.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/135.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"}]
         */
        private int total_page;
        private PageInfoBean pageInfo;
        private List<ColleglistBean> colleglist;

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<ColleglistBean> getColleglist() {
            return colleglist;
        }

        public void setColleglist(List<ColleglistBean> colleglist) {
            this.colleglist = colleglist;
        }

        public static class PageInfoBean {
            /**
             * count : 181
             * per_page : 5
             * current_page : 1
             * total_page : 37
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

        public static class ColleglistBean {
            /**
             * id : 881
             * chname : 纽卡斯尔大学
             * enname : Newcastle University
             * location : 英国英格兰泰恩河畔纽卡斯尔市
             * country : 英国_United Kingdom
             * applydifficulty : 困难
             * major : 建筑设计
             * logopic : http://www.mxsyzen.com/uploadfiles/image/201612/57.jpg
             * showpic : http://www.mxsyzen.com/uploadfiles/image/201702/91.jpg
             * applyendtime : 12月31日
             * degree : 学士,硕士
             * toefl :
             * ielts : 6.5-7.5
             */

            private String id;
            private String chname;
            private String enname;
            private String location;
            private String country;
            private String applydifficulty;
            private String major;
            private String logopic;
            private String showpic;
            private String applyendtime;
            private String degree;
            private String toefl;
            private String ielts;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getChname() {
                return chname;
            }

            public void setChname(String chname) {
                this.chname = chname;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getApplydifficulty() {
                return applydifficulty;
            }

            public void setApplydifficulty(String applydifficulty) {
                this.applydifficulty = applydifficulty;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getLogopic() {
                return logopic;
            }

            public void setLogopic(String logopic) {
                this.logopic = logopic;
            }

            public String getShowpic() {
                return showpic;
            }

            public void setShowpic(String showpic) {
                this.showpic = showpic;
            }

            public String getApplyendtime() {
                return applyendtime;
            }

            public void setApplyendtime(String applyendtime) {
                this.applyendtime = applyendtime;
            }

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public String getToefl() {
                return toefl;
            }

            public void setToefl(String toefl) {
                this.toefl = toefl;
            }

            public String getIelts() {
                return ielts;
            }

            public void setIelts(String ielts) {
                this.ielts = ielts;
            }
        }
    }
}
