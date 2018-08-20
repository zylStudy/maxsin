package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/20.
 */

public class MaxsinUniversityRankBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"total":"313","total_page":63,"per_page":5,"current_page":"1","colleglist":[{"id":"940","chname":"帕森斯设计学院","enname":"Parsons The New School for Design","location":"美国纽约州纽约西12街66号","country":"美国_United States","applydifficulty":"困难","major":"服装设计|室内设计|插画设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/312.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/29.jpg","applyendtime":"11月1日、常规：1月15日","degree":"学士,硕士","toefl":"92","ielts":"7.0"},{"id":"937","chname":"罗德岛设计学院","enname":"Rhode Island School of Design","location":"美国罗德岛州普罗维登斯市学院路2号","country":"美国_United States","applydifficulty":"困难","major":"工业设计|多媒体设计|陶艺|摄影|建筑设...","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/300.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201612/298.jpg","applyendtime":"11月1日 常规：2月1日","degree":"学士,硕士","toefl":"93","ielts":"6.5"},{"id":"931","chname":"中央圣马丁艺术与设计学院","enname":"Central Saint Martins","location":"英国伦敦国王十字格拉纳里广场一号格拉纳里大楼","country":"英国_United Kingdom","applydifficulty":"困难","major":"服装设计|室内设计|插画设计|平面设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/276.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/107.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"},{"id":"900","chname":"皇家艺术学院","enname":"Royal College of Art","location":"英国伦敦肯辛顿戈尔","country":"英国_United Kingdom","applydifficulty":"困难","major":"服装设计|室内设计|工业设计|动画设计|...","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/130.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/21.jpg","applyendtime":"1月13日","degree":"硕士","toefl":"","ielts":"6.5"},{"id":"941","chname":"纽约时装学院","enname":"Fashion Institute of Technology","location":"美国纽约州纽约市第7大道27街","country":"美国_United States","applydifficulty":"困难","major":"服装设计|插画设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/316.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/25.jpg","applyendtime":"10月1日、常规：1月1日","degree":"学士,硕士","toefl":"80","ielts":"6.5"}]}
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
         * total : 313
         * total_page : 63
         * per_page : 5
         * current_page : 1
         * colleglist : [{"id":"940","chname":"帕森斯设计学院","enname":"Parsons The New School for Design","location":"美国纽约州纽约西12街66号","country":"美国_United States","applydifficulty":"困难","major":"服装设计|室内设计|插画设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/312.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/29.jpg","applyendtime":"11月1日、常规：1月15日","degree":"学士,硕士","toefl":"92","ielts":"7.0"},{"id":"937","chname":"罗德岛设计学院","enname":"Rhode Island School of Design","location":"美国罗德岛州普罗维登斯市学院路2号","country":"美国_United States","applydifficulty":"困难","major":"工业设计|多媒体设计|陶艺|摄影|建筑设...","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/300.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201612/298.jpg","applyendtime":"11月1日 常规：2月1日","degree":"学士,硕士","toefl":"93","ielts":"6.5"},{"id":"931","chname":"中央圣马丁艺术与设计学院","enname":"Central Saint Martins","location":"英国伦敦国王十字格拉纳里广场一号格拉纳里大楼","country":"英国_United Kingdom","applydifficulty":"困难","major":"服装设计|室内设计|插画设计|平面设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/276.png","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/107.jpg","applyendtime":"1月15日","degree":"学士,硕士","toefl":"","ielts":"6.0"},{"id":"900","chname":"皇家艺术学院","enname":"Royal College of Art","location":"英国伦敦肯辛顿戈尔","country":"英国_United Kingdom","applydifficulty":"困难","major":"服装设计|室内设计|工业设计|动画设计|...","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/130.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/21.jpg","applyendtime":"1月13日","degree":"硕士","toefl":"","ielts":"6.5"},{"id":"941","chname":"纽约时装学院","enname":"Fashion Institute of Technology","location":"美国纽约州纽约市第7大道27街","country":"美国_United States","applydifficulty":"困难","major":"服装设计|插画设计","logopic":"http://www.mxsyzen.com/uploadfiles/image/201612/316.jpg","showpic":"http://www.mxsyzen.com/uploadfiles/image/201702/25.jpg","applyendtime":"10月1日、常规：1月1日","degree":"学士,硕士","toefl":"80","ielts":"6.5"}]
         */

        private String total;
        private int total_page;
        private int per_page;
        private String current_page;
        private List<ColleglistBean> colleglist;

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

        public List<ColleglistBean> getColleglist() {
            return colleglist;
        }

        public void setColleglist(List<ColleglistBean> colleglist) {
            this.colleglist = colleglist;
        }

        public static class ColleglistBean {
            /**
             * id : 940
             * chname : 帕森斯设计学院
             * enname : Parsons The New School for Design
             * location : 美国纽约州纽约西12街66号
             * country : 美国_United States
             * applydifficulty : 困难
             * major : 服装设计|室内设计|插画设计
             * logopic : http://www.mxsyzen.com/uploadfiles/image/201612/312.jpg
             * showpic : http://www.mxsyzen.com/uploadfiles/image/201702/29.jpg
             * applyendtime : 11月1日、常规：1月15日
             * degree : 学士,硕士
             * toefl : 92
             * ielts : 7.0
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
