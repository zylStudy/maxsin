package com.renyi.maxsin.module.Study.bean;

/**
 * Created by zhangyuliang on 2018/4/11.
 */

public class CourseDetailsBean {
    /**
     * code : 800
     * message : 成功
     * data : {"head_img":"http://edu.mxsyzen.com/images/addTeacher/img.png","s_name":"黄逸菲","profess_name":"服装设计","c_date":"2017-12-23","c_start_time":"10:00","c_end_time":"11:00","c_type":"普通项目课","c_name":"项目二","c_status":"待确认","c_key":"淡淡的","add_homework":"点点滴滴","homework_evaluate":"点点滴滴"}
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
         * head_img : http://edu.mxsyzen.com/images/addTeacher/img.png
         * s_name : 黄逸菲
         * profess_name : 服装设计
         * c_date : 2017-12-23
         * c_start_time : 10:00
         * c_end_time : 11:00
         * c_type : 普通项目课
         * c_name : 项目二
         * c_status : 待确认
         * c_key : 淡淡的
         * add_homework : 点点滴滴
         * homework_evaluate : 点点滴滴
         */

        private String head_img;
        private String t_name;
        private String teacher_profess;
        private String c_date;
        private String c_start_time;
        private String c_end_time;
        private String c_type;
        private String c_name;
        private String c_status;
        private String c_key;
        private String add_homework;
        private String homework_evaluate;

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getT_name() {
            return t_name;
        }

        public void setT_name(String t_name) {
            this.t_name = t_name;
        }

        public String getTeacher_profess() {
            return teacher_profess;
        }

        public void setTeacher_profess(String teacher_profess) {
            this.teacher_profess = teacher_profess;
        }

        public String getC_date() {
            return c_date;
        }

        public void setC_date(String c_date) {
            this.c_date = c_date;
        }

        public String getC_start_time() {
            return c_start_time;
        }

        public void setC_start_time(String c_start_time) {
            this.c_start_time = c_start_time;
        }

        public String getC_end_time() {
            return c_end_time;
        }

        public void setC_end_time(String c_end_time) {
            this.c_end_time = c_end_time;
        }

        public String getC_type() {
            return c_type;
        }

        public void setC_type(String c_type) {
            this.c_type = c_type;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_status() {
            return c_status;
        }

        public void setC_status(String c_status) {
            this.c_status = c_status;
        }

        public String getC_key() {
            return c_key;
        }

        public void setC_key(String c_key) {
            this.c_key = c_key;
        }

        public String getAdd_homework() {
            return add_homework;
        }

        public void setAdd_homework(String add_homework) {
            this.add_homework = add_homework;
        }

        public String getHomework_evaluate() {
            return homework_evaluate;
        }

        public void setHomework_evaluate(String homework_evaluate) {
            this.homework_evaluate = homework_evaluate;
        }
    }
}
