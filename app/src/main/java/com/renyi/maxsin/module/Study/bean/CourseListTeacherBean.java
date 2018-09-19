package com.renyi.maxsin.module.Study.bean;

import java.util.List;

/**
 * Created by zhangyuliang on 2017/7/25.
 */

public class CourseListTeacherBean {


    /**
     * code : 800
     * message : 成功
     * data : {"total":5,"per_page":10,"current_page":"1","last_page":1,"course_list":[{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"12","is_first":"0","c_date":"2017-07-15","c_start_time":"10:00","c_end_time":"11:30","change_recorder":"","c_status":"2","c_type":"项目课","c_name":"专业项目"},{"t_id":"2","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499417278nixpbt.jpg","user_name":"殷晨","professional":"服务设计","c_id":"27","is_first":"0","c_date":"2017-07-13","c_start_time":"10:30","c_end_time":"11:00","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"10","is_first":"0","c_date":"2017-07-13","c_start_time":"10:00","c_end_time":"11:30","change_recorder":"","c_status":"2","c_type":"项目课","c_name":"专业项目"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"24","is_first":"0","c_date":"2017-07-12","c_start_time":"9:00","c_end_time":"9:30","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"17","is_first":"0","c_date":"2017-07-11","c_start_time":"9:00","c_end_time":"9:30","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"}]}
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
         * total : 5
         * per_page : 10
         * current_page : 1
         * last_page : 1
         * course_list : [{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"12","is_first":"0","c_date":"2017-07-15","c_start_time":"10:00","c_end_time":"11:30","change_recorder":"","c_status":"2","c_type":"项目课","c_name":"专业项目"},{"t_id":"2","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499417278nixpbt.jpg","user_name":"殷晨","professional":"服务设计","c_id":"27","is_first":"0","c_date":"2017-07-13","c_start_time":"10:30","c_end_time":"11:00","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"10","is_first":"0","c_date":"2017-07-13","c_start_time":"10:00","c_end_time":"11:30","change_recorder":"","c_status":"2","c_type":"项目课","c_name":"专业项目"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"24","is_first":"0","c_date":"2017-07-12","c_start_time":"9:00","c_end_time":"9:30","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"},{"t_id":"1","head_url":"http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG","user_name":"李蔚","professional":"纺织品设计","c_id":"17","is_first":"0","c_date":"2017-07-11","c_start_time":"9:00","c_end_time":"9:30","change_recorder":"","c_status":"2","c_type":"基础课","c_name":"美学基础课"}]
         */

        private int total;
        private int per_page;
        private String current_page;
        private int last_page;
        private List<CourseListBean> course_list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
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

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<CourseListBean> getCourse_list() {
            return course_list;
        }

        public void setCourse_list(List<CourseListBean> course_list) {
            this.course_list = course_list;
        }

        public static class CourseListBean {
            /**
             * t_id : 1
             * head_url : http://mxsy2305.6655.la:18080/data_sourse/t_head_img/1499416996qykjwu.JPG
             * user_name : 李蔚
             * professional : 纺织品设计
             * c_id : 12
             * is_first : 0
             * c_date : 2017-07-15
             * c_start_time : 10:00
             * c_end_time : 11:30
             * change_recorder :
             * c_status : 2
             * c_type : 项目课
             * c_name : 专业项目
             */

            private String t_id;
            private String head_url;
            private String user_name;
            private String professional;
            private String c_id;
            private String is_first;
            private String c_date;
            private String c_start_time;
            private String c_end_time;
            private String change_recorder;
            private String c_status;
            private String c_type;
            private String c_name;
            private String course_id;
            private String t_head_url;

            public String getCourse_id() {
                return course_id;
            }

            public void setCourse_id(String course_id) {
                this.course_id = course_id;
            }

            public String getT_head_url() {
                return t_head_url;
            }

            public void setT_head_url(String t_head_url) {
                this.t_head_url = t_head_url;
            }

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getHead_url() {
                return head_url;
            }

            public void setHead_url(String head_url) {
                this.head_url = head_url;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getProfessional() {
                return professional;
            }

            public void setProfessional(String professional) {
                this.professional = professional;
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getIs_first() {
                return is_first;
            }

            public void setIs_first(String is_first) {
                this.is_first = is_first;
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

            public String getChange_recorder() {
                return change_recorder;
            }

            public void setChange_recorder(String change_recorder) {
                this.change_recorder = change_recorder;
            }

            public String getC_status() {
                return c_status;
            }

            public void setC_status(String c_status) {
                this.c_status = c_status;
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
        }
    }
}
