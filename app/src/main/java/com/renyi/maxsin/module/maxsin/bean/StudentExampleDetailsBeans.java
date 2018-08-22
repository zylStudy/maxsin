package com.renyi.maxsin.module.maxsin.bean;

/**
 * Created by zhangyuliang on 2018/8/21.
 */

public class StudentExampleDetailsBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"title":"何同学","thumb":"http://www.mxsyzen.com/uploadfiles/image/201610/44.jpg","keywords":"建筑设计","url":"http://www.mxsyzen.com//case/808.html","jiangxuejin":"180万人民币","luquyuanxiao":"罗德岛设计学院、康奈尔大学、纽约大学阿布扎比分校、布朗大学、华盛顿大学、莱斯大学、加州大学伯克利分校、弗吉尼亚大学","shenqingxuewei":"本科","cover":"http://www.mxsyzen.com/uploadfiles/image/201610/36.jpg","offer":"http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg","show":"http://www.mxsyzen.com/uploadfiles/image/201612/552.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/38.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/39.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/40.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/41.jpg,http://www.mxsyzen.com/uploadfiles/image/201612/553.jpg,http://www.mxsyzen.com/uploadfiles/image/201612/554.jpg","caseDetail":"&lt;p&gt;\u201c在我的认知里，梦想是随着年龄和环境而改变的，这其中甚至包含了些许的命中注定。我一直在寻找，寻找一个可以让我的青春有所追求，让我值得为之倾尽全力的信念与具体的目标，因为我是一个要有规划有计划才能做好事情的人。\u201d&lt;/p&gt;&lt;p&gt;作为一个准艺术留学生，何同学早早就选择了美行思远，在美行思远成都校区导师的悉心指导下，何同学一步步夯实专业基础。深知作品集在留学中的重要性，先期开展项目调查，并自己总结出项目思路，作品命题，通过初期作品定义，中期作品评测，后期作品排版及院校申请，何同学很高效的完成了整个作品集培训过程，并成功申请纽约大学阿布扎比分校，同时获得180万奖学金，也是2015年中国艺术留学生最高额奖学金获得者。&lt;/p&gt;"}
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
         * title : 何同学
         * thumb : http://www.mxsyzen.com/uploadfiles/image/201610/44.jpg
         * keywords : 建筑设计
         * url : http://www.mxsyzen.com//case/808.html
         * jiangxuejin : 180万人民币
         * luquyuanxiao : 罗德岛设计学院、康奈尔大学、纽约大学阿布扎比分校、布朗大学、华盛顿大学、莱斯大学、加州大学伯克利分校、弗吉尼亚大学
         * shenqingxuewei : 本科
         * cover : http://www.mxsyzen.com/uploadfiles/image/201610/36.jpg
         * offer : http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg
         * show : http://www.mxsyzen.com/uploadfiles/image/201612/552.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/38.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/39.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/40.jpg,http://www.mxsyzen.com/uploadfiles/image/201610/41.jpg,http://www.mxsyzen.com/uploadfiles/image/201612/553.jpg,http://www.mxsyzen.com/uploadfiles/image/201612/554.jpg
         * caseDetail : &lt;p&gt;“在我的认知里，梦想是随着年龄和环境而改变的，这其中甚至包含了些许的命中注定。我一直在寻找，寻找一个可以让我的青春有所追求，让我值得为之倾尽全力的信念与具体的目标，因为我是一个要有规划有计划才能做好事情的人。”&lt;/p&gt;&lt;p&gt;作为一个准艺术留学生，何同学早早就选择了美行思远，在美行思远成都校区导师的悉心指导下，何同学一步步夯实专业基础。深知作品集在留学中的重要性，先期开展项目调查，并自己总结出项目思路，作品命题，通过初期作品定义，中期作品评测，后期作品排版及院校申请，何同学很高效的完成了整个作品集培训过程，并成功申请纽约大学阿布扎比分校，同时获得180万奖学金，也是2015年中国艺术留学生最高额奖学金获得者。&lt;/p&gt;
         */

        private String title;
        private String thumb;
        private String keywords;
        private String url;
        private String jiangxuejin;
        private String luquyuanxiao;
        private String shenqingxuewei;
        private String cover;
        private String offer;
        private String show;
        private String offershow;
        private String caseDetail;
        private String traintime;
        private String graduatschool;
        private String ibase;

        public String getOffershow() {
            return offershow;
        }

        public void setOffershow(String offershow) {
            this.offershow = offershow;
        }

        public String getIbase() {
            return ibase;
        }

        public void setIbase(String ibase) {
            this.ibase = ibase;
        }

        public String getTraintime() {
            return traintime;
        }

        public void setTraintime(String traintime) {
            this.traintime = traintime;
        }

        public String getGraduatschool() {
            return graduatschool;
        }

        public void setGraduatschool(String graduatschool) {
            this.graduatschool = graduatschool;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getJiangxuejin() {
            return jiangxuejin;
        }

        public void setJiangxuejin(String jiangxuejin) {
            this.jiangxuejin = jiangxuejin;
        }

        public String getLuquyuanxiao() {
            return luquyuanxiao;
        }

        public void setLuquyuanxiao(String luquyuanxiao) {
            this.luquyuanxiao = luquyuanxiao;
        }

        public String getShenqingxuewei() {
            return shenqingxuewei;
        }

        public void setShenqingxuewei(String shenqingxuewei) {
            this.shenqingxuewei = shenqingxuewei;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public String getCaseDetail() {
            return caseDetail;
        }

        public void setCaseDetail(String caseDetail) {
            this.caseDetail = caseDetail;
        }
    }
}
