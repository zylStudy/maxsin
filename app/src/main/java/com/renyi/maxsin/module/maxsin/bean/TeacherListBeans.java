package com.renyi.maxsin.module.maxsin.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2018/8/24.
 */

public class TeacherListBeans {
    /**
     * code : 800
     * message : 成功
     * data : {"id":"617","name":"Dana Wang","major":"插画、服装设计","photo":"http://www.mxsyzen.com/uploadfiles/teachers/160115/1-160115155229632.jpg","educollege":"欧洲设计学院  坎伯韦尔艺术学院","desc":"在伦敦期间，以Freelance插画师的身份参与商业项目海报设计，书籍封面设计等。 多次与插画师Janet Wolley 以及David Williams 进行商业插画合作项目。 \n\n艺术留学作品集培训经验丰富，辅导学生成功申请到英国皇家艺术学院、纽约视觉艺术学院、伦敦艺术大学、金斯顿大学、马里兰艺术学院等知名海外艺术院校。","goodcourse":"插画 | 服装设计","prize":["以Freelance插画师的身份参与商业项目海报设计","获得时装插画师Michalis Christodoulou推荐与买手店 10 corso como 进行合作项目","多次与插画师Janet Wolley 以及David Williams 进行商业插画合作项目"],"experience":["曾任深圳市光合文化发展有限公司平面设计师","2年多艺术教育机构作品集辅导经验"],"workpic":["http://www.mxsyzen.com/uploadfiles/image/201612/552.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/38.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/39.jpg"]}
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
         * id : 617
         * name : Dana Wang
         * major : 插画、服装设计
         * photo : http://www.mxsyzen.com/uploadfiles/teachers/160115/1-160115155229632.jpg
         * educollege : 欧洲设计学院  坎伯韦尔艺术学院
         * desc : 在伦敦期间，以Freelance插画师的身份参与商业项目海报设计，书籍封面设计等。 多次与插画师Janet Wolley 以及David Williams 进行商业插画合作项目。

         艺术留学作品集培训经验丰富，辅导学生成功申请到英国皇家艺术学院、纽约视觉艺术学院、伦敦艺术大学、金斯顿大学、马里兰艺术学院等知名海外艺术院校。
         * goodcourse : 插画 | 服装设计
         * prize : ["以Freelance插画师的身份参与商业项目海报设计","获得时装插画师Michalis Christodoulou推荐与买手店 10 corso como 进行合作项目","多次与插画师Janet Wolley 以及David Williams 进行商业插画合作项目"]
         * experience : ["曾任深圳市光合文化发展有限公司平面设计师","2年多艺术教育机构作品集辅导经验"]
         * workpic : ["http://www.mxsyzen.com/uploadfiles/image/201612/552.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/42.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/38.jpg","http://www.mxsyzen.com/uploadfiles/image/201610/39.jpg"]
         */

        private String id;
        private String name;
        private String major;
        private String photo;
        private String educollege;
        private String desc;
        private String goodcourse;
        private List<String> prize;
        private List<String> experience;
        private List<String> workpic;

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

        public String getGoodcourse() {
            return goodcourse;
        }

        public void setGoodcourse(String goodcourse) {
            this.goodcourse = goodcourse;
        }

        public List<String> getPrize() {
            return prize;
        }

        public void setPrize(List<String> prize) {
            this.prize = prize;
        }

        public List<String> getExperience() {
            return experience;
        }

        public void setExperience(List<String> experience) {
            this.experience = experience;
        }

        public List<String> getWorkpic() {
            return workpic;
        }

        public void setWorkpic(List<String> workpic) {
            this.workpic = workpic;
        }
    }
}
